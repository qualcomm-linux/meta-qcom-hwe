inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Thermal Engine"

DEPENDS += "qmi-framework glib-2.0 libnl linux-kernel-qcom-headers"

QCM6490_SHA256SUM = "4b0db5de92ec5b7941a130a75c44c26d680c1813818ce23ee5c6289bec3a308b"
QCS9100_SHA256SUM = "9140261e3c3dc037bd0a7e3bd102da2c29c72c7f21d6043bf27e3fd211cf994c"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
