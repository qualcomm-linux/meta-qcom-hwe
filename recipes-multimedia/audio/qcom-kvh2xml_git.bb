inherit autotools pkgconfig

DESCRIPTION = "kvh2xml header"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://kvh2xml.h;beginline=2;endline=8;md5=88e3e0aeaa9486299de4f95a4e561e34"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audioreach-conf.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "4d9d2b4334a47023914f5925c0724c733ebb6d49"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audioreach-conf"

S = "${WORKDIR}/audio/opensource/audioreach-conf/acdbdata"

do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"
