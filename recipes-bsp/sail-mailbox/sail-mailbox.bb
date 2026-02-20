inherit autotools-brokensep pkgconfig qprebuilt systemd

SUMMARY = "Driver providing support for SAIL-APSS Mailbox communication"
DESCRIPTION = "Provide Sail Mailbox Driver to communicate between SAIL and APSS"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
DEPENDS += "glib-2.0 systemd linux-libc-headers pkgconfig-native cmake-native"

QCS9100_SHA256SUM = "3cd6d53b1c8c855869afc836d96f25459a3918e03ed883bfec340227876b3ffc"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"

SRC_URI  = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGES =+ "${PN}-bin"

do_install:append(){
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN}-bin = "image/${bindir}/sail_console_chan_app"
FILES:${PN}-bin = "image/${bindir}/saildbg"

