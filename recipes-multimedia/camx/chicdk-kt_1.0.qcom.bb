inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camx-kt virtual/libgles2 virtual/egl adrenocl qcom-fastcv-binaries"

QCM6490_SHA256SUM = "771b43693f6de00cadda63cc39fed1a263e12542eb0a9dcb5955c3a6ac0e1f95"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/lib/rfsa/adsp/* \
    /usr/include/* \
    /lib/firmware/* \
    /system/etc/camera/* "
FILES:${PN}-dev = ""


INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "already-stripped"
#The modules require .so to be dynamicaly loaded
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "file-rdeps"
