DESCRIPTION = "Recipe to install firmware files at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "0dee525f1469d49a8d7269dae5e8e093ea4c61de15e8a40714979ff7fd287163"
SRC_URI[qcs9100.sha256sum] = "368477b5a1ec7e3318a01bcf6be1c3c78aeb150b1b2389de48487b93a9e19d76"
SRC_URI[qcs8300.sha256sum] = "a8c6fa1432d67e46f886dec7feb5996406eb4c358d8399126385b0bb6ecdf637"
SRC_URI[qcs615.sha256sum] = "480701bb354ca01efe054fd3beb3aeb273f0f3c2954afabc90ec39bcdca0d200"

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
