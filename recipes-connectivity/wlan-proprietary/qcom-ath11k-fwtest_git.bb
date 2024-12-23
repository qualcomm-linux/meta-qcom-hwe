inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ath11k-fwtest"

DEPENDS += "libnl"

PV = "1.0"

QCM6490_SHA256SUM = "d16f5b8b6ef43e0ed29b54408380ca82a15169ee9a6cd0287ae9b1c061b3dd8d"
QCS9100_SHA256SUM = "903e2f8846a0f083c3e436329d6d0e22e9278b435d945ecd800e0e8a81ecb8d5"
QCS8300_SHA256SUM = "a2a987bb0a997f0243f029f1959777611dcf7af54f7bf035f15db1d2853cdda0"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"