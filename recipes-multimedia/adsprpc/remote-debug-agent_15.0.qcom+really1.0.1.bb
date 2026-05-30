inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "To create remote debug agent for LE"

DEPENDS += "glib-2.0"

PBT_ARCH = "aarch64"

AARCH64_SHA256SUM = "2cc22cd5097aa5f3bc8c0858919c63170d27add646caab8aa4bc0aff3c5ad851"
SRC_URI[aarch64.sha256sum] = "${AARCH64_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}      = "${bindir}/*"

PACKAGECONFIG ?= "glib"
PACKAGECONFIG[glib] = "--with-glib, --without-glib, glib-2.0"
