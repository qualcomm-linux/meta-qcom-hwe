inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camx chicdk-autogen qcom-fastcv-binaries protobuf-native protobuf"

QCS9100_SHA256SUM = "8ef5611d737ffcba452fa01d84c242e40202b7cca70486bdb0c4b4c05baab5e5"
QCS8300_SHA256SUM = "0c178bf4f90c7922dfc91c09c2635563d88f63ebe83424b91d49aeb2dea0d2e3"
QCS615_SHA256SUM = "eca23460da6412313cb99083f610524a6b0b3c8da18af5065e2ff2d72186e6d6"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/lib/rfsa/adsp/* \
    /system/etc/camera/*"
FILES:${PN}-dev = ""

#Skips check for .so symlinks
INSANE_SKIP:${PN} = "already-stripped"
#The modules require .so to be dynamicaly loaded
INSANE_SKIP:${PN} += "dev-so"
