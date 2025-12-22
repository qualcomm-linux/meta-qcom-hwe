DESCRIPTION = "Recipe to install partition.xml in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

PROVIDES += "virtual/partconf"

SRC_URI ="${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "3bfc7748493e4ac8d8ad77db5c132b5301195ce02d377c62171332a81901f552"
SRC_URI[qcs9100.sha256sum] = "963d54a9e9a3ef29c6933b3d71faa035b72df07cc36f42c14c39ed5f47878026"
SRC_URI[qcs8300.sha256sum] = "2067015d9a96d7e009ffec3a42bb7f88a659eb96c6b9e6f9df849af222a8f0e3"
SRC_URI[qcs615.sha256sum]  = "a5cd09352d760699ca79d76b28a20f6519db8d2c3b121074c15235d01f7e3a76"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

BOOTBINARIES:qcm6490 = "QCM6490_bootbinaries"
BOOTBINARIES:qcs9100 = "QCS9100_bootbinaries"
BOOTBINARIES:qcs8300 = "QCS8300_bootbinaries"
BOOTBINARIES:qcs615  = "QCS615_bootbinaries"

BOOTBINARIES_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

# Default parition xml
PARTITION_XML ?= "partition_ufs.xml"
PARTITION_XML:emmc-storage ?= "partition_emmc.xml"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python do_install() {

    fw_file = d.getVar("BOOTBINARIES")
    fw_path = d.getVar("BOOTBINARIES_PATH")

    firmware_install(d, fw_file, fw_path)

    import os
    import shutil

    # Remove all files except partition xmls.
    for item in os.listdir(d.getVar('D')):
        name, ext = os.path.splitext(item)
        if name.startswith('partition') and ext == '.xml':
            continue
        else:
            if os.path.isdir(os.path.join(d.getVar('D'), item)):
                shutil.rmtree(os.path.join(d.getVar('D'), item))
            else:
                os.remove(os.path.join(d.getVar('D'), item))

}

inherit deploy

do_deploy() {
    # Deploy default xml as partition.xml at root of deploydir
    if [ -f "${D}/${PARTITION_XML}" ]; then
        install -m 0644 ${D}/${PARTITION_XML} ${DEPLOYDIR}/partition.xml
    else
        install -m 0644 ${D}/partition.xml ${DEPLOYDIR}/partition.xml
    fi

    # Deploy ufs xml inside ufs specific dir.
    if [ -f "${D}/partition_ufs.xml" ]; then
        install -d ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${D}/partition_ufs.xml ${DEPLOYDIR}/partition_ufs/partition.xml
    fi

    # Deploy emmc xml inside emmc specific dir.
    if [ -f "${D}/partition_emmc.xml" ]; then
        install -d ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${D}/partition_emmc.xml ${DEPLOYDIR}/partition_emmc/partition.xml
    fi
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/*.xml"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "arch"
