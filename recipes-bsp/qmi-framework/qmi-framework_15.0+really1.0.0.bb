inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QMI framework will provide sample applications to test QMI communication between Apps and other remote sub systems. It also includes multiple services to support the QMI communications. qrtr-ns service should be running in background to support QMI communication because qrtr-ns is the one which handles the qrtr control packet. qrtr-filter service is required if the target want QMI access control which avoids unprivileged access to QMI services"

DEPENDS += "glib-2.0 property-vault syslog-plumber qrtr"

QCM6490_SHA256SUM = "0959851b34c8f1ea366ec97043fd45f35bf6077390097b38c0927ad3e7abe4b5"
QCS9100_SHA256SUM = "bd6128c9cfdd19292894c66736193b07f6b42355f18085497a067e4f1fee8421"
QCS8300_SHA256SUM = "33ce7f981f298f2ce03310b9eaccf3016ff8850e1a2f93171f10e775b480189b"
QCS615_SHA256SUM = "96095ca25286e68d0c7fc1fb51bf5bf03458aa8c59a1db266b86a524a7df8ad6"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

