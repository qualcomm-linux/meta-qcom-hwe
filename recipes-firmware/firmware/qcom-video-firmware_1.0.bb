inherit qprebuilt

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "fab3527f57b605d63a1deff42b5a56a0f52f2da55f6112830e9347db961acdc8"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${nonarch_base_libdir}/firmware"


INSANE_SKIP:${PN} = "arch"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
