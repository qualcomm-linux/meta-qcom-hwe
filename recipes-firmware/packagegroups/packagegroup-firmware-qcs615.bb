SUMMARY = "Firmware packages for the qcs615 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    hexagon-dsp-binaries-qcom-qcs615-ride-adsp \
    hexagon-dsp-binaries-qcom-qcs615-ride-cdsp \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-qca-qca6698 \
    linux-firmware-qcom-adreno-a630 \
    linux-firmware-qcom-qcs615-adreno \
    linux-firmware-qcom-qcs615-audio \
    linux-firmware-qcom-qcs615-compute \
    linux-firmware-qcom-venus-5.4 \
    linux-firmware-ath10k-qca9377 \
    firmware-qcom-verinfo \
"
# conflicting pkgs between base and custom bsp.
RRECOMMENDS:${PN}:remove:qcom-custom-bsp = "linux-firmware-qcom-venus-5.4"

# Additional pkgs for custom-bsp builds
RRECOMMENDS:${PN}:append:qcom-custom-bsp = " \
    hexagon-dsp-binaries-qcs615-updates \
    linux-firmware-qcom-qcs615-verinfo \
"
