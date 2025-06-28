DESCRIPTION = "Recipe to install firmware files at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "23dc576c4a420d15df770cd64fb01473b1ae9e101576473af54ae419a843bda4"
SRC_URI[qcs9100.sha256sum] = "5e20489ccd38cae8a97aa07248db5f29b39714af9815d1a6b7917a266b2f37bc"
SRC_URI[qcs8300.sha256sum] = "347e8f6ece22368b0d88b72f27ac4c4faeb4971fe248526fd4f7eebfe3655373"
SRC_URI[qcs615.sha256sum] = "c7429d40f0ac3d53c0d840819b16bc89d09ccdbd6250ca9ad3e5fb846b22dade"

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

python do_install() {

    fw_file = d.getVar("HLOSFIRMWARE")
    fw_path = d.getVar("HLOSFIRMWARE_PATH")

    firmware_install(d, fw_file, fw_path)

    import os
    import shutil

    # Remove files in /usr/lib/dsp, as the same are provided by dspso fw.
    dsp_fwpath = os.path.join(d.getVar('D'), 'usr/lib/dsp')
    if os.path.exists(dsp_fwpath) and os.path.isdir(dsp_fwpath):
        shutil.rmtree(dsp_fwpath)

    # Move contents from /lib to /usr/lib if the usrmerge distro feature is enabled
    if bb.utils.contains('DISTRO_FEATURES', 'usrmerge', True, False, d):
        lib_path = os.path.join(d.getVar('D'), 'lib')
        firm_path = os.path.join(d.getVar('D'), 'lib/firmware')
        firm_path_usr = os.path.join(d.getVar('D'), 'usr/lib/firmware')
        if os.path.exists(firm_path_usr):
            shutil.rmtree(firm_path_usr)
        shutil.copytree(firm_path, firm_path_usr)
        shutil.rmtree(lib_path)
}

inherit deploy

do_deploy() {
    install -D -m644 ${D}${nonarch_base_libdir}/firmware/qcom/${MATCHED_MACHINE}/Ver_Info.txt ${DEPLOYDIR}
}
addtask deploy before do_build after do_install

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "${nonarch_base_libdir}/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"
