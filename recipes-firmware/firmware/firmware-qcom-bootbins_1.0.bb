DESCRIPTION = "Recipe to install NHLOS images in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

PROVIDES += "virtual/bootbins"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "04d4a061a524604643b06abfa7c177789b0fbed225b706e4fcecd3d3a03130fa"
SRC_URI[qcs9100.sha256sum] = "34b8d8c8cb107145f96837e4b7727fb3ddc157708622bd4797290169ed5c1510"
SRC_URI[qcs8300.sha256sum] = "f0c343c29a4d36f60adc8c0a229b45660a97dbcda88002e311e8b7b5e080b93c"
SRC_URI[qcs615.sha256sum]  = "13f7cb56b505e0177c242dadc8bf1b8cf92aed9ef6fa31604796b0e937910fcb"

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
