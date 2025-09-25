inherit cmake pkgconfig python3native systemd qprebuilt

DESCRIPTION = "Data Portability Layer for IP Multimedia Support(IMS) on Qualcomm modems"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "75862779ae2e1a0ce3a51e4c64ce843ad27b64f6bd49c3592ac0e8a3f590ce8b"
QCS9100_SHA256SUM = "acc81d9109959a4cc72b3bba76f5569102a3c30f57c819cce5efe420190295d8"
QCS8300_SHA256SUM = "2d20afb56412f71f1c8ec9514ddedd7afee957c5cae9e6ef26a81a0846c721cb"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI   = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

DEPENDS += "virtual/kernel openssl glib-2.0 glib-2.0-native qmi-framework diag modemmanager"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${systemd_unitdir}/system/"
