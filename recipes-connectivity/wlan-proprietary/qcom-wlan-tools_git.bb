inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

QCM6490_SHA256SUM = "246ff3eb09c3e4fa8aef1da25455284274d6bebe607e51d08ee0725294eca602"
QCS9100_SHA256SUM = "82b0a84d351291c16f6b0f27a245608809279fd5060a9555ff17d94851300221"
QCS8300_SHA256SUM = "0974fdc9049a6f9f5f90975fc7aad6a31a7f93d6a0895f0bc4072f965fb7c8b7"
QCS615_SHA256SUM = "fc17e46b5abefde13dbfdadb8ef2cb4b4c4844147ccbe1a1ea3b296c15735b66"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += " \
	/usr/bin/* \
	/usr/sbin/* \
	/usr/lib/* \
	"

