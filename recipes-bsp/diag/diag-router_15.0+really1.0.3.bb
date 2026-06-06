inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "626fb1e827cccd846599851b10f6898528090a0e777a6a6c91cc6cbdd1a4347a"
QCS9100_SHA256SUM = "5c1c6abb118af054f21d6d06d2bf498ad26483b0aa86c49eb5c79cec101aaaad"
QCS8300_SHA256SUM = "b24188ba87646b22b04e5ec3d695aae200dd0b37afdb914ad11b81181c4ebc20"
QCS615_SHA256SUM = "14f7e5631a8603f87773987164395329bf7d9f900e974e367ca0dcbd80355773"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

