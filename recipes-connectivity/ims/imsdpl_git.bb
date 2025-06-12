inherit cmake pkgconfig python3native systemd qprebuilt

DESCRIPTION = "Data Portability Layer for IP Multimedia Support(IMS) on Qualcomm modems"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "08cfcb67c0eed576005bf14e27a2c8492d7e64393319e14153b519f312a4d978"
QCS9100_SHA256SUM = "29df77e68e9c660a5f13ae5990fb658b618db9870675210028eea3fac5c2ec72"
QCS8300_SHA256SUM = "2e27b6d25dc7b86ecd77519e86fbf6075a6ebd4cbd23e3e5c546b9e27d1e155f"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI   = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

DEPENDS += "virtual/kernel openssl glib-2.0 glib-2.0-native qmi-framework diag modemmanager"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${systemd_unitdir}/system/"
