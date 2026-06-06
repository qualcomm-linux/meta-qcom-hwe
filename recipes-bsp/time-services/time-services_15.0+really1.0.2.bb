inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"

QCM6490_SHA256SUM = "692ab7872bc5e186705a0ab8c8a5dc26a0b693e051d6d39094f52d9938fb2125"
QCS9100_SHA256SUM = "aa410535f3525330384910b7beaa1d223a046c860a523c5390242786896a3252"
QCS8300_SHA256SUM = "3fd0febef3c84907f9cfbd0310de4c13ecba0b625257f7860a64f54f68c7cc96"
QCS615_SHA256SUM = "ccfa4e978478ac6b1afef1fd7c52ccbe9b6cad8c0fc17b2b2fa2540a2a412343"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

