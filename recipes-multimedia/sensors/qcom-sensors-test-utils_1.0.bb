inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-test-utils Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber qcom-sensors-utils"

QCM6490_SHA256SUM = "eed1e1d1db6f53a484d15201b69a940b2a7d19d61d7309ae64e92fb141f9a63e"
QCS615_SHA256SUM = "223b6eb7420b6bb2d96569700ccaf16f56aee26e5d6a07cb47b2baf58ac8db3f"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
