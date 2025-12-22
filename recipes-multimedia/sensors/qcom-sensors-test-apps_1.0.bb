inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-test-apps Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber protobuf qcom-sensinghub qcom-sensors-api qcom-sensors-utils qcom-sensors-core qcom-sensors-test-core qcom-sensors-test-utils qcom-sensors-lookup"

QCM6490_SHA256SUM = "590b3849456bec588b6df1f69872f62888cdc119b7d7467bbfde03902e6e566b"
QCS615_SHA256SUM = "090c2f1852f793b5f54bd63d6b4c057f6d6e1053291e109f129b73a6270853bf"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
