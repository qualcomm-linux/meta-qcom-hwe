inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QCOM library for smart video codec control logic."

DEPENDS += "qcom-fastcv-binaries glib-2.0"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "ea76410167b812da03c12c6a87955f468dcca5a58cacbf0346e420f2646a118c"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += "${bindir}"
FILES:${PN} += "${libdir}"
FILES:${PN} += "${libdir}/pkgconfig"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
