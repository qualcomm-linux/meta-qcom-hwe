inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"

QCM6490_SHA256SUM = "ceffd2ed4253e73e2dc0348287adfeb2c95eeb829baa382afea208529261a22c"
QCS9100_SHA256SUM = "48010bbd8772acfa2c4d0bb53dd35ed56b4c253134f743a455a02782718fec40"
QCS8300_SHA256SUM = "e12e7226d634b7a26fa9627d78fa80e709299990b7b8e227fb3d75d0b8bdaf6c"
QCS615_SHA256SUM = "889b179e399aaf74ec80cd070c56673411e107be67b530802a55c6cd2057b812"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

