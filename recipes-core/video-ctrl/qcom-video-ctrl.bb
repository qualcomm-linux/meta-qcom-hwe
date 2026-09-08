inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "QCOM library for smart video codec control logic."

DEPENDS += "qcom-fastcv-binaries glib-2.0"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "eb51a3c0a3885d35054cd46f0d7e3c074d116a45e3a0a101895e1125377f18a1"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += "${bindir}"
FILES:${PN} += "${libdir}"
FILES:${PN} += "${libdir}/pkgconfig"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
