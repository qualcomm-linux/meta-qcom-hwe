inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-core Library"

DEPENDS += "glib-2.0 property-vault qmi-framework syslog-plumber protobuf qcom-sensinghub qcom-sensors-utils qcom-sensors-lookup"

QCM6490_SHA256SUM = "b3c282c7cf31b33009820b01d7fcc0a1c6aafd417d375f8fc9b6f0885dbcc783"
QCS615_SHA256SUM = "0b6c66d4c669c55818b847ab7dc18f1fcb75faab85623e9c5a79e83845d5353a"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
