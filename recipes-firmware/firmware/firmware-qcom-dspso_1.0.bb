DESCRIPTION = "Recipe to install dspso files on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${DSPSO}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "cf4368b152ea4c9d8000ea8f257e2df2d45204a357279a4cd950626515a54bbe"
SRC_URI[qcs9100.sha256sum] = "a5c94440736fe3ced81b6f1e91a2b35a1b0069b97434eacdb8d42a6f0331405e"
SRC_URI[qcs8300.sha256sum] = "ef71af5cb5ebfc54844ef48854a4bb5dd44171e7071932acf9eefdd3bac3ceb2"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

DSPSO:qcm6490 = "QCM6490_dspso"
DSPSO:qcs9100 = "QCS9100_dspso"
DSPSO:qcs8300 = "QCS8300_dspso"
DSPSO:qcs615  = "QCS615_dspso"

DSPSO_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"

python do_install() {

    fw_file = d.getVar("DSPSO")
    fw_path = d.getVar("DSPSO_PATH")

    firmware_install(d, fw_file, fw_path)
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/lib/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"
