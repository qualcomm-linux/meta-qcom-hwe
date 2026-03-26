inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camx chicdk-autogen qcom-fastcv-binaries protobuf-native protobuf"

QCS9100_SHA256SUM = "21534e0f405859206a9beb24737835ee639a141668b0da60b12898c979a7e083"
QCS8300_SHA256SUM = "3e7377f415a8d2e7998418ae43fc2e2c3993d438d73a23270f645aa73e353559"
QCS615_SHA256SUM = "c1884efa8b338b178b8a33af7cf1e50654274d5db9e5ff18017d33fc92a87ac1"

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
