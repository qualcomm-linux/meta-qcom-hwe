inherit module

DESCRIPTION = "QCOM Graphics drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-kernel.git;protocol=https"
SRCBRANCH  = "gfx-kernel.le.0.0.r1-rel"
SRCREV     = "551d6331d467fb4d89a5c4f0fa8e150a2757d46f"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=graphics-kernel"

S = "${WORKDIR}/graphics-kernel"

do_install:append() {
     install -m 0644 ${THISDIR}/kgsl.rules -D ${D}${sysconfdir}/udev/rules.d/kgsl.rules
}

FILES:${PN} += "${sysconfdir}/udev/rules.d"
