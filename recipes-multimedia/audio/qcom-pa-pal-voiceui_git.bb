inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "Voice UI test app"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pulseaudio-plugin.git;protocol=https"
SRCBRANCH  = "audio-algos.lnx.1.0.r1-rel"
SRCREV     = "35adad85a280ea8dc72f5687c1a70ddc9833758c"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pulseaudio-plugins \
    file://0001-Remove-__unused-macro.patch;patchdir=${UNPACKDIR}/audio/opensource/pulseaudio-plugins \
    file://0002-Use-0-over-NULL-for-int-type.patch;patchdir=${UNPACKDIR}/audio/opensource/pulseaudio-plugins \
"

S = "${UNPACKDIR}/audio/opensource/pulseaudio-plugins/utils/pa_pal_voiceui"

DEPENDS = "pulseaudio glib-2.0 qcom-pal qcom-pal-headers qcom-vui-interface-header"

EXTRA_OECONF = "--with-glib"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
