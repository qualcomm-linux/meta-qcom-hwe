SUMMARY = "Firmware packages for the qcs8300 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    hexagon-dsp-binaries-qcom-qcs8300-ride-adsp \
    hexagon-dsp-binaries-qcom-qcs8300-ride-cdsp \
    hexagon-dsp-binaries-qcom-qcs8300-ride-gdsp \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-qca-qca2066 \
    linux-firmware-qca-qca61x4-usb \
    linux-firmware-qca-qca6698 \
    linux-firmware-qca-wcn685x \
    linux-firmware-qcom-adreno-a623 \
    linux-firmware-qcom-adreno-a650 \
    linux-firmware-qcom-qcs8300-adreno \
    linux-firmware-qcom-qcs8300-audio \
    linux-firmware-qcom-qcs8300-compute \
    linux-firmware-qcom-qcs8300-generalpurpose \
    linux-firmware-qcom-qcs8300-qupv3fw \
    linux-firmware-qcom-vpu \
    firmware-qcom-verinfo \
"

# Additional pkgs for custom-bsp builds
RRECOMMENDS:${PN}:append:qcom-custom-bsp = " \
    hexagon-dsp-binaries-qcs8300-updates \
    linux-firmware-qcom-qcs8300-audio-updates \
    linux-firmware-qcom-qcs8300-compute-updates \
    linux-firmware-qcom-qcs8300-generalpurpose-updates \
    linux-firmware-qcom-qcs8300-verinfo \
    linux-firmware-qcs8300-ath11k-wcn6855-updates \
    linux-firmware-qcs8300-qcom-tzapps-updates \
"
