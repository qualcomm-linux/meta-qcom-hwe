inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxapi-kt fastrpc virtual/egl virtual/libgles2 adrenocl protobuf-native protobuf"

RDEPENDS:${PN} += "cameradlkm"

QCM6490_SHA256SUM = "7a57ac6c4d5bff047de1dda149bee55c947ab381edb45ec67bdf8ec5c18f7367"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

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
