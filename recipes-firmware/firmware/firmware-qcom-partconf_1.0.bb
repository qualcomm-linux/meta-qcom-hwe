DESCRIPTION = "Recipe to install partition.xml in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

PROVIDES += "virtual/partconf"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "dfe40af418ddb56b3a579bc241442c5c2b83b5a53253fdd28c00e3c66394d3da"
SRC_URI[qcs9100.sha256sum] = "6f1e31e51521f644791638c80ac9feb19b7170eb8e65f8f0c28559cc8a6bc264"
SRC_URI[qcs8300.sha256sum] = "590731ed48217542f97030eec870db544d051e370bc57e5f775eb5598fcc8292"
SRC_URI[qcs615.sha256sum]  = "f442bd476b64312969d49e5b5369744e19ab193f0f8c05986b96307b65161a80"

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
