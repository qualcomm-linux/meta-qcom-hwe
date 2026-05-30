inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "1e3e7b6dc25a4023253fb9ed363baee81c27f6da634caf2f524899dd2f971077"
QCS9100_SHA256SUM = "98cedca16c0540be937d885bfebe20722ac38177eea74d5dead89abaa70f1b50"
QCS8300_SHA256SUM = "77af212549373ddb1ef7adc54f4066542a5a7b57596d4d376f051fafe0f7d966"
QCS615_SHA256SUM = "ff735a7055e96a9f62bc265df5a9ce397c61bff5bd42c86a4e4d7ef42a2b850c"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

