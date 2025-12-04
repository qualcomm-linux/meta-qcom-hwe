# To include downstream VPU Firmware.
VPU_BUILD_ID ?= "r1.0_00108.0/le-qclinux-1-0-r1"
VPU_BIN_PATH ?= "apps_proc/prebuilt_HY22/qcom-video-firmware/251008"
VPUFW_ARTIFACTORY ?= "https://${FW_ARTIFACTORY}/${VPU_BUILD_ID}/${VPU_BIN_PATH}"

VPU_SHA256SUM ?= "b39210d378f2acd387d132e84fb26d34832eaab4ef0bd66fda8bfa9f1a395f7c"
SRC_URI[vpu.sha256sum] = "${VPU_SHA256SUM}"
SRC_URI += "${VPUFW_ARTIFACTORY}/qcom-video-firmware_1.0_armv8-2a.tar.gz;name=vpu;subdir=vpu"

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
