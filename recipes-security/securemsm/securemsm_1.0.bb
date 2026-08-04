inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 linux-kernel-qcom-headers qcom-libdmabufheap"

QCM6490_SHA256SUM = "44312615c4f201455e3aefdec94c56487e6f088ecb81b51e32a6421ce27a9f82"
QCS9100_SHA256SUM = "5b37f48f33cf0707b566b9f5619562f7f2542d6682471418fcbb5fd61c86d072"
QCS8300_SHA256SUM = "e9f55ac5158c71c24189ea4add397edf37474317ea22472f0d06586a78aa2797"
QCS615_SHA256SUM = "18f2d99c2c43a1a90fff429b784c10b1adbedb7d66978e6431d4cdfcedfca5e7"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/* /var/local/*"

INSANE_SKIP:${PN} += "debug-files"

