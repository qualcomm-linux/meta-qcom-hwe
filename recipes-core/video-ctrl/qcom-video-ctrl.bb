inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QCOM library for smart video codec control logic."

DEPENDS += "qcom-fastcv-binaries glib-2.0"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "5e5d74f440b8f7bc6c6a9b3059a561321a35d5d056c9f48eda02bf2e74215ec0"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += "${bindir}"
FILES:${PN} += "${libdir}"
FILES:${PN} += "${libdir}/pkgconfig"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
