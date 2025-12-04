inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QMI framework will provide sample applications to test QMI communication between Apps and other remote sub systems. It also includes multiple services to support the QMI communications. qrtr-ns service should be running in background to support QMI communication because qrtr-ns is the one which handles the qrtr control packet. qrtr-filter service is required if the target want QMI access control which avoids unprivileged access to QMI services"

DEPENDS += "glib-2.0 property-vault syslog-plumber qrtr"

QCM6490_SHA256SUM = "ab6051a3a24835ee2e2958815f632ceaae8140f590d90088ba5a0837ee6c149a"
QCS9100_SHA256SUM = "907d3723e883bcbb8917a045e36e4bcc3193a8dc0581c3878434f097baf33d1a"
QCS8300_SHA256SUM = "99deeaf9334fb2ea81064defbfabbeee671a855b0f32b9dd697ec49d074fd634"
QCS615_SHA256SUM = "9b072269467507b37d367d9834f0ccae1b330234edb2943b55f59af0cf89ab9c"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

