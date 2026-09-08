inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-test-apps Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber protobuf qcom-sensinghub qcom-sensors-api qcom-sensors-utils qcom-sensors-core qcom-sensors-test-core qcom-sensors-test-utils qcom-sensors-lookup"

QCM6490_SHA256SUM = "dd1d3f4d30726972d67f1a76d4b37b2fca67a920ea76683aac621e12d11823d3"
QCS615_SHA256SUM = "e11ce084f697804f6e01bbaa1ee2271e48d178317d4e4e008334c324e1cd609b"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
