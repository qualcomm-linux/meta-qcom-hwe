inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"


QCM6490_SHA256SUM = "5c4dfc4560a9dddb24f2b6b0a5762937d80d5d687a8bedd22d3cb1fe65e0fe4d"
QCS9100_SHA256SUM = "661fe13c90ff6019c018164cd89ff313ac5bd7bf99a59fb9550d49dd6cf25c7f"
QCS8300_SHA256SUM = "54599ec4abaa7e71f6ddf53c17a7e986717c1fdb550bc9e3295a55e0935cba0f"
QCS615_SHA256SUM = "dfa9084b9ecf9beb3031c0563f7071eed992b641d7576999121ebf32274193b6"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

