inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe created by PERF"

DEPENDS += "glib-2.0 libxml2 property-vault syslog-plumber"

RDEPENDS:${PN} = "property-vault"


PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "b3f2da959b2201356ba4ca904083cb6f918617906caea41f14aba87ab8209322"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += " /etc/*"
FILES:${PN} += " ${libdir} ${includedir}"
FILES:${PN} += " ${systemd_system_unitdir}"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
