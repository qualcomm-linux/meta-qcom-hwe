inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Thermal Engine"

DEPENDS += "qmi-framework glib-2.0 libnl linux-kernel-qcom-headers"

QCM6490_SHA256SUM = "43c33f19cade2e77bed5cc1fa32f56d6a742715b63a48f07df302013f3a3546c"
QCS9100_SHA256SUM = "c5ca91cb1723e168e25513b1dc63d51f0072a407e93ac89e4785af4427fd5444"
QCS8300_SHA256SUM = "889d1e02d9648e9bdcd44585cac99c68ef5bf8860b445b7b71d6adfc33864125"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/* ${bindir}/* ${libdir}/*"
