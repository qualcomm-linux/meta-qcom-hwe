inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "7dbc43151a2ccb849a70b5dc09bc8dc6e71e94e16b0ac33145102db49779e1da"
QCS9100_SHA256SUM = "0a190b866c54cc0ab26d1995613e30f4cee2c308a60b3e6e7c2751c7b25ff1a0"
QCS8300_SHA256SUM = "178a57c45623a7e9c5549d18bef59bef31e425871383ca045eea79964d3519cc"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

