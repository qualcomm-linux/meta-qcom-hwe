inherit cmake pkgconfig qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Chicdk Autogen"

DEPENDS:qcom-custom-bsp += "camxapi camxcommon protobuf-native protobuf"

QCS9100_SHA256SUM = "8b1c68edc69552edee8dde65b0a55178c88bb3e1735628645ce1f34495b5e1cc"
QCS8300_SHA256SUM = "9671e70905038c82a49cb1fec60002dd400b84c4cf0d417922af7c369964b0ae"
QCS615_SHA256SUM = "f826b4811277df405883034d02e0e49e48038881a4e6cad189d7cd756884c017"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

do_package_qa[noexec] = "1"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /lib/firmware/*"

FILES:${PN}-dev = "/usr/include/*"
INSANE_SKIP = "1"
#Skips check for .so symlinks
INSANE_SKIP:${PN} = "dev-so"
