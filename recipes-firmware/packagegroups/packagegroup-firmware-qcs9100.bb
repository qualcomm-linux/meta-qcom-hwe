SUMMARY = "Firmware packages for the qcs9100 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    hexagon-dsp-binaries-qcom-sa8775p-ride-adsp \
    hexagon-dsp-binaries-qcom-sa8775p-ride-cdsp \
    hexagon-dsp-binaries-qcom-sa8775p-ride-gdsp \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-ath12k-wcn7850 \
    linux-firmware-qca-qca2066 \
    linux-firmware-qca-qca61x4-usb \
    linux-firmware-qca-qca6698 \
    linux-firmware-qca-wcn685x \
    linux-firmware-qcom-adreno-a660 \
    linux-firmware-qcom-adreno-a663 \
    linux-firmware-qcom-sa8775p-adreno \
    linux-firmware-qcom-sa8775p-audio \
    linux-firmware-qcom-sa8775p-compute \
    linux-firmware-qcom-sa8775p-generalpurpose \
    linux-firmware-qcom-sa8775p-qupv3fw \
    linux-firmware-qcom-vpu \
    firmware-qcom-verinfo \
"

# Additional pkgs for custom-bsp builds
RRECOMMENDS:${PN}:append:qcom-custom-bsp = " \
    hexagon-dsp-binaries-qcs9100-updates \
    linux-firmware-qcom-qcs9100-verinfo \
    linux-firmware-qcom-sa8775p-audio-updates \
    linux-firmware-qcom-sa8775p-compute-updates \
    linux-firmware-qcom-sa8775p-generalpurpose-updates \
    linux-firmware-qcs9100-ath11k-wcn6855-updates \
    linux-firmware-qcs9100-qca-qca2066-updates \
    linux-firmware-qcs9100-qca-qca61x4-usb-updates \
    linux-firmware-qcs9100-qcom-tzapps-updates \
    linux-firmware-qcs9100-qca-wcn685x-updates \
"
