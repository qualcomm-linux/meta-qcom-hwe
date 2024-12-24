inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Thermal Engine"

DEPENDS += "qmi-framework glib-2.0 libnl linux-kernel-qcom-headers"

QCM6490_SHA256SUM = "0571f199740b88082af98293632e897eb628df6d38a44077efc2a8945e4c3883"
QCS9100_SHA256SUM = "f8079a41c5dd27adc2c106700a23bec5a64101b82e9faec10b1f058c0067c1a8"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
