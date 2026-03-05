inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS = "libxml2 libdrm libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libvmmem qcom-libdmabufheap \
           securemsm-headers minkipc property-vault jsoncpp qmi-framework curl\
          "

QCM6490_SHA256SUM = "99b3b7ee020cc9cf20254f5d5ac7bfb399049c9c8a9adfc2d88ce7e2d27bd349"
QCS9100_SHA256SUM = "8fbbe344f4e180c9e413da6a95445172d8d10225f2384956d66d0cc1f5ee585e"
QCS8300_SHA256SUM = "793ba6b80f81284a6c885a489499a23fc073c5f072917cbb4a49eec0363d7eaa"
QCS615_SHA256SUM = "fd85b9f019cfa29b2a70d74416085e73b03db0d00e059efd3be7d2f1ef7bbd94"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/usr/bin/"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"

PROVIDES:append = " virtual/wvoec"

INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "dev-deps"
INSANE_SKIP:${PN} += "debug-files"
INSANE_SKIP:${PN} += "file-rdeps"

