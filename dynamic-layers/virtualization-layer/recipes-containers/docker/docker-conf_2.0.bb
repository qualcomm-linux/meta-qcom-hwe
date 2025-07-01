SUMMARY = "Docker configuration files"
HOMEPAGE = "https://www.docker.com"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};\
md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "\
        file://daemon.json \
        file://mappings_qcs6490.json \
        file://mappings_qcs8300.json \
        file://mappings_qcs9100.json \
        "

DEPENDS += "jq-native"

do_install[depends] += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgbm', 'msm', 'msm:do_install', '', d)}"
do_install[depends] += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgles1', 'qcom-adreno', 'qcom-adreno:do_install', '', d)}"

do_install:prepend () {
    local MACHINE_NAME=${MACHINE}
    MACHINE_NAME=`echo "${MACHINE_NAME%%-*}"`

    local CONFIG_JSON="${WORKDIR}/mappings_${MACHINE_NAME}.json"
    local PATH_TO_DOCKER_CDI_JSON="${WORKDIR}/docker-run-cdi-hw-acc.json"
    local SUPPORTED_MACHINE="false"

    for SUPPORTED_JSON in ${WORKDIR}/mappings_*.json; do
        [ "${CONFIG_JSON}" == "${SUPPORTED_JSON}" ] && {
            SUPPORTED_MACHINE="true"
        } || {
            continue;
        }
    done

    [ "${SUPPORTED_MACHINE}" == "true" ] && {
        local JSON_CONTENT=$(cat ${CONFIG_JSON})
        local CONFIG_JSON_NAME=`basename ${CONFIG_JSON}`
        local VERSIONED_CONFIG_JSON="${WORKDIR}/versioned_${CONFIG_JSON_NAME}"

        declare -a PLATFORM_SPECIFIC_LIBS_ARRAY=""
        PLATFORM_SPECIFIC_LIBS_ARRAY=`echo ${JSON_CONTENT} | jq '.Platform_Libraries_To_Mount[]' | tr -d '"'`
        [ -z "${PLATFORM_SPECIFIC_LIBS_ARRAY}" ] && {
            echo "Platform_Libraries_To_Mount attribute in ${CONFIG_JSON_NAME} is not set !!!"
            return -1
        }

        declare -a PLATFORM_SPECIFIC_MAPS_ARRAY=""
        PLATFORM_SPECIFIC_MAPS_ARRAY=$(
            echo ${JSON_CONTENT} | jq '.Platform_Specific_Mappings[]' | tr -d '"'
        )
        [ -z "${PLATFORM_SPECIFIC_MAPS_ARRAY}" ] && {
            print-red "Platform_Specific_Mappings attribute in ${PATH_TO_CONFIG_JSON} is not set !!!"
            return -1
        }

        declare -a VERSIONED_PLATFORM_LIBS_ARRAY=""
        declare -a SORTED_VERSIONED_PLATFORM_LIBS_ARRAY=""
        local PKG_CONFIG_DIR="${SYSROOT_LIBDIR}/pkgconfig/"
        export PKG_CONFIG_PATH="${PKG_CONFIG_DIR}"

        # For versioned platform specific libs, map specific version. For the rest, leave as is.
        for PLATFORM_LIB in ${PLATFORM_SPECIFIC_LIBS_ARRAY[@]}; do
            echo "${PLATFORM_LIB}" | grep -q "/usr/lib" && {
                local PLATFORM_LIB_NAME=`basename ${PLATFORM_LIB} | cut -d\. -f1`
                local PLATFORM_LIB_DIR=`dirname ${PLATFORM_LIB}`
                pkg-config --modversion --silence-errors ${PLATFORM_LIB_NAME} > /dev/null && {
                    local VERSION=`pkg-config --modversion --silence-errors ${PLATFORM_LIB_NAME}`
                    local SHORT_VERSION=`echo ${VERSION} | cut -b 1`
                    VERSIONED_PLATFORM_LIBS_ARRAY+="${PLATFORM_LIB_DIR}/${PLATFORM_LIB_NAME}.so.${VERSION} "
                    VERSIONED_PLATFORM_LIBS_ARRAY+="${PLATFORM_LIB_DIR}/${PLATFORM_LIB_NAME}.so.${SHORT_VERSION} "
                } || {
                    VERSIONED_PLATFORM_LIBS_ARRAY+="${PLATFORM_LIB} "
                }
            } || {
                VERSIONED_PLATFORM_LIBS_ARRAY+="${PLATFORM_LIB} "
            }
        done

        # Sort versioned libs
        SORTED_VERSIONED_PLATFORM_LIBS_ARRAY+=`echo "${VERSIONED_PLATFORM_LIBS_ARRAY[@]}" | \
            tr ' ' '\n' | sort -u | tr '\n' ' '`

        # Fill contents of sorted versioned platform libs json and save to output file
        declare -a JSON_STRING_VERSIONED_PLATFORM_LIBS="{\"Platform_Libraries_To_Mount\": ["

        for VERSIONED_LIB in ${SORTED_VERSIONED_PLATFORM_LIBS_ARRAY[@]}; do
            JSON_STRING_VERSIONED_PLATFORM_LIBS+="\"${VERSIONED_LIB}\", "
        done

        JSON_STRING_VERSIONED_PLATFORM_LIBS=`echo ${JSON_STRING_VERSIONED_PLATFORM_LIBS} | sed 's/\(.*\),/\1 /'`
        JSON_STRING_VERSIONED_PLATFORM_LIBS+="]}"

        jq -n                                                                                      \
            --arg cdiVersion "0.6.0"                                                               \
            --arg kind "qualcomm.com/device"                                                       \
            --argjson devices "[$( jq -n                                                           \
            --arg name "cdi-hw-acc"                                                                \
                --argjson containerEdits "$( jq -n                                                 \
                    --argjson deviceNodes "$(echo ${JSON_CONTENT} | jq                             \
                                            '[.Platform_Specific_Mappings[] | { "path": . }]')"    \
                    --argjson mounts "$(echo ${JSON_STRING_VERSIONED_PLATFORM_LIBS} | jq           \
                        '[.Platform_Libraries_To_Mount[] | { "hostPath": ., "containerPath": ., "options": ["bind"] }]')" \
                    '$ARGS.named')"                                                                \
                '$ARGS.named')]"                                                                   \
            '$ARGS.named' > ${PATH_TO_DOCKER_CDI_JSON}                                             \
                                                                                                || {
            print-red "Failed to generate Docker CDI json file !!!"
            rm -rf ${PATH_TO_DOCKER_CDI_JSON}
            return -1
        }
    }
}

do_install () {
    install -d ${D}${sysconfdir}/cdi
    install -d ${D}${sysconfdir}/docker
    install -m 0644 ${WORKDIR}/daemon.json ${D}${sysconfdir}/docker/

    local MACHINE_NAME=${MACHINE}
    MACHINE_NAME=`echo "${MACHINE_NAME%%-*}"`

    local CONFIG_JSON="${WORKDIR}/mappings_${MACHINE_NAME}.json"
    local SUPPORTED_MACHINE="false"

    for SUPPORTED_JSON in ${WORKDIR}/mappings_*.json; do
        [ "${CONFIG_JSON}" == "${SUPPORTED_JSON}" ] && {
            SUPPORTED_MACHINE="true"
        } || {
            continue;
        }
    done

    [ "${SUPPORTED_MACHINE}" == "true" ] && {
        install -m 0644 ${WORKDIR}/docker-run-cdi-hw-acc.json ${D}${sysconfdir}/cdi/docker-run-cdi-hw-acc.json
    }

    return 0
}

FILES:${PN} += "\
    ${sysconfdir}/cdi/ \
    ${sysconfdir}/docker/ \
    "
