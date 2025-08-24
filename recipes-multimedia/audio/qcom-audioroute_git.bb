inherit autotools pkgconfig

SUMMARY = "Audioroute for AROSP"
LICENSE = "Apache-2.0 & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://audio_route.c;beginline=2;endline=20;md5=84ef494e74239b3871a4da47f3702691"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https"
SRCBRANCH  = "audio-utils.lnx.1.0.r1-rel"
SRCREV     = "70dabd1cce2a6a62127355bca922a186597934d4"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audio-utils"

S = "${WORKDIR}/audio/opensource/audio-utils/audio-route"

DEPENDS = "glib-2.0 tinyalsa expat"

SOLIBS = ".so*"

FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} = "dev-so"
