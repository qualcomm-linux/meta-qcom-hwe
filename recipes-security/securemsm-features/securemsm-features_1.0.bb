inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS = "libxml2 libdrm libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libvmmem qcom-libdmabufheap \
           securemsm-headers minkipc property-vault jsoncpp qmi-framework curl\
          "

QCM6490_SHA256SUM = "167ba5a7df656a606828fcf31429f737d9e6a85e5560fa2ee7fd0b4b69cb459c"
QCS9100_SHA256SUM = "6d866e6382fb21fe9437b83764be9ea11da4d0e7f20bd0cf7f1f65011cd77562"
QCS8300_SHA256SUM = "556305a9d11c55b1211a409fc8dc11edfc8d1e93a1e2a9035573a4e9b75f5fc0"

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

