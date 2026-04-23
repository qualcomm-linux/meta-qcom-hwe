SUMMARY = "IOT Base Utilities Packagegroup"
LICENSE = "LICENSE.qcom-2"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qcom-iot-base-utils \
    "

RDEPENDS:packagegroup-qcom-iot-base-utils:qcom-custom-bsp = " \
      qcom-video-ctrl \
    "
