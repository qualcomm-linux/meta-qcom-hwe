DESCRIPTION = "Recipe to install NHLOS images in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

PROVIDES += "virtual/bootbins"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "74cde8a44295681a91a9033097343792b1111bb048c1d86f6a9584358e9d5442"
SRC_URI[qcs9100.sha256sum] = "efe9e256054eb07dd347059afb9f2989c47ec1214c2050012d9502e6a783e57f"
SRC_URI[qcs8300.sha256sum] = "9928e12bf7d6eacc9e7059915f254e5e6276d6f84c136bc689fc6d6f7b1b019a"
SRC_URI[qcs615.sha256sum]  = "fb0f29a9d092ed127e6ea6eb6ef44332bfeb8fc487fc305b6a48fd65e053b78e"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

BOOTBINARIES:qcm6490 = "QCM6490_bootbinaries"
BOOTBINARIES:qcs9100 = "QCS9100_bootbinaries"
BOOTBINARIES:qcs8300 = "QCS8300_bootbinaries"
BOOTBINARIES:qcs615  = "QCS615_bootbinaries"

BOOTBINARIES_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python do_install() {

    fw_file = d.getVar("BOOTBINARIES")
    fw_path = d.getVar("BOOTBINARIES_PATH")

    firmware_install(d, fw_file, fw_path)

    # Remove partition xmls.
    for item in os.listdir(d.getVar('D')):
        name, ext = os.path.splitext(item)
        if name.startswith('partition') and ext == '.xml':
            os.remove(os.path.join(d.getVar('D'), item))
        if name.startswith('contents') and ext == '.xml':
            os.remove(os.path.join(d.getVar('D'), item))

}

inherit deploy

do_deploy() {
    find "${D}" -maxdepth 1 -name '*.bin' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -maxdepth 1 -name '*.elf' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -maxdepth 1 -name '*.fv' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -maxdepth 1 -name '*.mbn' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -maxdepth 1 -name '*.melf' -exec install -m 0644 {} ${DEPLOYDIR} \;
    # Copy sail_nor files to deploydir
    for f in $(find "${D}/sail_nor" -type f -printf '%P ') ; do
        install -d ${DEPLOYDIR}/sail_nor
        install -m 0644 ${D}/sail_nor/$f ${DEPLOYDIR}/sail_nor/$f
    done
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/*.elf /*.mbn /*.bin /*.fv */.melf /sail_nor/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "arch"
