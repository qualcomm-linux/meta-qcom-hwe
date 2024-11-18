inherit autotools pkgconfig
SUMMARY = "Pulseaudio pal plugins"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM += "file://module-pal-card/inc/pal-card.h;beginline=3;endline=4;md5=e382f520a6e20234a07a086090ad76df"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pulseaudio-plugin.git;protocol=https"
SRCBRANCH  = "audio-algos.lnx.1.0.r1-rel"
SRCREV     = "47f57bb8df85b134c36e1db65cb1368cfa9cda32"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pulseaudio-plugins"

S = "${WORKDIR}/audio/opensource/pulseaudio-plugins/modules/pa-pal-plugins"

DEPENDS = "qcom-agm pulseaudio qcom-pal"
EXTRA_OECONF = " --with-pa_version=15.0 --without-pa-support-card-status"

PACKAGES =+ "\
             pulseaudio-module-pal-card \
             pulseaudio-module-pal-voiceui-card \
            "
FILES:pulseaudio-module-pal-card = "${libdir}/pulse-15.0/modules/module-pal-card.so ${sysconfdir}/*"
FILES:pulseaudio-module-pal-voiceui-card = "${libdir}/pulse-15.0/modules/module-pal-voiceui-card.so"

RDEPENDS:pulseaudio-server = " pulseaudio-module-pal-card"
RDEPENDS:pulseaudio-server = " pulseaudio-module-pal-voiceui-card"

ALLOW_EMPTY:${PN} = "1"
