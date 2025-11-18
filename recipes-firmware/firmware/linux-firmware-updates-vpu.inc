# To include downstream VPU Firmware
VPU_ARTIFACTORY = "https://softwarecenter.qualcomm.com/download/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
VPU_BUILD_ID    = "r1.0_00106.0"
VPU_BIN_PATH    = "le-qclinux-1-0-r1/apps_proc/prebuilt_HY22/qcom-video-firmware/251008"
VIDEO_FW_ARTIFACTORY = "${VPU_ARTIFACTORY}/${VPU_BUILD_ID}/${VPU_BIN_PATH}"

VPU_SHA256SUM = "b39210d378f2acd387d132e84fb26d34832eaab4ef0bd66fda8bfa9f1a395f7c"
SRC_URI[vpu.sha256sum] = "${VPU_SHA256SUM}"
SRC_URI += "${VIDEO_FW_ARTIFACTORY}/qcom-video-firmware_1.0_armv8-2a.tar.gz;name=vpu;subdir=vpu"

do_install:append:qcom (){
    install -d ${D}${FIRMWARE_UPDATES_DIR}/qcom/vpu-2.0/
    install -m 0644 ${WORKDIR}/vpu/${nonarch_base_libdir}/firmware/qcom/vpu-2.0/* \
                   ${D}${FIRMWARE_UPDATES_DIR}/qcom/vpu-2.0/
    install -d ${D}${FIRMWARE_UPDATES_DIR}/qcom/vpu-3.0/
    install -m 0644 ${WORKDIR}/vpu/${nonarch_base_libdir}/firmware/qcom/vpu-3.0/* \
                   ${D}${FIRMWARE_UPDATES_DIR}/qcom/vpu-3.0/
}

PACKAGES:prepend = " \
    ${PN}-qcom-vpu-updates \
"

SUMMARY:${PN}-qcom-vpu-updates = "QCOM VPU firmware overlay"
FILES:${PN}-qcom-vpu-updates = " \
    ${FIRMWARE_UPDATES_DIR}/qcom/vpu* \
"
