SUMMARY = "Firmware packages for the qcm6490 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:qcom-base-bsp = " \
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
"
