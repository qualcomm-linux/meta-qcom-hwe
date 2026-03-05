inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "895b74b317313f4365bb2556064111eb317b30dd6810e8076a31e91e71c9c162"
QCS9100_SHA256SUM = "72fb83765a999fd67f7868cc5095f4df5bfa3733988a16fa77b8ba6537a41a11"
QCS8300_SHA256SUM = "ea9a699fd0a38c5f42162571ca23f89f533be1b43ea97a161710d9d3e9bbeab8"
QCS615_SHA256SUM = "d7fea647bfdc04834f7053f8abf2ac923e1b9b6f49db44fe343acbdbac4d9a2d"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

