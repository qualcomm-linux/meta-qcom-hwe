SUMMARY = "Firmware packages for the qcs615 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:append:qcom-base-bsp = " \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-qca-qca6698 \
    linux-firmware-qcom-adreno-a630 \
    linux-firmware-qcom-qcs615-adreno \
    linux-firmware-qcom-venus-5.4 \
    linux-firmware-ath10k-qca9377 \
"
