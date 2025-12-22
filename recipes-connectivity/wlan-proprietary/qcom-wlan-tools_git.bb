inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

QCM6490_SHA256SUM = "4d0493979f7241e4124d7c8aa290fcc761b24c73f0a3e42a64672e32705a8b37"
QCS9100_SHA256SUM = "48a301493fba90203e99600c7613051fa356e3cbae0dfc7cc7de50a4f43c22ab"
QCS8300_SHA256SUM = "a7ae7a442a8405b30ed4bd98c6f0cc2aa1ccf8f4aef9597ab81fdfd147641f0f"
QCS615_SHA256SUM = "6cdc402a94d628b0793a44b63539c9fbdf66f9e403b593991c93ef13c94c354a"

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

