inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "2a11bb71f8dc6ca8f2b437060326c4b382f009f468a9bc71e2d7c6ebf2914512"
QCS9100_SHA256SUM = "ebeb7b69fcd2713b4b133fd77e692ffeb58f2daef259051b557e7efa0d2b6b5b"
QCS8300_SHA256SUM = "c353e3c1ee6cd92ff10653d0f3f6eecfd203e544aff838f914c9b947dd65457d"
QCS615_SHA256SUM = "7f68e357420d530aa1fca0340e161f74c47c16ad3d0000596b81aca34759ee40"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

