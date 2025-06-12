inherit cmake python3native systemd qprebuilt

DESCRIPTION = "Real Time Protocols for IP Multimedia Support(IMS) on Qualcomm modems"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "38efe19c2132ab5d329fc1c486b0f0c0f9fcb6bf9147b39ef59ab78c26008a72"
QCS9100_SHA256SUM = "ba3fe5136b7b6bd00e93379ed13f690bb6f516508890b9dcd462b0e80b7834da"
QCS8300_SHA256SUM = "c151da86f7079582987b6f7d7fdf3d510c216a128330e93d1822dbb41f5342db"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI   = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

DEPENDS += "glib-2.0 qmi-framework diag imsdpl gstreamer1.0 gstreamer1.0-plugins-base"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${systemd_unitdir}/system/"
