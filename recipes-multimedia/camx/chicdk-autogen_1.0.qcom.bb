inherit cmake pkgconfig qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Chicdk Autogen"

DEPENDS:qcom-custom-bsp += "camxapi camxcommon protobuf-native protobuf"

QCS9100_SHA256SUM = "ecd1d895a922c0f166fe68afebc2d3fc2e0f776291d3d937a598cadeb2618f61"
QCS8300_SHA256SUM = "372df7d8c97b026a503531d90cc857144a991d270357fd04215e1b224d0c251a"
QCS615_SHA256SUM = "802086e93e2e0513409c52ca9cb885c275eeb5fc0bb9565e8b54260e1c4449f3"

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
