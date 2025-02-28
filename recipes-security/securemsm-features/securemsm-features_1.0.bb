inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS = "libxml2 libdrm libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libvmmem qcom-libdmabufheap \
           securemsm-headers minkipc property-vault jsoncpp qmi-framework curl\
          "

QCM6490_SHA256SUM = "c2d229139b74f985cce5fef153d806d59dc03cd340a44fc0cb21358338c59490"
QCS9100_SHA256SUM = "1cacd489a5dd8393505052e8fe6d6c65a5a04667b9f7c2e3988188d14ccd2034"
QCS8300_SHA256SUM = "171134947b52871e6fdeee70956288c220a53f24db6bbc181210ed4dda056bd5"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/usr/bin/"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "dev-deps"
INSANE_SKIP:${PN} += "debug-files"
INSANE_SKIP:${PN} += "file-rdeps"

