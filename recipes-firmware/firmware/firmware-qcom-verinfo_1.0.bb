DESCRIPTION = "Install verinfo at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "(qcm6490|qcs9100|qcs8300|qcs615)"

SRC_URI ="${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "29d7217061b68b74f019971d3a4f6155509758175fba4c2e77c62d28040613f6"
SRC_URI[qcs9100.sha256sum] = "d566c8d00988ae70ede40fe34af85aeaf2fe21bd2d0ebcff93216e368979f616"
SRC_URI[qcs8300.sha256sum] = "8567a9b67a5bac7194494344ab540858c6712e8b2af1bf475c7f69d06242992d"
SRC_URI[qcs615.sha256sum] = "9d7bd5f2914881f17c38a838dd0b6af056b34482cb4aff3eeeecbf1c8aca2125"

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
