inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-api Library"

DEPENDS += "glib-2.0 property-vault fastrpc syslog-plumber protobuf qcom-sensinghub"

QCM6490_SHA256SUM = "7d908a02aeed85be8edafd4449f03d230fc37e2d536b9378ba47c2e24a9e1730"
QCS615_SHA256SUM = "68cc6218e1e77d214e8998cef6a82adca5375d885ca73afdc83c17afc30f0011"

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
