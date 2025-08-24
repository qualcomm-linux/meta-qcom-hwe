inherit autotools pkgconfig systemd

DESCRIPTION = "pal"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://Pal.cpp;beginline=31;endline=32;md5=c83ea7207cf32b515967896ac7a5f0bc \
                     file://inc/PalDefs.h;beginline=30;endline=31;md5=c83ea7207cf32b515967896ac7a5f0bc"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "1c7e1da83a1b36d9dec6daf8f2259735c10d4f87"

SRC_URI  = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/arpal-lx \
            file://adsprpcd_audiopd.service"

S = "${WORKDIR}/audio/opensource/arpal-lx"

DEPENDS = "tinyalsa tinycompress qcom-agm qcom-kvh2xml qcom-audioroute fastrpc qcom-pal-headers"

EXTRA_OECONF += " --with-glib --with-syslog"

SYSTEMD_SERVICE:${PN} += "adsprpcd_audiopd.service"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/adsprpcd_audiopd.service ${D}${systemd_system_unitdir}
    install -d ${D}${systemd_system_unitdir}/multi-user.target.wants/
    ln -sf ${systemd_system_unitdir}/adsprpcd_audiopd.service \
           ${D}${systemd_system_unitdir}/multi-user.target.wants/adsprpcd_audiopd.service
}

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
