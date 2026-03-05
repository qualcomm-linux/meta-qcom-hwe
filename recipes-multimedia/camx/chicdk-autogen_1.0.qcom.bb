inherit cmake pkgconfig qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Chicdk Autogen"

DEPENDS:qcom-custom-bsp += "camxapi camxcommon protobuf-native protobuf"

QCS9100_SHA256SUM = "77ed48a65bf155f5daf64411fbea54617bb5f7e3e4e7e98e21d6101880e776da"
QCS8300_SHA256SUM = "fd295ca4a4bcc2cbdd251c181628e6f66526843bd7c181013c30b37b4f8143ed"
QCS615_SHA256SUM = "6e15c63f780be88ba548886210d2687b53f0a472a609fb5bc519ac67a492f3b0"

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
