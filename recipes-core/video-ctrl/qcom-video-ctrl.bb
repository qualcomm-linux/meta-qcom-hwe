inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QCOM library for smart video codec control logic."

DEPENDS += "qcom-fastcv-binaries glib-2.0"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "bba83222ac2d266abf11ab864c1d9f7bc010e51cb690135b9b27577b161526c5"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += "${bindir}"
FILES:${PN} += "${libdir}"
FILES:${PN} += "${libdir}/pkgconfig"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
