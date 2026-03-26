inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "5fddaf52d3ba0d4e14324fb0b50a168901f2bdd38e88c946d44acc2d0781974b"
QCS9100_SHA256SUM = "cadd7b6afad7a431b97619f155a707cb9befd002a06b6458022e4d95708035c5"
QCS8300_SHA256SUM = "6dd7273d4c631f2bffed4636b4b1ea437eeca4d6083d23860f06d04e7eeb87b6"
QCS615_SHA256SUM = "daacbac2790c08cf94a24d31917c971953fba112f42bb43e771a98d002c30939"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

