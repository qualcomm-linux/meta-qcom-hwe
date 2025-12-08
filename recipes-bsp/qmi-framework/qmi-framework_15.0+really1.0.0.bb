inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QMI framework will provide sample applications to test QMI communication between Apps and other remote sub systems. It also includes multiple services to support the QMI communications. qrtr-ns service should be running in background to support QMI communication because qrtr-ns is the one which handles the qrtr control packet. qrtr-filter service is required if the target want QMI access control which avoids unprivileged access to QMI services"

DEPENDS += "glib-2.0 property-vault syslog-plumber qrtr"

QCM6490_SHA256SUM = "d30f33d67f960324d9c2ff1076c3779d0f370b715d2fd5faac62cdf3a4a1a58d"
QCS9100_SHA256SUM = "4e9c6ff06c08d3ce58b89ce285cefe7f68e42f3201f5918c950ec5496c50c2df"
QCS8300_SHA256SUM = "5d77a854843dfeb53a9a43f4bf72ec425fb83c87281d27597374a210718a4416"
QCS615_SHA256SUM = "a7cb5e859f07af353e6fa8c4245ec74eb8786fead233ac3f7948a63f4fe5a73e"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

