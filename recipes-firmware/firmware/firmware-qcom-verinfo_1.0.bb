DESCRIPTION = "Install verinfo at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "(qcm6490|qcs9100|qcs8300|qcs615)"

SRC_URI ="${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "1214a6405d152d4fd319f556d5afcf173d22722efc1363ad88cef7578e9e744a"
SRC_URI[qcs9100.sha256sum] = "32b9820e0e022d88323104dca7677b20c76f45007f0099ec475301b84ff42384"
SRC_URI[qcs8300.sha256sum] = "9fb9956d1e29ae748214a09a2c7f92302e763b2359f307bf1e7cbfb3b0d1b290"
SRC_URI[qcs615.sha256sum] = "53378e80b2e9d5b33144d46d45f27363084a1dbb3ef9cbf53826d0e989f7210e"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

HLOSFIRMWARE:qcm6490 = "QCM6490_fw"
HLOSFIRMWARE:qcs9100 = "QCS9100_fw"
HLOSFIRMWARE:qcs8300 = "QCS8300_fw"
HLOSFIRMWARE:qcs615  = "QCS615_fw"

HLOSFIRMWARE_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    for dir in "${WORKDIR}"/*_fw/; do
        [ -d "$dir" ] || continue
        fwdir=$(basename "$dir")
        bbdebug 1 "Processing firmware directory: ${fwdir}"

        socdir=$(printf '%s' "$fwdir" | sed 's/_fw//g' | tr '[:upper:]' '[:lower:]')
        mkdir -p ${D}${nonarch_base_libdir}/firmware/qcom/${socdir}
        find "${WORKDIR}/$fwdir/lib/firmware/updates/qcom" -type f  -name "Ver_Info.txt" \
                 -exec cp -r {} ${D}${nonarch_base_libdir}/firmware/qcom/${socdir} \;
    done
}

inherit deploy

do_deploy() {
    install -D -m644 ${D}${nonarch_base_libdir}/firmware/qcom/${MATCHED_MACHINE}/Ver_Info.txt ${DEPLOYDIR}
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${nonarch_base_libdir}/firmware/qcom/*/Ver_Info.txt"
