inherit cmake pkgconfig python3native systemd qprebuilt

DESCRIPTION = "Data Portability Layer for IP Multimedia Support(IMS) on Qualcomm modems"
LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "060c96d02f0018814b00137f4bcffc89e23d8908237f9579c5fad5c674b690f4"
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
