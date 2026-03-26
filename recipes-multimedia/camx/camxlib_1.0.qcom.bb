inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxcommon camx-autogen chicdk-autogen cameradlkm fastrpc adrenocl protobuf-native protobuf"

QCS9100_SHA256SUM = "6a469c8fdda72050e784fe78d38d35fc0773099a1f13fa927d5ceebabb2f7bac"
QCS8300_SHA256SUM = "be0b123814ba59cc0d9edbc3949d30252a2fac3f29c4e3d10a473e63efdc75d3"
QCS615_SHA256SUM = "463e42bf6998c20c298a6e35d66956281f8b1f3497f4b8fd1e6c0fb95beee670"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Install firmware file at right location
relocate_firmware_files () {
    install -d ${D}${nonarch_base_libdir}/firmware/
    mv ${D}/lib/* ${D}${nonarch_base_libdir}/
    rm -rf ${D}/lib/
}
do_install[postfuncs] += "relocate_firmware_files"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    ${nonarch_base_libdir}/firmware/*"

FILES:${PN}-dev = ""

#Skips check for archtecture
INSANE_SKIP:${PN}-dbg = "arch"
INSANE_SKIP:${PN} = "arch"
# The modules require .so to be dynamicaly loaded
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "file-rdeps"
