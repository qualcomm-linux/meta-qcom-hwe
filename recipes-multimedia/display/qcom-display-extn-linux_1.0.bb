inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "libsdmextension Library"

DEPENDS += "qcom-display-hal-linux jsoncpp glib-2.0 qcom-display-color-linux property-vault libdrm openssl linux-kernel-qcom-headers syslog-plumber"

QCM6490_SHA256SUM = "5a7eea526cc3231f9149b4a95eea21ac366f41af2b3d0b289c0856445bca1567"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
FILES:${PN}  += " /usr/data/display/* "
FILES:${PN}  += " ${libdir}/* "
FILES:${PN}-dev  = " ${includedir}/* "
FILES:${PN}-dbg  = " ${libdir}/.debug/* "
INSANE_SKIP:${PN} = "dev-so"

