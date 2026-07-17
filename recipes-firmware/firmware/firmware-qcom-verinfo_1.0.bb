DESCRIPTION = "Install verinfo at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "(qcm6490|qcs9100|qcs8300|qcs615)"

SRC_URI ="${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "27ef79f60f961b21da10cab618f0d299b5d73b0ff82809d0044fa93199c56a6a"
SRC_URI[qcs9100.sha256sum] = "55664228c84e89693f6bdcbaadb457532d765f7b00f5181d966481200bbace3e"
SRC_URI[qcs8300.sha256sum] = "9ec967e5d4aa2252360a233439cc02d370f57d3728abdd971532fe24b5348d8c"
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
