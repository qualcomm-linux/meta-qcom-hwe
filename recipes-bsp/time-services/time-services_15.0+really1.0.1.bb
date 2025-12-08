inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"


QCM6490_SHA256SUM = "28d3f2101c2e2277796b58a0e6092eb9c8bcdd264eb664f69f22fb99d4be5409"
QCS9100_SHA256SUM = "13b8474579e302d0549e99b532bd72941408d8caed100fea3826aac824d9d1d7"
QCS8300_SHA256SUM = "dadb29430883a26e3718d33d7489af48f9bf37a636728d86d6c40572438ed378"
QCS615_SHA256SUM = "740712921f3a605099fa86b4dffc356a9909f4c43136f90cfc21e05a2e3f5911"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

