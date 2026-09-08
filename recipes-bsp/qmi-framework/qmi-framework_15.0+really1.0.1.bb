inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "QMI framework will provide sample applications to test QMI communication between Apps and other remote sub systems. It also includes multiple services to support the QMI communications. qrtr-ns service should be running in background to support QMI communication because qrtr-ns is the one which handles the qrtr control packet. qrtr-filter service is required if the target want QMI access control which avoids unprivileged access to QMI services"

DEPENDS += "glib-2.0 property-vault syslog-plumber qrtr"

QCM6490_SHA256SUM = "9ddc643cc6ca5fc2f64d8a41516abf9cf946fe4ebb9ac38d4088fa06d0ce1fed"
QCS9100_SHA256SUM = "47e4b73f5453134a0d9c70883153ced6f04bf95e30747ef2b3a2fba0a10e1281"
QCS8300_SHA256SUM = "4b4b705a8cca3db32aae364c5637b6971f9049c5e678d98921f1393584a755cb"
QCS615_SHA256SUM = "e2c89a61c59564f3cf1f8ab4b17a1c1d141c99ff0691704d3827dc17d9871f55"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

