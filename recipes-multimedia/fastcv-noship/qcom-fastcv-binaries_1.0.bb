inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "qcom-libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"


QCM6490_SHA256SUM = "65bf5dc174dcea1fdc0bbcf8b7930b792de28855df2949ae1d0c5f57ecb38497"
QCS9100_SHA256SUM = "ccf655aef7774dcb728a43e58702775d2bbe2ee1f84ea5092b8a9bd2168d21ef"
QCS8300_SHA256SUM = "866f5474f80f7067237369fa876165d38f2e1b55f2ffe93bfde52083add9a565"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-cpu = "${libdir}/libfastcvopt.so* ${libdir}/libfastcvdsp_stub.so*"
FILES:${PN}-cpu += "/usr/include/*"
FILES:${PN}-dsp += "${libdir}/dsp/cdsp/cv/*"


INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN}-cpu += "dev-so"
INSANE_SKIP:${PN}-dsp = "arch"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

ALLOW_EMPTY:${PN} = "1"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
