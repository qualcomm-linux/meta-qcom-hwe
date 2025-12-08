inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS = "libxml2 libdrm libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libvmmem qcom-libdmabufheap \
           securemsm-headers minkipc property-vault jsoncpp qmi-framework curl\
          "

QCM6490_SHA256SUM = "61ce8dec9e4a9562414a6e1c8c8e4ce04bbe1e5f3d3ffb521ca4a4dddcb1264c"
QCS9100_SHA256SUM = "eef5b3ec0559940b7f6013d63cfa5d41b8811cc7b01887e5a0a959168e05c378"
QCS8300_SHA256SUM = "12a79b7999465f301490fa81787c5c0d3704919bbae91d70b758d2db11ca4991"
QCS615_SHA256SUM = "bbb1188cd6dab173ed8b9942caf61466eaad6a76a39ceb5d1618b90ff7644977"

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

