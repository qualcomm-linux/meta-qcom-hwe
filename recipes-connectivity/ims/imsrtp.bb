inherit cmake python3native systemd qprebuilt

DESCRIPTION = "Real Time Protocols for IP Multimedia Support(IMS) on Qualcomm modems"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "0971f95ae4a39234941695f4b3a0263886278d3a8db475629f31ded6840e8475"
QCS9100_SHA256SUM = "19f6f15fbbebcc571d3e646905afb70ed6f2135dd5ad3d34422d4ca6afe6bf85"
QCS8300_SHA256SUM = "4709c1eae96ec60ae845c76085095801d722fd7ca657b88d47593e8ee0a5c63f"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI   = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

DEPENDS += "glib-2.0 qmi-framework diag imsdpl gstreamer1.0 gstreamer1.0-plugins-base"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${systemd_unitdir}/system/"
