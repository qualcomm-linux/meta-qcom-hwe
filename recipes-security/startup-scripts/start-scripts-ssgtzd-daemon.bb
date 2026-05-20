inherit update-rc.d systemd pkgconfig

LICENSE = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Start up script for ssgtzd daemon"

SRC_URI += "file://ssgtzd.service"

INITSCRIPT_NAME = "ssgtzd"

do_install:append() {
       install -d ${D}${systemd_unitdir}/system/
       install -m 0644 ${WORKDIR}/ssgtzd.service -D ${D}${systemd_unitdir}/system/ssgtzd.service
}

SYSTEMD_SERVICE:${PN} = "ssgtzd.service"
FILES:${PN} += "${systemd_unitdir}/system/*"
