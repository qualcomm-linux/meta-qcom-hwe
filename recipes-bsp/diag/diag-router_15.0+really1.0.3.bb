inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "11dc8af90da5c7f64d75d56b553f014eaaaef65a73d80b07f357b8aace1edb43"
QCS9100_SHA256SUM = "e27b14d3a8bdcd676109c2ba178050e72dd4899ef7207eab752210837d2a8481"
QCS8300_SHA256SUM = "462cf0fc7e9c424db844ee8651798fecc735359766139894d9860b49c74b36cd"
QCS615_SHA256SUM = "08de6429e12ee9333c3a15f2b204c679f08c6d233911d5ec10db93b30f4976c0"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

