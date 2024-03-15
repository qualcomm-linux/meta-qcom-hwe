inherit module

DESCRIPTION = "QCOM Graphics drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-kernel.git;protocol=https;rev=0117dc21f91e92b83d962b2aba9d1aad10452dd4;branch=gfx-kernel.le.0.0.r1-rel;destsuffix=graphics-kernel"

SRC_URI:append:qcs8550 = " file://kgsl_aim300_bringup.patch"

S = "${WORKDIR}/graphics-kernel"