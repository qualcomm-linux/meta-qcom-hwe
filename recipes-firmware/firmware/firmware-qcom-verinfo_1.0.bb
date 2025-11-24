DESCRIPTION = "Install verinfo at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "(qcm6490|qcs9100|qcs8300|qcs615)"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "5d511a815c4c34d890e3bd81270e66648982e6b4d5a31dc1a5095f5bf6de9460"
SRC_URI[qcs9100.sha256sum] = "c4978685d9898bff4dd8c75a7c9df157af2d7b2474f73020f6ae444cdcac550c"
SRC_URI[qcs8300.sha256sum] = "b36e47346bc919654ac5cd3cd4436306f5cb171a80419c8a9e1f6e2abf13e767"
SRC_URI[qcs615.sha256sum] = "fa061c1c2d1c7b10061fa36b9aa704bd1d5e24c821a029a49cce6f4701158d4c"

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

        socdir=$(echo "${fwdir/_fw/}" | tr '[:upper:]' '[:lower:]')
	mkdir -p ${D}${nonarch_base_libdir}/firmware/qcom/${socdir}
        find "${WORKDIR}/$fwdir/lib/firmware/qcom" -type f  -name "Ver_Info.txt" \
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
