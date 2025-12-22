inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-api Library"

DEPENDS += "glib-2.0 property-vault fastrpc syslog-plumber protobuf qcom-sensinghub"

QCM6490_SHA256SUM = "57a3db85a023d5ec45e9b8cb6d42e194f912c3d4070e7433c664e2c12273d341"
QCS615_SHA256SUM = "ce89c86f41fbaad01cae0761e38877b85f286ba77aed50013cbb951e884d2439"

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
