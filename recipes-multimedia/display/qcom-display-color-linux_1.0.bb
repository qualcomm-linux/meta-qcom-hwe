inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "display Library"

QCM6490_SHA256SUM = "89f33f2226add53264ffffca71aeae1acf3f3a31816d7a2189d0381930f5a72d"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

INSANE_SKIP:${PN} = "dev-so"


ALLOW_EMPTY:${PN} = "1"

