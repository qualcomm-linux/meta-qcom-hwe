inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "qcom-libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "94c6e0985b9e8b37380f74ddf5bcdb6349a4ce9d57092a077426fe42a8902f1f"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-cpu = "${libdir}/libfastcvopt.so* ${libdir}/libfastcvdsp_stub.so*"
FILES:${PN}-cpu += "/usr/include/*"
FILES:${PN}-cpu += "${libdir}/pkgconfig/"
FILES:${PN}-dsp += "${libdir}/dsp/cdsp/cv/*"


INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN}-cpu += "dev-so"
INSANE_SKIP:${PN}-dsp = "arch"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

ALLOW_EMPTY:${PN} = "1"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
