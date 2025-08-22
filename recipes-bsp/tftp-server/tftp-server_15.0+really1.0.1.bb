inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "tftp_server server module"

DEPENDS += "glib-2.0 virtual/kernel qmi-framework property-vault libcap"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "202617708c75da8491340245a547d64a0e7f4d8908899b524375679d06922fc7"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/system /data"
FILES:${PN} += "/lib/systemd/*"


SYSTEMD_SERVICE:${PN} = "tftp_server.service"
