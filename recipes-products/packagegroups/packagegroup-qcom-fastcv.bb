SUMMARY = "QCOM fastCV Proprietary Package Group"

LICENSE = "LICENSE.qcom-2"
PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:qcom-custom-bsp = " \
    qcom-fastcv-binaries \
    "
