inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

QCS9100_SHA256SUM = "bcbc0b3d23d9c657d93e3f66bed3261ea53cc6eb304778fb6942a73af0313eae"
QCS8300_SHA256SUM = "77a97684dc65e336f5353f4ed3245e8dc6c36967c32e68b93c54baf68fd421df"
QCS615_SHA256SUM = "35f728a627b8d9b23d2c918acce467de65bd22b87db24e65c27a74864ccb2bc8"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-dev = "/usr/include/*"

#Skips check for .so symlinks
INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "already-stripped"

