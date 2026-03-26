inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxlib chicdk-autogen camx-autogen fastrpc"

QCS9100_SHA256SUM = "cc97a9a1ffe3173b583e3215c06c5cfb36615cf2f478b88f057f5b4ee4a5a3fb"
QCS8300_SHA256SUM = "6ea9053627409abec3c6c3278a1934ea254db924efe378ccd275d030b26165d5"
QCS615_SHA256SUM = "32c16f87bb96e9bef7fb5312818fbe2d7f8b6e6aad9f8aec41e546f1c6cf0d95"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /lib/firmware/*"
FILES:${PN}-dev = "/usr/include/*"

#Skips check for .so symlinks
INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

