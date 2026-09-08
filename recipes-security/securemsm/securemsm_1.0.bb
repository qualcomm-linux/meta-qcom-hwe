inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap"

QCM6490_SHA256SUM = "35f2b54f97a4ad6d05feee0c1b9cf6f1be9ae02431c4f9c9004b02aed5d4193d"
QCS9100_SHA256SUM = "b10330308f6b032a8d9fde1dd90d4beb062d0ee9585d2481e76669d60b54268d"
QCS8300_SHA256SUM = "45015c16a39200f2f2286c4babdaf7adaafe5fe6cf7edc9f85e9f4ec8cd1170f"
QCS615_SHA256SUM = "42e57150e4a4c5cfe725bcac6e936ec0f694db8ada42fd6b32f8c99ed5a5d6de"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/* /var/local/*"

INSANE_SKIP:${PN} += "debug-files"

