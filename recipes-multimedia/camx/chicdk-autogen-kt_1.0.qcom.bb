inherit cmake pkgconfig qprebuilt

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Chicdk Autogen (KT variant)"

# If KT depends on KT stack, use -kt deps here; adjust as required
DEPENDS:qcom-custom-bsp += "camxapi-kt protobuf-native protobuf"

# Use the actual checksums for KT artifacts
QCM6490_SHA256SUM = "fc9f0d5f12db23677177c8034b36e0d0d12d948d4d9b7bcfb92cb7d959667f72"
QCS9100_SHA256SUM = "09ed50ff48bbd0168a427fdc08166b535ae546272877a5967b1de4e049c9af7b"
QCS8300_SHA256SUM = "96421945a0f450d70d6e4d4fe5c883681debe408a527aa316849932597bbb3f2"
QCS615_SHA256SUM  = "a2eff5f72da84b7f3ddb33bc6b256a8c294beec581b8f6f329adc619f7992f2f"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum]  = "${QCS615_SHA256SUM}"

# Point to the KT tarball/artifact path
SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

do_package_qa[noexec] = "1"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /lib/firmware/* \
"
FILES:${PN}-dev = "/usr/include/*"

INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"
