DESCRIPTION = "Recipe to install partition.xml in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

PROVIDES += "virtual/partconf"

SRC_URI ="${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "f51dc0a44d40532353d4aa31ac492bd5f52514100820f0203abeab6012a6bd85"
SRC_URI[qcs9100.sha256sum] = "f9e2ee53338653c816cc3dbed1fd07fa1e09b2dbb5dd7fc80217f0efce72f2d1"
SRC_URI[qcs8300.sha256sum] = "df72d6ba5f0fecf49121c3f435fef2f2c041acffd2035c53f53744104cbaea4e"
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
