inherit autotools-brokensep pkgconfig qprebuilt

DESCRIPTION = "Qualcomm Technologies Ctrl App Dut Tool"
LICENSE     = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DEPENDS += "libnl glib-2.0"
PV = "1.0"

QCM6490_SHA256SUM = "8fb5739eb3f5f0a833c5010347ccaa674029bd4897fafb222b1e5e645fcbee9b"
QCS9100_SHA256SUM = "6709dea6d21f687d57f6f38dea716af1982f7fa767953a963bab70e4a2a2ffd0"
QCS8300_SHA256SUM = "7f88ce1cb2293cf5c69627eb20391357901563b63d6a40f520f2e28eb91bae9e"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
