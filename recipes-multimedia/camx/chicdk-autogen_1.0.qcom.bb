inherit cmake pkgconfig qprebuilt

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Chicdk Autogen"

DEPENDS:qcom-custom-bsp += "camxapi camxcommon protobuf-native protobuf"

QCS9100_SHA256SUM = "17851e16e1ef8ce1b1d55e291716d01bb9ad2589bd08a123ce8674c27aab4542"
QCS8300_SHA256SUM = "be3c16bdd445646e88245dfbefeb3a5da6981654b2a63f0e4da1ca49c5833bc6"
QCS615_SHA256SUM = "8f9f38dbc643a4ad278ab5396d1a9daa91fdda86d21e5a3e2354c8a3108536ed"

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
