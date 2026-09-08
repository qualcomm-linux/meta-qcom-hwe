inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Libraries enabling MinkIPC"

DEPENDS += "glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap securemsm-headers"

QCM6490_SHA256SUM = "0f60ee4809d98057f71f527d7a61a43e0252ae13d2265651c6e9993aea44c974"
QCS9100_SHA256SUM = "b7f8e78caf7af5da7260cdc45d5e711ec76f68234f6a141ad5b89ceb031c2a52"
QCS8300_SHA256SUM = "9c5498ce826bc8b7b40f90a56bc0c4c7b6b0cc66109fc8a2123b8138f9dcf0d8"
QCS615_SHA256SUM = "a1d5019acadbcb7f0b68a7a38d127b0a5284aa2c04ed1f8b21045ba8160cbbbd"

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

