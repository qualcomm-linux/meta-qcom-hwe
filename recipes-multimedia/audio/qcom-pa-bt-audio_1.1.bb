inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "pulseaudio BT audio interface"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pulseaudio-plugin.git;protocol=https"
SRCBRANCH  = "audio-algos.lnx.1.0.r1-rel"
SRCREV     = "bb51f6b4f907956d5eeed1190b97242bc7ea7aa2"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pulseaudio-plugins"

S = "${WORKDIR}/audio/opensource/pulseaudio-plugins/utils/pa_bt_audio"

DEPENDS = "pulseaudio"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
