inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"

QCM6490_SHA256SUM = "a99fab46cca4a8c52f7af318e58a282ac0991a3e992eda80ff44a59c32845c59"
QCS9100_SHA256SUM = "284d9f97e460e52a318f779ea6b75cecbdaff2b76da06cd7074edf65db25850e"
QCS8300_SHA256SUM = "73309d2f52141c29696b67e648c8b42f2ee25e1aee943d6a68426923fe9c37fc"
QCS615_SHA256SUM = "b6e6ec3cb4088ed42e16be0f8a0eba7efddf437c56f9c0de02ab99eaebf77ab2"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

