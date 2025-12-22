# To include downstream firmware provided by HLOSFW tars.
require firmware-qcm6490.inc
require firmware-qcs9100.inc
require firmware-qcs8300.inc
require firmware-qcs615.inc

QCM6490_SRC_URI ?= "${FW_ARTIFACTORY}/${QCM6490_FW_BUILD_ID}/${QCM6490_FW_BIN_PATH}/QCM6490_fw.zip;name=QCM6490_fw"
QCS9100_SRC_URI ?= "${FW_ARTIFACTORY}/${QCS9100_FW_BUILD_ID}/${QCS9100_FW_BIN_PATH}/QCS9100_fw.zip;name=QCS9100_fw"
QCS8300_SRC_URI ?= "${FW_ARTIFACTORY}/${QCS8300_FW_BUILD_ID}/${QCS8300_FW_BIN_PATH}/QCS8300_fw.zip;name=QCS8300_fw"
QCS615_SRC_URI  ?= "${FW_ARTIFACTORY}/${QCS615_FW_BUILD_ID}/${QCS615_FW_BIN_PATH}/QCS615_fw.zip;name=QCS615_fw"

SRC_URI[QCM6490_fw.sha256sum] = "${QCM6490_HLOSFW_SHA256SUM}"
SRC_URI[QCS9100_fw.sha256sum] = "${QCS9100_HLOSFW_SHA256SUM}"
SRC_URI[QCS8300_fw.sha256sum] = "${QCS8300_HLOSFW_SHA256SUM}"
SRC_URI[QCS615_fw.sha256sum] = "${QCS615_HLOSFW_SHA256SUM}"
SRC_URI += "${QCM6490_SRC_URI}"
SRC_URI += "${QCS9100_SRC_URI}"
SRC_URI += "${QCS8300_SRC_URI}"
SRC_URI += "${QCS615_SRC_URI}"

do_install:append:qcom (){
    for dir in "${WORKDIR}"/*_fw/; do
        [ -d "$dir" ] || continue
        fwdir=$(basename "$dir")
        bbdebug 1 "Processing firmware directory: ${fwdir}"

        # Copy all except 'lib/firmware/updates'
        socdir=$(echo "${fwdir/_fw/}" | tr '[:upper:]' '[:lower:]')
        install -d ${D}/${socdir}${FIRMWARE_UPDATES_DIR}
        [ -d "${WORKDIR}/$fwdir/lib/firmware" ] && \
        find "${WORKDIR}/$fwdir/lib/firmware" -mindepth 1 -maxdepth 1 -type d ! -name "updates" \
                -exec cp -r {} ${D}/${socdir}${FIRMWARE_UPDATES_DIR} \;

        # When present handle 'lib/firmware/updates' separately
        [ -d "${WORKDIR}/$fwdir/lib/firmware/updates" ] && \
        find "${WORKDIR}/$fwdir/lib/firmware/updates" -mindepth 1 -maxdepth 1 -type d \
                -exec cp -r {} ${D}/${socdir}${FIRMWARE_UPDATES_DIR} \;
    done
}

def hlosfw_update_packages(d, pkgs_list):
    import os
    import shutil

    pkgdest =  d.getVar('PKGDEST')
    hlosfw_update_pkgs = d.getVar(pkgs_list).split()

    for pkg in hlosfw_update_pkgs:
        soc = pkgs_list.split('_')[0].lower()
        soc_dir = '%s/%s/%s' % (pkgdest, pkg, soc)
        parent_dir = os.path.dirname(soc_dir)

        for item in os.listdir(soc_dir):
            src = os.path.join(soc_dir, item)
            dest = os.path.join(parent_dir, item)
            shutil.move(src, dest)

        os.rmdir(soc_dir)

        # TZ Apps are not really firmware. Retain them in 'lib/firmware'
        if pkg.endswith("qcom-tzapps-updates"):
            tza_updates_dir = '%s/%s/%s' % (pkgdest, pkg, d.getVar('FIRMWARE_UPDATES_DIR'))
            tza_parent_dir = os.path.dirname(tza_updates_dir)
            for item in os.listdir(tza_updates_dir):
                src = os.path.join(tza_updates_dir, item)
                dest = os.path.join(tza_parent_dir, item)
                shutil.move(src, dest)

            os.rmdir(tza_updates_dir)

python populate_packages:append() {
    hlosfw_update_packages(d, 'QCM6490_HLOSFW_UPDATE_PACKAGES')
    hlosfw_update_packages(d, 'QCS9100_HLOSFW_UPDATE_PACKAGES')
    hlosfw_update_packages(d, 'QCS8300_HLOSFW_UPDATE_PACKAGES')
    hlosfw_update_packages(d, 'QCS615_HLOSFW_UPDATE_PACKAGES')
}

QCM6490_HLOSFW_UPDATE_PACKAGES = " \
  ${PN}-ath11k-wcn6750-updates \
  ${PN}-qcacld-wcn6855-updates \
  ${PN}-qca-ramps-updates \
  ${PN}-qca-wcn6750-updates \
  ${PN}-qcm6490-ath11k-wcn6855-updates \
  ${PN}-qcm6490-qca-qca2066-hpbtfw-updates \
  ${PN}-qcm6490-qca-qca2066-updates \
  ${PN}-qcm6490-qca-qca61x4-usb-updates \
  ${PN}-qcm6490-qca-wcn685x-updates \
  ${PN}-qcm6490-qcom-tzapps-updates \
  ${PN}-qcom-qcm6490-audio-updates \
  ${PN}-qcom-qcm6490-compute-updates \
  ${PN}-qcom-qcm6490-qps615-updates \
  ${PN}-qcom-qcm6490-qupv3fw-updates \
  ${PN}-qcom-qcm6490-verinfo \
  ${PN}-qcom-qcm6490-wifi-updates \
  ${PN}-qcom-qcm6490-wlanhsp-updates \
  ${PN}-qcom-qcm6490-wlan-updates \
  ${PN}-qcom-qcm6490-ipafw-updates \
"

QCS9100_HLOSFW_UPDATE_PACKAGES = " \
  ${PN}-qcom-qcs9100-verinfo \
  ${PN}-qcom-sa8775p-audio-updates \
  ${PN}-qcom-sa8775p-compute-updates \
  ${PN}-qcom-sa8775p-generalpurpose-updates \
  ${PN}-qcom-sa8775p-qupv3fw-updates \
  ${PN}-qcs9100-ath11k-qca6698aq-updates \
  ${PN}-qcs9100-ath11k-wcn6855-updates \
  ${PN}-qcs9100-qca-qca2066-updates \
  ${PN}-qcs9100-qca-qca61x4-usb-updates \
  ${PN}-qcs9100-qca-qca6698-updates \
  ${PN}-qcs9100-qca-wcn685x-updates \
  ${PN}-qcs9100-qcom-tzapps-updates \
"

QCS8300_HLOSFW_UPDATE_PACKAGES = " \
  ${PN}-qcom-qcs8300-audio-updates \
  ${PN}-qcom-qcs8300-compute-updates \
  ${PN}-qcom-qcs8300-generalpurpose-updates \
  ${PN}-qcom-qcs8300-qupv3fw-updates \
  ${PN}-qcom-qcs8300-verinfo \
  ${PN}-qcs8300-ath11k-qca6698aq-updates \
  ${PN}-qcs8300-ath11k-wcn6855-updates \
  ${PN}-qcs8300-qca-qca2066-updates \
  ${PN}-qcs8300-qca-qca61x4-usb-updates \
  ${PN}-qcs8300-qca-qca6698-updates \
  ${PN}-qcs8300-qca-wcn685x-updates \
  ${PN}-qcs8300-qcom-tzapps-updates \
"

QCS615_HLOSFW_UPDATE_PACKAGES = " \
  ${PN}-qcom-qcs615-updates \
  ${PN}-qcom-qcs615-verinfo \
  ${PN}-qcs615-qcom-tzapps-updates \
"

PACKAGES:prepend = " \
  ${QCM6490_HLOSFW_UPDATE_PACKAGES} \
  ${QCS9100_HLOSFW_UPDATE_PACKAGES} \
  ${QCS8300_HLOSFW_UPDATE_PACKAGES} \
  ${QCS615_HLOSFW_UPDATE_PACKAGES} \
"

SUMMARY:${PN}-qcs9100-ath11k-qca6698aq-updates = "QCS9100 ATH11K QCA6698AQ firmware overlay"
FILES:${PN}-qcs9100-ath11k-qca6698aq-updates = "/qcs9100${FIRMWARE_UPDATES_DIR}/ath11k/QCA6698AQ"

SUMMARY:${PN}-qcs8300-ath11k-qca6698aq-updates = "QCS8300 ATH11K QCA6698AQ firmware overlay"
FILES:${PN}-qcs8300-ath11k-qca6698aq-updates = "/qcs8300${FIRMWARE_UPDATES_DIR}/ath11k/QCA6698AQ"

SUMMARY:${PN}-ath11k-wcn6750-updates = "ATH11K WCN6750 firmware overlay"
FILES:${PN}-ath11k-wcn6750-updates = "/qcm6490${FIRMWARE_UPDATES_DIR}/ath11k/WCN6750"

SUMMARY:${PN}-qcm6490-ath11k-wcn6855-updates = "QCM6490 ATH11K WCN6855 firmware overlay"
FILES:${PN}-qcm6490-ath11k-wcn6855-updates = "/qcm6490${FIRMWARE_UPDATES_DIR}/ath11k/WCN6855"

SUMMARY:${PN}-qcs9100-ath11k-wcn6855-updates = "QCS9100 ATH11K WCN6855 firmware overlay"
FILES:${PN}-qcs9100-ath11k-wcn6855-updates = "/qcs9100${FIRMWARE_UPDATES_DIR}/ath11k/WCN6855"

SUMMARY:${PN}-qcs8300-ath11k-wcn6855-updates = "QCS8300 ATH11K WCN6855 firmware overlay"
FILES:${PN}-qcs8300-ath11k-wcn6855-updates = "/qcs8300${FIRMWARE_UPDATES_DIR}/ath11k/WCN6855"

SUMMARY:${PN}-qcm6490-qca-qca2066-updates = "QCA2066 firmware overlay"
FILES:${PN}-qcm6490-qca-qca2066-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpbtfw21.tlv \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21.bin \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.bin \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21.3* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.3* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv21_LPO* \
"

SUMMARY:${PN}-qcm6490-qca-qca2066-hpbtfw-updates = "QCA firmware update files"
FILES:${PN}-qcm6490-qca-qca2066-hpbtfw-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpbtfw21* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/msbtfw11* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpbtfw20* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/hpnv20* \
"

SUMMARY:${PN}-qcs9100-qca-qca2066-updates = "QCA2066 firmware overlay"
FILES:${PN}-qcs9100-qca-qca2066-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpbtfw21.tlv \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21.3* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.3* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21_* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/hpnv21g_* \
"

SUMMARY:${PN}-qcs8300-qca-qca2066-updates = "QCA2066 firmware overlay"
FILES:${PN}-qcs8300-qca-qca2066-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpbtfw21.tlv \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21.3* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.3* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21g.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21_* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/hpnv21g_* \
"

SUMMARY:${PN}-qcs9100-qca-qca6698-updates = "QCA6698 firmware update files"
FILES:${PN}-qcs9100-qca-qca6698-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpbtfw21.tlv* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21.b* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21g.b* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_AA_losslessg.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_48K.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_48Kg.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_LPOg.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_LPO.bin \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_AA_lossless.bin \
"

SUMMARY:${PN}-qcs8300-qca-qca6698-updates = "QCA6698 firmware update files"
FILES:${PN}-qcs8300-qca-qca6698-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpbtfw21.tlv* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21g.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_AA_losslessg.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_48K.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_48Kg.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_LPOg.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_LPO.bin \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/hpnv21_AA_lossless.bin \
"

SUMMARY:${PN}-qca-wcn6750-updates = "QCA WCN6750 firmware update files"
FILES:${PN}-qca-wcn6750-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/msbtfw11.mbn* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/msbtfw11.tlv* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/msnv11.bin* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/msnv11.b0a* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/msnv11.b09* \
"

SUMMARY:${PN}-qcm6490-qca-qca61x4-usb-updates = "QCM6490 QCA QCA61x4 USB firmware update files"
FILES:${PN}-qcm6490-qca-qca61x4-usb-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/nvm_usb_*.bin* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/rampatch_usb_*.bin* \
"

SUMMARY:${PN}-qcs9100-qca-qca61x4-usb-updates = "QCS9100 QCA QCA61x4 USB firmware update files"
FILES:${PN}-qcs9100-qca-qca61x4-usb-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/nvm_usb_*.bin* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/rampatch_usb_*.bin* \
"

SUMMARY:${PN}-qcs8300-qca-qca61x4-usb-updates = "QCS8300 QCA QCA61x4 USB firmware update files"
FILES:${PN}-qcs8300-qca-qca61x4-usb-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/nvm_usb_*.bin* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/rampatch_usb_*.bin* \
"

SUMMARY:${PN}-qcm6490-qca-wcn685x-updates = "QCM6490 QCA wcn685x firmware update files"
FILES:${PN}-qcm6490-qca-wcn685x-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/wcnhpbtfw21.tlv* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/wcnhpbtfw21g* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/wcnhpnv21.b* \
  qcm6490${FIRMWARE_UPDATES_DIR}/qca/wcnhpnv21* \
"
SUMMARY:${PN}-qcs9100-qca-wcn685x-updates = "QCM6490 QCA wcn685x firmware update files"
FILES:${PN}-qcs9100-qca-wcn685x-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpbtfw21.tlv* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpnv21.b* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpnv21g.* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpnv21* \
"

SUMMARY:${PN}-qcs8300-qca-wcn685x-updates = "QCM6490 QCA wcn685x firmware update files"
FILES:${PN}-qcs8300-qca-wcn685x-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpbtfw21.tlv* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpnv21.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpnv21g.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qca/QCA6698/wcnhpnv21* \
"

SUMMARY:${PN}-qcom-qcm6490-audio-updates = "QCOM qcom-qcm6490-audio firmware overlay"
FILES:${PN}-qcom-qcm6490-audio-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/adsp*.* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/battmgr.jsn \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qcs6490/QCS6490-RB3Gen2-tplg.bin* \
"

SUMMARY:${PN}-qcom-qcm6490-compute-updates = "QCOM qcom-qcm6490-compute firmware overlay"
FILES:${PN}-qcom-qcm6490-compute-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/cdsp*.* \
"

SUMMARY:${PN}-qcom-qcm6490-qupv3fw-updates = "QCOM qcom-qcm6490-qupv3fw firmware overlay"
FILES:${PN}-qcom-qcm6490-qupv3fw-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/qupv3fw.elf* \
"

SUMMARY:${PN}-qcom-qcm6490-wifi-updates = "QCM6490 QCOM wifi firmware overlay"
FILES:${PN}-qcom-qcm6490-wifi-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/wpss.b* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/wpss.mdt \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/wpss.mbn \
"

SUMMARY:${PN}-qcom-qcm6490-ipafw-updates = "QCM6490 QCOM IPA firmware overlay"
FILES:${PN}-qcom-qcm6490-ipafw-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/ipa_fws.b* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/ipa_fws.mdt \
"

SUMMARY:${PN}-qcom-qcs8300-audio-updates = "QCOM qcom-qcs8300-audio firmware overlay"
FILES:${PN}-qcom-qcs8300-audio-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/adsp*.* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/MONACO-EVK-tplg.bin* \
"

SUMMARY:${PN}-qcom-qcs8300-compute-updates = "QCOM qcom-qcs8300-compute firmware overlay"
FILES:${PN}-qcom-qcs8300-compute-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/cdsp*.* \
"

SUMMARY:${PN}-qcom-qcs8300-generalpurpose-updates = "QCOM qcom-qcs8300-generalpurpose firmware overlay"
FILES:${PN}-qcom-qcs8300-generalpurpose-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/gpdsp*.* \
"

SUMMARY:${PN}-qcom-qcs8300-qupv3fw-updates = "QCOM qcom-qcs8300-qupv3fw firmware overlay"
FILES:${PN}-qcom-qcs8300-qupv3fw-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/qupv3fw.elf* \
"

SUMMARY:${PN}-qcom-sa8775p-audio-updates = "QCOM qcom-sa8775p-audio firmware overlay"
FILES:${PN}-qcom-sa8775p-audio-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/adsp*.* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/qcs9100/LEMANS-EVK-tplg.bin* \
"

SUMMARY:${PN}-qcom-sa8775p-compute-updates = "QCOM qcom-sa8775p-compute firmware overlay"
FILES:${PN}-qcom-sa8775p-compute-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/cdsp*.* \
"

SUMMARY:${PN}-qcom-sa8775p-generalpurpose-updates = "QCOM qcom-sa8775p-generalpurpose firmware overlay"
FILES:${PN}-qcom-sa8775p-generalpurpose-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/gpdsp*.* \
"

SUMMARY:${PN}-qcom-sa8775p-qupv3fw-updates = "QCOM qcom-sa8775p-qupv3fw firmware overlay"
FILES:${PN}-qcom-sa8775p-qupv3fw-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/qupv3fw.elf* \
"

SUMMARY:${PN}-qcacld-wcn6855-updates = "QCACLD WCN6855 firmware update files"
FILES:${PN}-qcacld-wcn6855-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcacld/WCN6855/* \
"

SUMMARY:${PN}-qcom-qcm6490-wlan-updates = "QCOM WLAN QCM6490 firmware update files"
FILES:${PN}-qcom-qcm6490-wlan-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/bdwlan* \
"

SUMMARY:${PN}-qcm6490-qcom-tzapps-updates = "QCM6490 QCOM TZ application files"
FILES:${PN}-qcm6490-qcom-tzapps-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/FD02C9DA* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/loadalgota64.b* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/loadalgota64.mdt \
"


SUMMARY:${PN}-qcs9100-qcom-tzapps-updates = "QCS9100 QCOM TZ application files"
FILES:${PN}-qcs9100-qcom-tzapps-updates = " \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/FD02C9DA* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/loadalgota64.b* \
  /qcs9100${FIRMWARE_UPDATES_DIR}/qcom/sa8775p/loadalgota64.mdt \
"

SUMMARY:${PN}-qcs8300-qcom-tzapps-updates = "QCS8300 QCOM TZ application files"
FILES:${PN}-qcs8300-qcom-tzapps-updates = " \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/FD02C9DA* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/loadalgota64.b* \
  /qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/loadalgota64.mdt \
"

SUMMARY:${PN}-qcs615-qcom-tzapps-updates = "QCS615 QCOM TZ application files"
FILES:${PN}-qcs615-qcom-tzapps-updates = " \
  /qcs615${FIRMWARE_UPDATES_DIR}/qcom/qcs615/loadalgota64.b* \
  /qcs615${FIRMWARE_UPDATES_DIR}/qcom/qcs615/loadalgota64.mdt \
"

SUMMARY:${PN}-qcom-qcm6490-wlanhsp-updates = "QCOM QCM6490 Data firmware update files"
FILES:${PN}-qcom-qcm6490-wlanhsp-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/Data* \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/regdb.* \
"

SUMMARY:${PN}-qcom-qcs615-updates = "QCOM QCS615 firmware update files"
FILES:${PN}-qcom-qcs615-updates = " \
  /qcs615${FIRMWARE_UPDATES_DIR}/qcom/qcs615/adsp* \
  /qcs615${FIRMWARE_UPDATES_DIR}/qcom/qcs615/cdsp* \
"

SUMMARY:${PN}-qcom-qcm6490-qps615-updates = "QCOM QCM6490 QPS615 firmware update files"
FILES:${PN}-qcom-qcm6490-qps615-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qps615.bin \
"

SUMMARY:${PN}-qca-ramps-updates = "QCOM QCA ramps firmware update files"
FILES:${PN}-qca-ramps-updates = " \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/ramps_0x00130201_030a.dfu \
  /qcm6490${FIRMWARE_UPDATES_DIR}/qca/ramps_0x00130201_gf_030a.dfu \
"

SUMMARY:${PN}-qcom-qcm6490-verinfo = "QCM6490 HLOS Ver_Info.txt convenience link in overlay"
FILES:${PN}-qcom-qcm6490-verinfo = "/qcm6490${FIRMWARE_UPDATES_DIR}/qcom/qc[ms]6490/Ver_Info.txt"

SUMMARY:${PN}-qcom-qcs9100-verinfo = "QCS9100 HLOS Ver_Info.txt convenience link in overlay"
FILES:${PN}-qcom-qcs9100-verinfo = "/qcs9100${FIRMWARE_UPDATES_DIR}/qcom/qcs9100/Ver_Info.txt"

SUMMARY:${PN}-qcom-qcs8300-verinfo = "QCS8300 HLOS Ver_Info.txt convenience link in overlay"
FILES:${PN}-qcom-qcs8300-verinfo = "/qcs8300${FIRMWARE_UPDATES_DIR}/qcom/qcs8300/Ver_Info.txt"

SUMMARY:${PN}-qcom-qcs615-verinfo = "QCS615 HLOS Ver_Info.txt convenience link in overlay"
FILES:${PN}-qcom-qcs615-verinfo = "/qcs615${FIRMWARE_UPDATES_DIR}/qcom/qcs615/Ver_Info.txt"
