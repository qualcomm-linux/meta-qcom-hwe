inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber chicdk-autogen-kt glib-2.0 property-vault camxlib-kt cameradlkm fastrpc qcom-sensinghub qcom-sensors-utils qcom-sensors-core qmi-framework abseil-cpp"

QCM6490_SHA256SUM = "5835550a33362a398338966fc90614dd650607fd3c5c26b043b0d0ed12c08748"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /lib/firmware/*"

FILES:${PN}-dev = "/usr/include/*"
INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

