inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

QCM6490_SHA256SUM = "622f36054eea4093c7b6935de0375be2a1eaf9033da916ad5ee0acedc29548ba"
QCS9100_SHA256SUM = "4b26f8d422bcd65c0ef0e756b5ee01a15ecf160a1fd03b3a14b5ef0486cb1e8a"
QCS8300_SHA256SUM = "9ccc305b94745c260cdba2f309ee18921d057eecb6a8d6e149f78cc5be08e191"
QCS615_SHA256SUM = "156d4053add5a439ec9d22348f2381442a8fb39c3dc5640ef6b11d89aca11ab9"

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

