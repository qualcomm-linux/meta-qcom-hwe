inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "ff84c73fa2a1a747577f02d1cf315dda72a87c364d19be36c7460afd2a1eb83b"
QCS9100_SHA256SUM = "94b837dcbb824a272193768e21653702e2a779e997751bc9c731c5ce5be034e9"
QCS8300_SHA256SUM = "4df68e3c018104b78bd0bf5a8cd44f9188b62cef866b17ad0505c35843adbb28"
QCS615_SHA256SUM = "0d47d5b1a1ad3e327cd46ad6d16605bdf701c5c50117c95dfb816717c6404411"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

