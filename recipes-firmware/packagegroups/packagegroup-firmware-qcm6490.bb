SUMMARY = "Firmware packages for the qcm6490 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    hexagon-dsp-binaries-thundercomm-rb3gen2-adsp \
    hexagon-dsp-binaries-thundercomm-rb3gen2-cdsp \
    linux-firmware-ath11k-wcn6750 \
    linux-firmware-qca-wcn6750 \
    linux-firmware-qcom-adreno-a660 \
    linux-firmware-qcom-qcm6490-adreno \
    linux-firmware-qcom-qcm6490-audio \
    linux-firmware-qcom-qcm6490-compute \
    linux-firmware-qcom-qcm6490-qupv3fw \
    linux-firmware-qcom-qcm6490-wifi \
    linux-firmware-qcom-vpu \
    firmware-qcom-verinfo \
"

# Additional pkgs for custom-bsp builds
RRECOMMENDS:${PN}:append:qcom-custom-bsp = " \
    hexagon-dsp-binaries-qcm6490-updates \
    linux-firmware-ath11k-wcn6750-updates \
    linux-firmware-qcacld-wcn6855-updates \
    linux-firmware-qca-ramps-updates \
    linux-firmware-qca-wcn6750-updates \
    linux-firmware-qcm6490-ath11k-wcn6855-updates \
    linux-firmware-qcm6490-qca-qca2066-hpbtfw-updates \
    linux-firmware-qcm6490-qca-qca2066-updates \
    linux-firmware-qcm6490-qca-qca61x4-usb-updates \
    linux-firmware-qcm6490-qcom-tzapps-updates \
    linux-firmware-qcom-qcm6490-audio-updates \
    linux-firmware-qcom-qcm6490-compute-updates \
    linux-firmware-qcom-qcm6490-qps615-updates \
    linux-firmware-qcom-qcm6490-qupv3fw-updates \
    linux-firmware-qcom-qcm6490-verinfo \
    linux-firmware-qcom-qcm6490-wifi-updates \
    linux-firmware-qcom-qcm6490-wlanhsp-updates \
    linux-firmware-qcom-qcm6490-wlan-updates \
"