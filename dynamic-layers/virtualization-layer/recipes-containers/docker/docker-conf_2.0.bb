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
    MACHINE_NAME="${MACHINE}"
    MACHINE_NAME=$(echo "${MACHINE_NAME}" | cut -d '-' -f1)

    CONFIG_JSON="${WORKDIR}/mappings_${MACHINE_NAME}.json"

    SUPPORTED_MACHINE="false"

    # Parse all mappings.json files to map the machine name to correct target
    for SUPPORTED_JSON in "${WORKDIR}"/mappings_*.json; do
        JSON_CONTENT=$(cat "${SUPPORTED_JSON}")

        SOC_LIST=$(echo "${JSON_CONTENT}" | jq -r '.Soc[]')
        if [ -z "${SOC_LIST}" ]; then
            echo "Soc attribute in ${SUPPORTED_JSON} is not set !!!"
            continue
        fi

        for SOC in ${SOC_LIST}; do
            SOC_LOWER=$(echo "${SOC}" | tr '[:upper:]' '[:lower:]')
            if [ "${SOC_LOWER}" = "${MACHINE_NAME}" ]; then
                SUPPORTED_MACHINE="true"
                CONFIG_JSON="${SUPPORTED_JSON}"
                break
            fi
        done
    done

    PATH_TO_DOCKER_CDI_JSON="${WORKDIR}/docker-run-cdi-hw-acc.json"

    if [ "${SUPPORTED_MACHINE}" = "true" ]; then
        JSON_CONTENT=$(cat "${CONFIG_JSON}")
        CONFIG_JSON_NAME=$(basename "${CONFIG_JSON}")
        VERSIONED_CONFIG_JSON="${WORKDIR}/versioned_${CONFIG_JSON_NAME}"

        PLATFORM_SPECIFIC_LIBS_ARRAY=$(echo "${JSON_CONTENT}" |                                    \
            jq -r '.Platform_Libraries_To_Mount[]')
        if [ -z "${PLATFORM_SPECIFIC_LIBS_ARRAY}" ]; then
            echo "Platform_Libraries_To_Mount attribute in ${CONFIG_JSON_NAME} is not set !!!"
            return -1
        fi


        PLATFORM_SPECIFIC_MAPS_ARRAY=$(echo "${JSON_CONTENT}" | jq -r '.Platform_Specific_Mappings[]')
        if [ -z "${PLATFORM_SPECIFIC_MAPS_ARRAY}" ]; then
            print-red "Platform_Specific_Mappings attribute in ${PATH_TO_CONFIG_JSON} is not set !!!"
            return -1
        fi

        VERSIONED_PLATFORM_LIBS_ARRAY=""
        PKG_CONFIG_DIR="${SYSROOT_LIBDIR}/pkgconfig/"
        export PKG_CONFIG_PATH="${PKG_CONFIG_DIR}"

        # For versioned platform specific libs, map specific version. For the rest, leave as is.
        for PLATFORM_LIB in ${PLATFORM_SPECIFIC_LIBS_ARRAY}; do
            if echo "${PLATFORM_LIB}" | grep -q "/usr/lib"; then
                PLATFORM_LIB_NAME=$(basename "${PLATFORM_LIB}" | cut -d '.' -f1)
                PLATFORM_LIB_DIR=$(dirname "${PLATFORM_LIB}")
                if VERSION=$(pkg-config --modversion --silence-errors "${PLATFORM_LIB_NAME}"       \
                    2>/dev/null); then
                    SHORT_VERSION=$(echo "${VERSION}" | cut -b 1)
                    VERSIONED_PLATFORM_LIBS_ARRAY="${VERSIONED_PLATFORM_LIBS_ARRAY}                \
                        ${PLATFORM_LIB_DIR}/${PLATFORM_LIB_NAME}.so.${VERSION}                     \
                        ${PLATFORM_LIB_DIR}/${PLATFORM_LIB_NAME}.so.${SHORT_VERSION}"
                else
                    VERSIONED_PLATFORM_LIBS_ARRAY="${VERSIONED_PLATFORM_LIBS_ARRAY} ${PLATFORM_LIB}"
                fi
            else
                VERSIONED_PLATFORM_LIBS_ARRAY="${VERSIONED_PLATFORM_LIBS_ARRAY} ${PLATFORM_LIB}"
            fi
        done

        # Sort versioned libs
        SORTED_VERSIONED_PLATFORM_LIBS_ARRAY=$(echo "${VERSIONED_PLATFORM_LIBS_ARRAY}" |           \
            tr ' ' '\n' | sort -u | tr '\n' ' ')

        # Fill contents of sorted versioned platform libs json and save to output file
        JSON_STRING_VERSIONED_PLATFORM_LIBS="{\"Platform_Libraries_To_Mount\": ["
        for VERSIONED_LIB in ${SORTED_VERSIONED_PLATFORM_LIBS_ARRAY}; do
            JSON_STRING_VERSIONED_PLATFORM_LIBS="${JSON_STRING_VERSIONED_PLATFORM_LIBS}            \
                \"${VERSIONED_LIB}\", "
        done

        JSON_STRING_VERSIONED_PLATFORM_LIBS=$(echo "${JSON_STRING_VERSIONED_PLATFORM_LIBS}" |      \
            sed 's/,\s*$//')
        JSON_STRING_VERSIONED_PLATFORM_LIBS="${JSON_STRING_VERSIONED_PLATFORM_LIBS}]}"

        jq -n                                                                                      \
            --arg cdiVersion "0.6.0"                                                               \
            --arg kind "qualcomm.com/device"                                                       \
            --argjson devices "[$( jq -n                                                           \
            --arg name "cdi-hw-acc"                                                                \
                --argjson containerEdits "$( jq -n                                                 \
                    --argjson deviceNodes "$(echo "${JSON_CONTENT}" | jq                           \
                                            '[.Platform_Specific_Mappings[] | { "path": . }]')"    \
                    --argjson mounts "$(echo "${JSON_STRING_VERSIONED_PLATFORM_LIBS}" | jq         \
                        '[.Platform_Libraries_To_Mount[] | { "hostPath": ., "containerPath": ., "options": ["bind"] }]')" \
                    '$ARGS.named')"                                                                \
                '$ARGS.named')]"                                                                   \
            '$ARGS.named' > "${PATH_TO_DOCKER_CDI_JSON}"                                           \
                                                                                                || {
            print-red "Failed to generate Docker CDI json file !!!"
            rm -rf "${PATH_TO_DOCKER_CDI_JSON}"
            return -1
        }
    fi
}

do_install () {
    install -d "${D}${sysconfdir}/cdi"
    install -d "${D}${sysconfdir}/docker"
    install -m 0644 "${WORKDIR}/daemon.json" "${D}${sysconfdir}/docker/"

    MACHINE_NAME="${MACHINE}"
    MACHINE_NAME=$(echo "${MACHINE_NAME}" | cut -d '-' -f1)

    SUPPORTED_MACHINE="false"

    # Parse all mappings.json files to map the machine name to correct target
    for SUPPORTED_JSON in "${WORKDIR}"/mappings_*.json; do
        JSON_CONTENT=$(cat "${SUPPORTED_JSON}")

        SOC_LIST=$(echo "${JSON_CONTENT}" | jq -r '.Soc[]')
        if [ -z "${SOC_LIST}" ]; then
            echo "Soc attribute in ${SUPPORTED_JSON} is not set !!!"
            continue
        fi

        for SOC in ${SOC_LIST}; do
            SOC_LOWER=$(echo "${SOC}" | tr '[:upper:]' '[:lower:]')
            if [ "${SOC_LOWER}" = "${MACHINE_NAME}" ]; then
                SUPPORTED_MACHINE="true"
                break
            fi
        done
    done

    if [ "${SUPPORTED_MACHINE}" = "true" ]; then
        install -m 0644 "${WORKDIR}/docker-run-cdi-hw-acc.json" "${D}${sysconfdir}/cdi/docker-run-cdi-hw-acc.json"
    fi

    return 0
}

FILES:${PN} += "\
    ${sysconfdir}/cdi/ \
    ${sysconfdir}/docker/ \
    "
