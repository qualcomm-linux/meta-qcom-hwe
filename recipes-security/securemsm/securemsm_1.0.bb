inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap"

QCM6490_SHA256SUM = "fe83976e12ce0048930145718e8434d3bd091ff1be6e8141a653a8af17c5597a"
QCS9100_SHA256SUM = "2b6d42aee73d073a1c408f671a6aec1a3decd0348cdd6e8f5a0f47ed8b55dfc6"
QCS8300_SHA256SUM = "b17494acdc2f02480f01ed580cddc9cdcaf9435d461b81601e2d6cd3b11e30df"
QCS615_SHA256SUM = "e9266b8d05470fc95c071d2fbdca35c779b18f857147d7b7284565751f1e711a"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/* /var/local/*"

INSANE_SKIP:${PN} += "debug-files"

