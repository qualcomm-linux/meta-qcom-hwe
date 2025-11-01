inherit qprebuilt native

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Chicdk Autogen"

# Required for native build
DEPENDS:class-native += "libxml-simple-perl-native"

QCS9100_SHA256SUM = "8b1c68edc69552edee8dde65b0a55178c88bb3e1735628645ce1f34495b5e1cc"
QCS8300_SHA256SUM = "9671e70905038c82a49cb1fec60002dd400b84c4cf0d417922af7c369964b0ae"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

