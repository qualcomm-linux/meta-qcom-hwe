# To include downstream GPU Firmware.
GPU_COMPONENT = "component/gfx-adreno.le.0.0"
GPUFW_DATE = "250908/prebuilt_yocto"
SRC_URI[adreno.sha256sum] = "02974d294597994a255f7aca6d6d61422fb44f11bd4cf6064e183c3888ea9def"
SRC_URI += "${FW_ARTIFACTORY}/${GPU_COMPONENT}/${GPUFW_DATE}/qcom-adreno_1.838.1_armv8-2a.tar.gz;name=adreno;subdir=adreno"

do_install:append:qcom (){
    install -d ${D}${FIRMWARE_UPDATES_DIR}
    install -m 0644 ${WORKDIR}/adreno/lib/firmware/* ${D}${FIRMWARE_UPDATES_DIR}
}

PACKAGES:prepend = " \
    ${PN}-qcom-adreno-a630-updates \
    ${PN}-qcom-adreno-a660-updates \
    ${PN}-qcom-adreno-a663-updates \
    ${PN}-qcom-adreno-a623-updates \
    ${PN}-qcom-adreno-a612-updates \
    ${PN}-qcom-adreno-a740-updates \
    ${PN}-qcom-adreno-a650-updates \
    ${PN}-qcom-adreno-gen70900-updates \
    ${PN}-qcom-qcs615-adreno-updates \
"

SUMMARY:${PN}-qcom-adreno-a612-updates = "QCOM Adreno A612 GPU firmware updates"
FILES:${PN}-qcom-adreno-a612-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a612* \
"

SUMMARY:${PN}-qcom-adreno-a623-updates = "QCOM Adreno A623 GPU firmware updates"
FILES:${PN}-qcom-adreno-a623-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a623* \
"

SUMMARY:${PN}-qcom-adreno-a630-updates = "QCOM Adreno A630 GPU firmware updates"
FILES:${PN}-qcom-adreno-a630-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a630* \
    ${FIRMWARE_UPDATES_DIR}/qcom/a630* \
"

SUMMARY:${PN}-qcom-adreno-a650-updates = "QCOM Adreno A650 GPU firmware updates"
FILES:${PN}-qcom-adreno-a650-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a650* \
"

SUMMARY:${PN}-qcom-adreno-a660-updates = "QCOM Adreno A660 GPU firmware updates"
FILES:${PN}-qcom-adreno-a660-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a660* \
"

SUMMARY:${PN}-qcom-adreno-a663-updates = "QCOM Adreno A663 GPU firmware updates"
FILES:${PN}-qcom-adreno-a663-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a663* \
"

SUMMARY:${PN}-qcom-adreno-a740-updates = "QCOM Adreno A740 GPU firmware updates"
FILES:${PN}-qcom-adreno-a740-updates = " \
    ${FIRMWARE_UPDATES_DIR}/a740* \
    ${FIRMWARE_UPDATES_DIR}/gmu_gen70200.bin \
"

SUMMARY:${PN}-qcom-adreno-gen70900-updates = "QCOM Adreno Gen70900 GPU firmware updates"
FILES:${PN}-qcom-adreno-gen70900-updates = " \
    ${FIRMWARE_UPDATES_DIR}/gen70900* \
    ${FIRMWARE_UPDATES_DIR}/gmu_gen70900.bin \
"

SUMMARY:${PN}-qcom-qcs615-adreno-updates = "QCOM qcom-qcs615-adreno firmware overlay"
FILES:${PN}-qcom-qcs615-adreno-updates = " \
    ${FIRMWARE_UPDATES_DIR}/qcom/qcs615/a612_zap.mbn* \
"
