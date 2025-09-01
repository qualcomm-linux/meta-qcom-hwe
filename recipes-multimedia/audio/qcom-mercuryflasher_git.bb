inherit autotools pkgconfig qprebuilt

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Mercury flasher"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "1dee7c50957a149398a2c93985d07a4f0dd95d391f6f8ca25ba91127e15f5be6"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
