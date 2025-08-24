inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "Audio DAC Plugin"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https"
SRCBRANCH  = "audio-utils.lnx.1.0.r1-rel"
SRCREV     = "70dabd1cce2a6a62127355bca922a186597934d4"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audio-utils"

S = "${WORKDIR}/audio/opensource/audio-utils/audio-plugins/dac_plugin"

EXTRA_OECONF += " --with-versioned-lib "
EXTRA_OECONF:append:qcs8300 = " --enable-qcs8300=yes "

DEPENDS = "qcom-audio-plugin-headers"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
