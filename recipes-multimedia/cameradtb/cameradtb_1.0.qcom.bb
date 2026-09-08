inherit module deploy

DESCRIPTION = "QCOM Camera device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-devicetree.git;protocol=https"
SRCBRANCH  = "camera-kernel.qclinux.1.0.r1-rel"
SRCREV     = "2d4fc1d8e8b6750eb2ae92c5d3405b0ff9487fc4"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/camera-devicetree"

DEPENDS:qcom-custom-bsp += "cameradlkm"

S = "${WORKDIR}/vendor/qcom/opensource/camera-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs6490|qcs8300|qcs615"
KODIAK_BOARD_NAMES = "qcm6490-idp|qcs6490-rb3gen2-vision-kit|qcs6490-rb3gen2-core-kit|"
LEMANS_BOARD_NAMES = "qcs9100-ride-sx|qcs9075-ride-sx|qcs9075-rb8-core-kit|qcs9075-iq-9075-evk|qcs9075-iq-9075-evk-ifp|"
MONACO_BOARD_NAMES = "qcs8300-ride-sx|qcs8275-iq-8275-evk|qcs8275-iq-8275-evk-ifp|qcs8275-iq-8275-evk-pro-sku|monaco-monza"

python get_soc_family() {
    need_machine = d.getVar('COMPATIBLE_MACHINE')
    all_boards = d.getVar('KODIAK_BOARD_NAMES')
    all_boards += d.getVar('LEMANS_BOARD_NAMES')
    all_boards += d.getVar('MONACO_BOARD_NAMES')

    import re
    compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
    for m in compat_machines:
        if re.match(need_machine, m):
            d.appendVar('SOC_FAM', m)
            break
    target_board = (d.getVar('MACHINEOVERRIDES') or "").split(":")
    for m in target_board:
        if re.match(all_boards, m):
            d.appendVar('TARGET_BOARD', m)
            break

    soc_family =  d.getVar('SOC_FAM')
}

do_compile[prefuncs] += "get_soc_family"

EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    if [ "${SOC_FAM}" = "qcm6490" ]; then
        if [ "${TARGET_BOARD}" = "qcm6490-idp" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-idp
        elif [ "${TARGET_BOARD}" = "qcs6490-rb3gen2-vision-kit" ] || \
             [ "${TARGET_BOARD}" = "qcs6490-rb3gen2-core-kit" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
            oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
        else
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-idp
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
            oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
        fi
    elif [ "${SOC_FAM}" = "qcs9100" ]; then
        if [ "${TARGET_BOARD}" = "qcs9100-ride-sx" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9100-ride-sx-camera
            oe_runmake ${EXTRA_OEMAKE} qcs9100-ride-sx-camera-el2
        elif [ "${TARGET_BOARD}" = "qcs9075-ride-sx" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9075-ride-sx-camera
            oe_runmake ${EXTRA_OEMAKE} qcs9075-ride-sx-camera-el2
        elif [ "${TARGET_BOARD}" = "qcs9075-rb8-core-kit" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-iq-9075-evk
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-iq-9075-evk-el2
        elif [ "${TARGET_BOARD}" = "qcs9075-iq-9075-evk" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-iq-9075-evk
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-iq-9075-evk-el2
        elif [ "${TARGET_BOARD}" = "qcs9075-iq-9075-evk-ifp" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-iq-9075-evk
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-iq-9075-evk-el2
        fi
    elif [ "${SOC_FAM}" = "qcs8300" ]; then
        if [ "${TARGET_BOARD}" = "qcs8300-ride-sx" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs8300-camera
            oe_runmake ${EXTRA_OEMAKE} qcs8300-camera-el2
        elif [ "${TARGET_BOARD}" = "qcs8275-iq-8275-evk" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs8275-camera-iq-8275-evk
            oe_runmake ${EXTRA_OEMAKE} qcs8275-camera-iq-8275-evk-el2
        elif [ "${TARGET_BOARD}" = "qcs8275-iq-8275-evk-ifp" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs8275-camera-iq-8275-evk
            oe_runmake ${EXTRA_OEMAKE} qcs8275-camera-iq-8275-evk-el2
        elif [ "${TARGET_BOARD}" = "qcs8275-iq-8275-evk-pro-sku" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs8275-camera-iq-8275-evk-pro-sku
            oe_runmake ${EXTRA_OEMAKE} qcs8275-camera-iq-8275-evk-pro-sku-el2
        elif [ "${TARGET_BOARD}" = "monaco-monza" ]; then
            oe_runmake ${EXTRA_OEMAKE} monaco-monza-camera
            oe_runmake ${EXTRA_OEMAKE} monaco-monza-camera-el2
        fi
    elif [ "${SOC_FAM}" = "qcs615" ]; then
        oe_runmake ${EXTRA_OEMAKE} qcs615-camera
    else
        echo "Unknown SOC_FAM -> " ${SOC_FAM}
    fi
}

do_install() {
    :
}

do_deploy() {
    echo "DTBO Staging path -> " ${DEPLOYDIR}/tech_dtbs
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 ${S}/*.dtbo ${DEPLOYDIR}/tech_dtbs
}

addtask do_deploy after do_install
