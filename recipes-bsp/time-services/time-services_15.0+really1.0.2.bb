inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"

QCM6490_SHA256SUM = "0a3de28fe2555ef5c8147c9824f5e693a88c37b6a46cf21cdb9e9626329f4844"
QCS9100_SHA256SUM = "d7d7d47b0a893449c7cf9cda3f4307c0bf9e618b30922bcaf22991f323aba94e"
QCS8300_SHA256SUM = "6f57918e5e81837097f8487914068971b6a8a088c496b187a91ff1cdaf95ac63"
QCS615_SHA256SUM = "6ba9e3c7ae3ce103aba042e6d3670b40e99cdf6f29ed46e567db713dac273d7c"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

