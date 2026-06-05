SUMMARY = "QCOM Video proprietary package groups"
LICENSE = "LICENSE.qcom-2"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    qcom-video-firmware \
    qcom-videodlkm \
"
