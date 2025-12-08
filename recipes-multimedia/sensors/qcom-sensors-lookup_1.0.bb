inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-lookup Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber qcom-sensors-utils qcom-sensinghub"

QCM6490_SHA256SUM = "800bfb7d2bca9ca1d8155181f97e756d80d1f890503db1305a3a555758d145b3"
QCS615_SHA256SUM = "c088d3d8c2d1cbcf2065bdeffdf06e73b0a0c1dd00f6c50bfcf72ea414c5426d"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
