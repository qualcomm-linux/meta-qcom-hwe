inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap"

QCM6490_SHA256SUM = "fd73cb17a43dbb42c15f5e1ff988fa2941f3ada9c88307ec6d2c4b820ee8ea6d"
QCS9100_SHA256SUM = "007bf22f39cc5ba9f3cd4b5fad1e1a8fd7bec9d7a9b46bd3997725c68536cf77"
QCS8300_SHA256SUM = "efb7cc96525faa9d423a386950acd81f2ce73720e3d1d949302b0ab156f7257e"
QCS615_SHA256SUM = "a3a2199d4810e5e9fdcde12db51361258ff795c624e5ef3661530b6b6280da70"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/* /var/local/*"

INSANE_SKIP:${PN} += "debug-files"

