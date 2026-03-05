inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Libraries enabling MinkIPC"

DEPENDS += "glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap securemsm-headers"

QCM6490_SHA256SUM = "60d216b8439f5e6ca0be5b9a14966b6fce20fb2039a42d32a913cecb5699b9b6"
QCS9100_SHA256SUM = "465138298d4638909a9bd4fa59c64d303ff0069268a9efc9683929e2a55add47"
QCS8300_SHA256SUM = "7278bb308b4a3063637d5004d375d00b8470c1ef98666098fec8e1f3fcf20b84"
QCS615_SHA256SUM = "e250e89ad6a6373d668f8816c182003eea7de7af2586bd85f2e224ee5660dca7"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "debug-files"

