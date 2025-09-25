inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "92655567371f187b2b075f041ba2a9a93201cba4cc24500a200ee068f33c901c"
QCS9100_SHA256SUM = "4c6bec4b27a0ceffd183cfda940adb90ea9a3bb5f7d471da6ae1a872bc310f2b"
QCS8300_SHA256SUM = "8fbcb3e113a7c84c442b0fe6816baf8114e78eb3baa92c8fce9d28fe263730a1"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

