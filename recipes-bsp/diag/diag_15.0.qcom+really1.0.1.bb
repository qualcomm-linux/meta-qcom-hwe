inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "3abac66fc045ffa5acb243994574a24bad53a53e58b57507b257c053ee04a04c"
QCS9100_SHA256SUM = "8b97f2a1b8eb7a343617603f3eeff119c968ebf9ba193eee4a58ff269d943972"
QCS8300_SHA256SUM = "747141311e86196fc4ad4894fc534c78b1ec04052807d489af4252cee8c74b4d"
QCS615_SHA256SUM = "0e1a7cbfbfa831ef0587e6e9be934113e038bdc70f5fbd84f0acf69f6c7d39b6"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

