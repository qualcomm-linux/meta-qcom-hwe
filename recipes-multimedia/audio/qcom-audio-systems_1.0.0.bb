inherit qprebuilt pkgconfig

LICENSE = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "audio-systems"

SECTION = "multimedia"

DEPENDS += "qcom-sva-eai qcom-capiv2-headers"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "d610c84dc558c74142dd9feebeaed08e06d8a652c3a41182c3c9c090690be644"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
