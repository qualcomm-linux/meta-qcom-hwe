SUMMARY = "system logging helper"
DESCRIPTION = "Utility to redirect ALOG style logs into syslog buffers"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

COMPATIBLE_MACHINE = "(qcom)"

DEPENDS = "logrotate"

SRCPROJECT = "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https"
SRCBRANCH  = "le-utils.qclinux.1.0.r2-rel"
SRCREV     = "7d67737c7d8ced20c51d82ad4f15f1686afd3524"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=le-utils"

S = "${WORKDIR}/le-utils/syslog-plumber"

do_install() {
    install -d ${D}${includedir}
    install -m 0755 ${S}/include/log.h ${D}${includedir}/
}
