inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "f00e9e752711ac101b149f3c5bf24a33d6da0abee2395438177927e9dda2f1be"
QCS9100_SHA256SUM = "3167d55a23f5dee97086f74472818cb69f10c4fc4a6773432d764f4bfa5bde02"
QCS8300_SHA256SUM = "17df8b702de8134d65825519b132c94e89e2380c72bf6ff51ee7c0c8d105013a"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

