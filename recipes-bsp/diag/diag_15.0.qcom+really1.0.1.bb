inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "889947a4baf32b469f7d3f0e0d6ac76af34d80a1eeef3e4e1b41de6a6632d1a3"
QCS9100_SHA256SUM = "b1c65566bc7d82ddb4aec5a607281b01a3894f61fb0106ebfccfea1813a0a73b"
QCS8300_SHA256SUM = "6068ba172534114da8bc13b498d75c2cc4ed4b72435721f53571c4b2efc94d1c"
QCS615_SHA256SUM = "a6b2fd62a246c363c288ed2a7af634327a630ff52f24b1adb3e116be3215533e"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

