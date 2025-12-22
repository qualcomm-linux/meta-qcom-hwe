inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "ce9e7df649dbb56bb520291fc8133ab92189630209448dae9b72f3a48ced0aa2"
QCS9100_SHA256SUM = "56b0ed5c620d16caf0ab9e5df31683a65f5289436d5f2545bfb12fb668fb5e42"
QCS8300_SHA256SUM = "6575a475860137995cd112323371be2bd2dac5805142d2466e37b3a4deface46"
QCS615_SHA256SUM = "a391da77b84ccafe65d7551be1168c4992d534b715e042112fce657b462b6114"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

