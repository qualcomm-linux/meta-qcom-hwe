inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensing-registry Library"

DEPENDS += "syslog-plumber"

QCM6490_SHA256SUM = "356349ce5427e4c584d009e1e4e237df6c532cd573e20808773fad1cfb2fdc6e"
QCS615_SHA256SUM = "583c0a511a935d9e43cac468ff342b1b1fa21196bce9c8c4a7958249b782e3b3"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/etc/sensors/*"


INSANE_SKIP:${PN} = "dev-so"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
