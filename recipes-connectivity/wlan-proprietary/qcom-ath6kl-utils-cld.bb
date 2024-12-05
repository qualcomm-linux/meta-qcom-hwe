inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros ath6kl utils."

DEPENDS += "diag libnl glib-2.0"

PV = "1.0"

SRC_URI[qcm6490.sha256sum] = "None"
SRC_URI[qcs9100.sha256sum] = "None"
SRC_URI[qcs8300.sha256sum] = "None"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
