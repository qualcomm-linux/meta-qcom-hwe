inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "pulseaudio BT audio interface"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pulseaudio-plugin.git;protocol=https"
SRCBRANCH  = "audio-algos.lnx.1.0.r1-rel"
SRCREV     = "54111949a80ca10ded32b31035a4e3d1183f68e1"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pulseaudio-plugins \
    file://0001-Have-HAVE_SYS_SOCKET_H-defined-by-configure.patch;patchdir=${UNPACKDIR}/audio/opensource/pulseaudio-plugins \
"

S = "${UNPACKDIR}/audio/opensource/pulseaudio-plugins/utils/pa_bt_audio"

DEPENDS = "pulseaudio"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
