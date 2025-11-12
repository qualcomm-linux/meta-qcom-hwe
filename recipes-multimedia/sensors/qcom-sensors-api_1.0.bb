inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-api Library"

DEPENDS += "glib-2.0 property-vault fastrpc syslog-plumber protobuf qcom-sensinghub"

QCM6490_SHA256SUM = "9d8da25e55affca045bacb2c4902b0efc0e7ce9828414d3a9d72ac10723da51b"
QCS615_SHA256SUM = "1a50f71ce30409f1db406bc07d4ec58d154389019407be8c52a5c9b91335b7b4"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  += "${libdir}/*.la ${includedir}"
FILES:${PN} += "/etc/sensors/*"


INSANE_SKIP:${PN} = "dev-so"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
