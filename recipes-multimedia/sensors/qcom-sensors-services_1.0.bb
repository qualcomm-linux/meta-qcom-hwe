inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-services Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber protobuf qcom-sensors-utils fastrpc"

QCM6490_SHA256SUM = "704775909713bfe81c4c103293c104e5085edb7d35b98c69446cf0a5f1fe4d30"
QCS615_SHA256SUM = "6dc1eb4e64fa357d3249636e5b2b1de95d4d66dec77ad0dcc11bcce81dac4ee7"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
FILES:${PN} += "${systemd_unitdir}/system/"


INSANE_SKIP:${PN} = "dev-so"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

SYSTEMD_SERVICE:${PN} = "sscrpcd.service"
