inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS += "libxml2 libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libdmabufheap securemsm-headers minkipc property-vault jsoncpp qmi-framework curl"

QCM6490_SHA256SUM = "1fb6b33f33727d7ca26d67fdef20a19bac934bbd7cf7f31781b89e0c9c2b21ef"
QCS9100_SHA256SUM = "9b7bd612fdff551fee97f1fa58026d5ad656613ddc7b7a5760ee63375faf2fa6"
QCS8300_SHA256SUM = "1e8b2c8adc040079e1c8e18cbae54a2194e075401a8c8a59b75f88e4f15d8854"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/usr/bin/"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "dev-deps"
INSANE_SKIP:${PN} += "debug-files"
INSANE_SKIP:${PN} += "file-rdeps"

