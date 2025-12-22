inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-core Library"

DEPENDS += "glib-2.0 property-vault qmi-framework syslog-plumber protobuf qcom-sensinghub qcom-sensors-utils qcom-sensors-lookup"

QCM6490_SHA256SUM = "fc3880d71b2bc1178449f25cb6fb82a70540c4a52aba670ebc9b390c8cca1a94"
QCS615_SHA256SUM = "8a0e33fe2811e2ff94f1121dbc034d2c06ac7fd24201dc7b707c68e719a03a50"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
