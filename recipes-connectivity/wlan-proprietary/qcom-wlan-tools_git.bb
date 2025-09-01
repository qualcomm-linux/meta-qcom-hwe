inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

QCM6490_SHA256SUM = "5ebf96d4b61e5574a780753c20cdb92ceb1f976b814badd3e62a2b1a78a5ae88"
QCS9100_SHA256SUM = "8f578d36895c8b5e544246ceea8e27ac7cdc12b58bbd5b8e9d757b7662f5e5e4"
QCS8300_SHA256SUM = "6572fb4e411140e60c2a3579b7b56de13fef1c98226d0b51cdcec85d13ae0032"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += " \
	/usr/bin/* \
	/usr/sbin/* \
	/usr/lib/* \
	"

