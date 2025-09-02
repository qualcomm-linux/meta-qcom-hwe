DESCRIPTION = "QCOM Display devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/\
${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/display-devicetree.git;protocol=https"
SRCBRANCH  = "display-kernel.qclinux.1.0.r2-rel"
SRCREV     = "05bdee019918f932fdcb48f2e7ed251b2bd12542"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=display/vendor/qcom/opensource/display-devicetree"
S = "${WORKDIR}/display/vendor/qcom/opensource/display-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display-idp-amoled
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display-rb3
    oe_runmake ${EXTRA_OEMAKE} qcs8550-display
    oe_runmake ${EXTRA_OEMAKE} sa8775p-display
    oe_runmake ${EXTRA_OEMAKE} qcs9075-display-rb8
    oe_runmake ${EXTRA_OEMAKE} qcs9100-display-custom
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 \
    ${S}/display/*.dtbo \
    ${DEPLOYDIR}/tech_dtbs/
}
addtask do_deploy after do_install
