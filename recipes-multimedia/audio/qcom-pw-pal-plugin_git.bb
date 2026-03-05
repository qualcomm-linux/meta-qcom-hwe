inherit autotools pkgconfig

SUMMARY = "Pipewire pal plugins"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pipewire-plugin;protocol=https"
SRCBRANCH  = "pipewireaudio.lnx.1.0.r1-rel"
SRCREV     = "83cb12bd39043b0c9bdbf94c5eff80112c06e0ef"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pipewire-plugin"
SRC_URI += "\
    file://pipewire-pulse.service \
    file://pipewire-pulse.socket \
    file://98-qcom-pipewire.preset \
"

S = "${WORKDIR}/audio/opensource/pipewire-plugin/pipewire-plugin"

DEPENDS = "qcom-agm pipewire qcom-pal qcom-pal-headers"
TARGET_CFLAGS += "-I ${STAGING_DIR_TARGET}/usr/include/spa-0.2"
TARGET_CFLAGS += "-I ${STAGING_DIR_TARGET}/usr/include/pipewire-0.3"


SYSTEMD_PACKAGES += "${PN} ${PN}-systemd"
SYSTEMD_SERVICE:${PN} += "pipewire-pulse.socket"
SYSTEMD_SERVICE:${PN} += "98-qcom-pipewire.preset"
SYSTEMD_SERVICE:${PN} += " pipewire-pulse.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_AUTO_ENABLE:${PN}-systemd = "enable"

do_install:append:qcom () {
    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${nonarch_libdir}/systemd/system-preset
    install -d ${D}/usr/lib/pipewire-0.3
    install -m 0644 ${WORKDIR}/pipewire-pulse.service ${D}${systemd_system_unitdir}/pipewire-pulse.service
    install -Dm0644 ${WORKDIR}/98-qcom-pipewire.preset ${D}${nonarch_libdir}/systemd/system-preset/98-qcom-pipewire.preset
    install -Dm0644 ${WORKDIR}/pipewire-pulse.socket ${D}${systemd_system_unitdir}/pipewire-pulse.socket
    install -d ${D}${systemd_system_unitdir}/multi-user.target.wants/
    install -m 0755  ${D}/usr/lib/libpipewire-module-pal.so ${D}/usr/lib/pipewire-0.3/
    rm -f ${D}/usr/lib/libpipewire-module-pal.so
}

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/* /usr/lib/pipewire-0.3 /usr/lib/systemd/system-preset /usr/share/wireplumber/wireplumber.conf.d /usr/share/pipewire/pipewire.conf.d /usr/share/wireplumber/scripts"
FILES:${PN} += "${systemd_system_unitdir}/pipewire-pulse.service"
FILES:${PN} += "${systemd_system_unitdir}/pipewire-pulse.socket"
FILES_${PN} += "/usr/lib/systemd/system-preset/98-qcom-pipewire.preset"
FILES_${PN} += "/usr/share/wireplumber/wireplumber.conf.d/60-disable-alsa.conf"
FILES_${PN} += "/usr/share/wireplumber/wireplumber.conf.d/90-device-detection.conf"
FILES_${PN} += "/usr/share/wireplumber/scripts/90-device-detection.lua"
FILES_${PN} += "/usr/share/pipewire/pipewire.conf.d/pw-pal-plugin.conf"
FILES_${PN} += "/usr/share/pipewire/pipewire.conf.d/deep-buffer-combine-stream.conf"
FILES_${PN} += "/usr/share/pipewire/pipewire.conf.d/low-latency-combine-stream.conf"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
INSANE_SKIP:${PN} = "dev-so"
