LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

DESCRIPTION = "Mount EFI partition on /boot since the firmware is not yet providing \
the needed EFI variables for systemd-gpt-auto-generator to work."
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

SRC_URI = "file://boot.mount"

do_install () {
    install -d ${D}${systemd_unitdir}/system/local-fs.target.wants
    install -m 0644  ${WORKDIR}/boot.mount ${D}${systemd_unitdir}/system/boot.mount
    ln -sf ${systemd_unitdir}/system/boot.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/boot.mount
}

FILES:${PN} += "${systemd_unitdir}/*"