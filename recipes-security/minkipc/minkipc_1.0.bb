inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Libraries enabling MinkIPC"

DEPENDS += "glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap securemsm-headers"

QCM6490_SHA256SUM = "88be77e73c2a08997550a3dbc304c0ad6e13f73f0295068e641ee9ec80e72a3f"
QCS9100_SHA256SUM = "0244a94f42d82aca25d5438dbdb7bf580c3000b409c1b15b1eb1583578be86e9"
QCS8300_SHA256SUM = "f64e56c606c44d0910cfbe5803bedba5da7a82037deffafc4c8dc7ed5f4be18d"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "debug-files"

