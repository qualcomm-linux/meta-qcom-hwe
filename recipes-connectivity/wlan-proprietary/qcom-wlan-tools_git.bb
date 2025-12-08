inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

QCM6490_SHA256SUM = "271ff9789583bac51181f5ded9c4fc7ec2dbe635aea56659604a4e11f4512401"
QCS9100_SHA256SUM = "90ba6835a2b01fe7e2479d5ce38b82e8281d08efada80246c2d00b18032a9197"
QCS8300_SHA256SUM = "55a88f46eb8aff821e0b8d1e8922feedb5b581128528a8747145786bcff19d25"
QCS615_SHA256SUM = "4b6f96fa9951ae0aa0dac6eaab12d7a5e3efb0f88619ea9ee4438e052f56fd5f"

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

