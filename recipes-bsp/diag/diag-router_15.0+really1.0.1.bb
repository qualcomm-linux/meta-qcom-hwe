inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "721edf337ca29cde0beb7f97be014c1f1642994a22a6176c341ae9aa0fa7cd89"
QCS9100_SHA256SUM = "b17f6f7e833547115969c0d36c69bfbaa29ad892ba4f2a67a020bdf86d299071"
QCS8300_SHA256SUM = "45de7ae0ccee27a41fa5b822f0cc76fa165f1cf057457222dedeb12fb9d62222"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

