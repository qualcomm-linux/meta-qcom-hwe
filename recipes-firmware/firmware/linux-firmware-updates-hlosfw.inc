# Audio firmware
HLOSFW_ARTIFACTORY = "softwarecenter.qualcomm.com/download/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"

SRC_URI[QCM6490_fw.sha256sum] = "9f7651efc5a79f68d060e01e04ab6b807b5c185fded59d3d416c0d3b0f1a7b35"
SRC_URI[QCS9100_fw.sha256sum] = "9d3dc8fb72a3ace8034e2623d5ab2298089027a2228af30f44f59d32560af0d3"
SRC_URI[QCS8300_fw.sha256sum] = "f963bb2116d338e407676039724d42fa6242be91214ad16a4c4e635e8bc180a5"
SRC_URI[QCS615_fw.sha256sum] = "1247a5585346408a82bdd44f248676bed22be70dfd481d938c40ce8e2448200c"

SRC_URI += "https://${HLOSFW_ARTIFACTORY}/r1.0_00108.0/qcm6490-le-1-0/common/build/ufs/bin/QCM6490_fw.zip;name=QCM6490_fw"
SRC_URI += "https://${HLOSFW_ARTIFACTORY}/r1.0_00108.0/qcs9100-le-1-0/common/build/ufs/bin/QCS9100_fw.zip;name=QCS9100_fw"
SRC_URI += "https://${HLOSFW_ARTIFACTORY}/r1.0_00108.0/qcs8300-le-1-0/common/build/ufs/bin/QCS8300_fw.zip;name=QCS8300_fw"
SRC_URI += "https://${HLOSFW_ARTIFACTORY}/r1.0_00108.0/qcs615-le-1-0/common/build/common/bin/QCS615_fw.zip;name=QCS615_fw"

def firmware_install(d, firmware, fw_path):
    import os
    import shutil
    import re

    dstdir = d.getVar('S')

    def find_file(root_folder, file_name):
        for root, dir, files in os.walk(root_folder):
            if file_name in files:
                return os.path.join(root, file_name)
        return None

    filename = f'{firmware}.zip'
    zipfile = find_file(fw_path, filename)

    if zipfile:
        if os.path.exists(zipfile):
            shutil.unpack_archive(zipfile, dstdir)
    else:
        workdir = d.getVar('WORKDIR')
        shutil.copytree(os.path.join(workdir, firmware), os.path.join(dstdir, firmware), symlinks=True, dirs_exist_ok=True)

    D = d.getVar('D')
    root = os.path.join(dstdir, firmware)
    # skip mdt formats except for qca
    skip_bnn = re.compile(
        r'^(?!.*[/\\]qca[/\\])(?!.*\.bin$).*\.(b\d+[a-zA-Z]*|mdt)$',
        re.IGNORECASE
    )
    def map_dest(rel):
        if rel.startswith('lib/firmware/updates/'):
            return rel
        if rel.startswith('lib/firmware/'):
            return rel.replace('lib/firmware/', 'lib/firmware/updates/', 1)
        return rel
    for dirpath, _, files in os.walk(root):
        print(files)
        for name in files:
            if skip_bnn.search(name):  # skip .b<number> files
                continue
            src = os.path.join(dirpath, name)
            rel = os.path.relpath(src, root)
            # Remove files in /usr/lib/dsp, as the same are provided by dspso fw.
            if rel.startswith('usr/lib/dsp/'):
                continue

            dst = os.path.join(D, map_dest(rel))
            print(rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            if not os.path.exists(dst):
                shutil.copy(src, dst)

python do_firmware_install_extra () {
    fw_file = d.getVar("HLOSFIRMWARE")
    fw_path = d.getVar("WORKDIR")
    import os
    import shutil

    for entry in os.scandir(fw_path):
        if entry.is_dir() and entry.name.endswith('_fw'):
            bb.debug(1, "Processing firmware directory: %s" % entry.name)
            firmware_install(d, entry.name, fw_path)

    # Move contents from /lib to /usr/lib if the usrmerge distro feature is enabled
    if bb.utils.contains('DISTRO_FEATURES', 'usrmerge', True, False, d):
        lib_path = os.path.join(d.getVar('D'), 'lib')
        firm_path = os.path.join(d.getVar('D'), 'lib/firmware')
        firm_path_usr = os.path.join(d.getVar('D'), 'usr/lib/firmware')
        shutil.copytree(firm_path, firm_path_usr, dirs_exist_ok=True)
        shutil.rmtree(lib_path)
}
addtask firmware_install_extra after do_install before do_package

PACKAGES:prepend = " \
    ${PN}-qcom-copyright \
    ${PN}-ath11k-qca6698aq-updates \
    ${PN}-ath11k-wcn6750-updates \
    ${PN}-ath11k-wcn6855-updates \
    ${PN}-qca-qca2066-updates \
    ${PN}-qca-qca6698-updates \
    ${PN}-qca-wcn6750-updates \
    ${PN}-qca-qca61x4-usb-updates \
    ${PN}-qcom-venus-5.4-updates \
    ${PN}-qcom-qcm6490-audio-updates \
    ${PN}-qcom-qcm6490-compute-updates \
    ${PN}-qcom-qcm6490-qupv3fw-updates \
    ${PN}-qcom-qcm6490-wifi-updates \
    ${PN}-qcom-qcs8300-audio-updates \
    ${PN}-qcom-qcs8300-compute-updates \
    ${PN}-qcom-qcs8300-generalpurpose-updates \
    ${PN}-qcom-qcs8300-qupv3fw-updates \
    ${PN}-qcom-sa8775p-audio-updates \
    ${PN}-qcom-sa8775p-compute-updates \
    ${PN}-qcom-sa8775p-generalpurpose-updates \
    ${PN}-qcom-sa8775p-qupv3fw-updates \
"

SUMMARY:${PN}-qcom-copyright = "QCOM copyright files overlay"
FILES:${PN}-qcom-copyright = "Qualcomm-Technologies-Inc.-Proprietary"

SUMMARY:${PN}-ath11k-qca6698aq-updates = "ATH11K QCA6698AQ firmware overlay"
FILES:${PN}-ath11k-qca6698aq-updates = "${FIRMWARE_UPDATES_DIR}/ath11k/QCA6698AQ"

SUMMARY:${PN}-ath11k-wcn6750-updates = "ATH11K WCN6750 firmware overlay"
FILES:${PN}-ath11k-wcn6750-updates = "${FIRMWARE_UPDATES_DIR}/ath11k/WCN6750"

SUMMARY:${PN}-ath11k-wcn6855-updates = "ATH11K WCN6855 firmware overlay"
FILES:${PN}-ath11k-wcn6855-updates = "${FIRMWARE_UPDATES_DIR}/ath11k/WCN6855"

SUMMARY:${PN}-qca-qca2066-updates = "QCA2066 firmware overlay"
FILES:${PN}-qca-qca2066-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qca/hpbtfw21.tlv* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.bin* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.bin* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.301* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.302* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.301* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.302* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.309* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.309* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.30a* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.30a* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b8c* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b9f* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.ba0* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.ba1* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.ba2* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.ba3* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.ba4* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.baa* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.bb8* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b10c* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b111* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b8c* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b9f* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.ba0* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.ba1* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.ba2* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.ba3* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.ba4* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.baa* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.bb8* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b10c* \
  ${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b111* \
"

SUMMARY:${PN}-qca-qca6698-updates = "QCA6698 firmware update files"
FILES:${PN}-qca-qca6698-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpbtfw21.tlv* \
  ${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21.b206* \
  ${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21.b207* \
  ${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21.bin* \
"

SUMMARY:${PN}-qca-wcn6750-updates = "QCA WCN6750 firmware update files"
FILES:${PN}-qca-wcn6750-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qca/msbtfw11.mbn* \
  ${FIRMWARE_UPDATES_DIR}/qca/msbtfw11.tlv* \
  ${FIRMWARE_UPDATES_DIR}/qca/msnv11.bin* \
  ${FIRMWARE_UPDATES_DIR}/qca/msnv11.b0a* \
  ${FIRMWARE_UPDATES_DIR}/qca/msnv11.b09* \
"

SUMMARY:${PN}-qca-qca61x4-usb-updates = "QCA QCA61x4 USB firmware update files"
FILES:${PN}-qca-qca61x4-usb = " \
  ${FIRMWARE_UPDATES_DIR}/qca/nvm_usb_*.bin* \
  ${FIRMWARE_UPDATES_DIR}/qca/rampatch_usb_*.bin* \
"

SUMMARY:${PN}-qcom-venus-5.4-updates = "QCOM venus 5.4 firmware overlay"
FILES:${PN}-qcom-venus-5.4-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/venus-5.4/* \
"

SUMMARY:${PN}-qcom-qcm6490-audio-updates = "QCOM qcom-qcm6490-audio firmware overlay"
FILES:${PN}-qcom-qcm6490-audio-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/adsp*.* \
  ${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/battmgr.jsn \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs6490/QCS6490-RB3Gen2-tplg.bin* \
"

SUMMARY:${PN}-qcom-qcm6490-compute-updates = "QCOM qcom-qcm6490-compute firmware overlay"
FILES:${PN}-qcom-qcm6490-compute-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/cdsp*.* \
"

SUMMARY:${PN}-qcom-qcm6490-qupv3fw-updates = "QCOM qcom-qcm6490-qupv3fw firmware overlay"
FILES:${PN}-qcom-qcm6490-qupv3fw-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/qupv3fw.elf* \
"

SUMMARY:${PN}-qcom-qcm6490-wifi-updates = "QCOM qcom-qcm6490-wifi firmware overlay"
FILES:${PN}-qcom-qcm6490-wifi-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/wpss.mbn* \
"

SUMMARY:${PN}-qcom-qcs8300-audio-updates = "QCOM qcom-qcs8300-audio firmware overlay"
FILES:${PN}-qcom-qcs8300-audio-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/adsp*.* \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/MONACO-EVK-tplg.bin* \
"

SUMMARY:${PN}-qcom-qcs8300-compute-updates = "QCOM qcom-qcs8300-compute firmware overlay"
FILES:${PN}-qcom-qcs8300-compute-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/cdsp*.* \
"

SUMMARY:${PN}-qcom-qcs8300-generalpurpose-updates = "QCOM qcom-qcs8300-generalpurpose firmware overlay"
FILES:${PN}-qcom-qcs8300-generalpurpose-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/gpdsp*.* \
"

SUMMARY:${PN}-qcom-qcs8300-qupv3fw-updates = "QCOM qcom-qcs8300-qupv3fw firmware overlay"
FILES:${PN}-qcom-qcs8300-qupv3fw-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/qupv3fw.elf* \
"

SUMMARY:${PN}-qcom-sa8775p-audio-updates = "QCOM qcom-sa8775p-audio firmware overlay"
FILES:${PN}-qcom-sa8775p-audio-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/adsp*.* \
  ${FIRMWARE_UPDATES_DIR}/qcom/qcs9100/LEMANS-EVK-tplg.bin* \
"

SUMMARY:${PN}-qcom-sa8775p-compute-updates = "QCOM qcom-sa8775p-compute firmware overlay"
FILES:${PN}-qcom-sa8775p-compute-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/cdsp*.* \
"

SUMMARY:${PN}-qcom-sa8775p-generalpurpose-updates = "QCOM qcom-sa8775p-generalpurpose firmware overlay"
FILES:${PN}-qcom-sa8775p-generalpurpose-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/gpdsp*.* \
"

SUMMARY:${PN}-qcom-sa8775p-qupv3fw-updates = "QCOM qcom-sa8775p-qupv3fw firmware overlay"
FILES:${PN}-qcom-sa8775p-qupv3fw-updates = " \
  ${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/qupv3fw.elf* \
"
