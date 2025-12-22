inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxapi"

QCS9100_SHA256SUM = "d248f16edd5e8291fcecbcaa2cec630b269fb13c88a6fe3d1f278ae36b50d4dd"
QCS8300_SHA256SUM = "6ea53f8cf5f460084b7bbd95d03d1c3b6919e0468eb39c4451f1a04548eb89df"
QCS615_SHA256SUM = "4e9870fd88922d447c493663a45f2d57ec9b7104e9f28b68398850eb984c2efa"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-dev = "/usr/include/*"
FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"

#Skips check for .so symlinks
INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

