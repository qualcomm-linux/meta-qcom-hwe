inherit autotools-brokensep pkgconfig qprebuilt systemd

SUMMARY = "Driver providing support for SAIL-APSS Mailbox communication"
DESCRIPTION = "Provide Sail Mailbox Driver to communicate between SAIL and APSS"
LICENSE = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"
DEPENDS += "glib-2.0 systemd linux-libc-headers pkgconfig-native cmake-native"

QCS9100_SHA256SUM = "6ccd34bff70aec66c5e7b7cea8458ee720232db1cad0ec3d738d04b13618054b"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"

SRC_URI:append:qcs9100  = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"



QCS8300_SHA256SUM = "8e5f3d62be383e7acacf4cb132b6f9672184fb7ebc0e2e954b86b810500adcbe"

SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI:append:qcs8300  = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGES =+ "${PN}-bin"

do_install:append(){
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN}-bin = "image/${bindir}/sail_console_chan_app"
FILES:${PN}-bin = "image/${bindir}/saildbg"

