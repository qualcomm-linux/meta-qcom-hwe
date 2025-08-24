SUMMARY = "AGM for AROSP"

LICENSE = "BSD-3-Clause & LGPL-2.1-only"
LIC_FILES_CHKSUM += "file://service/src/agm.c;beginline=30;endline=31;md5=c901025e24b8cbc3b2ec2714b0571261 \
                     file://service/inc/public/agm/agm_api.h;beginline=31;endline=32;md5=c901025e24b8cbc3b2ec2714b0571261"

inherit autotools pkgconfig

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/agm.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "c34bbd51fb9797ce592d74e22c25af70c4746415"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/agm"

S = "${WORKDIR}/audio/opensource/agm"

DEPENDS = "glib-2.0 qcom-kvh2xml tinyalsa tinycompress qcom-args expat"
EXTRA_OECONF += "--with-glib --with-syslog --with-agm-no-ipc"
SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
