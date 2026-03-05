inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "52f3dd414532f5b5ff741bd7265b8766a3767e212973343f2e164b5eb42cb42c"
QCS9100_SHA256SUM = "ea9b5817f607af3f35c2fc4b1d892fda84dfc814c68ddcdcbb4d53d49b41e1ba"
QCS8300_SHA256SUM = "3b08cb5f1b25dc22bd49515eb364a21a292fe5333b0e131a30853eebb869b025"
QCS615_SHA256SUM = "5c5565ae223730d99647effaf611676a76573fc96e9205453737147f1d41d0f2"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

