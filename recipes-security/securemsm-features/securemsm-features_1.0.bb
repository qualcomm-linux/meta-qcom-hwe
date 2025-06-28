inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS = "libxml2 libdrm libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libvmmem qcom-libdmabufheap \
           securemsm-headers minkipc property-vault jsoncpp qmi-framework curl\
          "

QCM6490_SHA256SUM = "08fbbb4f92a351af43aeeb51aedc47ae03f41a6008f5c3a2c1bf95be0e4cc359"
QCS9100_SHA256SUM = "c5025125e228ce582b513fba1a51b2d0716cd73d32a1c626152f45c86617f8e2"
QCS8300_SHA256SUM = "c139aa145c441965650ff764817fe72529f7890075325478bc7f7f6c0ecd4b2e"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

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

