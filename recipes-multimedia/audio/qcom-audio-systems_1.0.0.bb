inherit qprebuilt pkgconfig

LICENSE = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "audio-systems"

SECTION = "multimedia"

DEPENDS += "qcom-sva-eai qcom-capiv2-headers"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "013d314e2bf1e4e37cc0a8d2300171ca103090a1a4fcd78628d66a84d83badcb"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
