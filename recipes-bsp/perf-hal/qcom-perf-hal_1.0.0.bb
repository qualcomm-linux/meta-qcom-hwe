inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe created by PERF"

DEPENDS += "glib-2.0 libxml2 property-vault syslog-plumber"

RDEPENDS:${PN} = "property-vault"


PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "b7323c1fe83a0bd06b8b39d07e0fcab23d5262de6132173e0e36dda41eec834a"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += " /etc/*"
FILES:${PN} += " ${libdir} ${includedir}"
FILES:${PN} += " ${systemd_system_unitdir}"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
