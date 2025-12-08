inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Libraries enabling MinkIPC"

DEPENDS += "glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap securemsm-headers"

QCM6490_SHA256SUM = "b973c8f24a443ae9b6e32686c3b789ab7f775769f1e4b0cf4dc1198e7045595f"
QCS9100_SHA256SUM = "cc94a2db470feb7b86a53189048c5e4ae003e24d4b4bb40fac4c422d3fffa5da"
QCS8300_SHA256SUM = "81d5076c4d8ed36708ce06443ea2c8cccd0f911fbd5898ddec54162935efe9a3"
QCS615_SHA256SUM = "160d3097f01ca57771f82f6215fc931ce4f33792c8ba258d2cba2c83b65464b0"

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

