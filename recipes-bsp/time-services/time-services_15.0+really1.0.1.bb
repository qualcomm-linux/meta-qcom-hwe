inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"

QCM6490_SHA256SUM = "157dd07a3b71a2024644aea788bd89ae013d30ca60a5d899d828cc417c48e705"
QCS9100_SHA256SUM = "7465209bbf48c1bf54ce29d2773a61713f9dd022a35d4f8485464f9e16a2c4a7"
QCS8300_SHA256SUM = "ea2ed7db0eee6dac21812b04c5b549245480f6e3075911b35c121f8951ad8f51"
QCS615_SHA256SUM = "033aa3e83f276ec571ce8c0ab4f8406e1b9a49980f1aa8a5bb906b5985279a5b"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

