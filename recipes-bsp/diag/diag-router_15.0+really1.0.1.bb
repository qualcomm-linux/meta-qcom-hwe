inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "8d5d063513d3bc448e50bccb43dbeeea72a3a0038282007efac83b6574589861"
QCS9100_SHA256SUM = "8e2a12f706f5ba31b95dea9eaaf1907098469cbbf4d80792e9afd8e721cb91a9"
QCS8300_SHA256SUM = "e47a8b051eb45110d10214da70556b97185a5314dca52b8aadc9fc6f80560215"
QCS615_SHA256SUM = "bf59e3e1496dec8b1596104a791b6a726265be1deac7c08e124fb7af918e66d9"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

