inherit autotools pkgconfig
SUMMARY = "Pipewire pal plugins"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-qti-bsp/files/common-licenses/\
${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pulseaudio-plugin.git;protocol=https"
SRCBRANCH  = "pulseaudio-plugins.lnx.1.0"
SRCREV     = "a2e9d5c0f8b033f59d0e121e95ebab78f53584c3"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pulseaudio-plugins/pipewire-plugin"

S = "${WORKDIR}/audio/opensource/pulseaudio-plugins/pipewire-plugin"

DEPENDS = "qcom-agm pipewire qcom-pal qcom-pal-headers"
TARGET_CFLAGS += "-I ${STAGING_DIR_TARGET}/usr/include/spa-0.2"
TARGET_CFLAGS += "-I ${STAGING_DIR_TARGET}/usr/include/pipewire-0.3"


do_install:append:qcm6490() {
    install -d ${D}/usr/lib/pipewire-0.3
    install -d ${D}/usr/share/pipewire/pipewire.conf.d
    install -m 0755  ${D}/usr/lib/libpipewire-module-pal.so ${D}/usr/lib/pipewire-0.3/
    rm -f ${D}/usr/lib/libpipewire-module-pal.so
}


SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/libpipewire-module-pal.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/* /usr/lib/pipewire-0.3"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
INSANE_SKIP:${PN} = "dev-so"
