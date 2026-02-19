LICENSE = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Start up script for secure file system configuration"

SRC_URI +="file://sfs_config"
SRC_URI +="file://sfsconfig.service"
PACKAGE_ARCH = "${MACHINE_ARCH}"

INITSCRIPT_NAME = "sfs_config"
INITSCRIPT_PARAMS = "start 80 2 3 4 5 . stop 20 0 1 6 ."

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN} += "${sysconfdir}/initscripts/${INITSCRIPT_NAME}"

do_install:append() {

    install -m 0755 ${WORKDIR}/sfs_config -D ${D}${sysconfdir}/initscripts/${INITSCRIPT_NAME}
    install -m 0644 ${WORKDIR}/sfsconfig.service -D ${D}${systemd_unitdir}/system/sfsconfig.service

    # Enable the service for multi-user.target
    install -d ${D}/etc/systemd/system/multi-user.target.wants/
    ln -sf ${systemd_unitdir}/system/sfsconfig.service ${D}/etc/systemd/system/multi-user.target.wants/sfsconfig.service
}

SYSTEMD_SERVICE:${PN} = "sfsconfig.service"
