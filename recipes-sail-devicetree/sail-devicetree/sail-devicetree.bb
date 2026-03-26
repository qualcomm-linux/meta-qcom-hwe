inherit module deploy

DESCRIPTION = "QCOM Sail-mailbox device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/sail-mailbox-devicetree.git;protocol=https"
SRCBRANCH  = "sail-mailbox-kernel.lnx.1.0.r1-rel"
SRCREV     = "317440253769bba76a25dd772279d3b542ef7121"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=sail-mailbox/sail-mb-devicetree"

S = "${WORKDIR}/sail-mailbox/sail-mb-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"

COMPATIBLE_MACHINE = "qcs9075|qcs9100|qcs8300"
LEMANS_BOARD_NAMES = "qcs9100-ride-sx|qcs9075-ride-sx|qcs9075-rb8-core-kit|qcs9075-iq-9075-evk|qcs9075-iq-9075-evk-ifp|qcs8275-iq-8275-evk-pro-sku"

python get_soc_family() {
    need_machine = d.getVar('COMPATIBLE_MACHINE')
    all_boards = d.getVar('LEMANS_BOARD_NAMES')

    import re
    compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
    for m in compat_machines:
        if re.match(need_machine, m):
            d.appendVar('SOC_FAM', m)
            break
    target_board = (d.getVar('MACHINEOVERRIDES') or "").split(":")
    for m in target_board:
        if re.match(all_boards, m):
            d.appendVar('TARGET_BOARD', m)
            break

    soc_family =  d.getVar('SOC_FAM')
}

do_compile[prefuncs] += "get_soc_family"

EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
        if [ "${SOC_FAM}" = "qcs9100" ]; then
            oe_runmake ${EXTRA_OEMAKE}  qcs9075-ride-sail
            oe_runmake ${EXTRA_OEMAKE}  qcs9100-ride-sail
            oe_runmake ${EXTRA_OEMAKE}  qcs9075-rb8-evk
        fi
        if [ "${SOC_FAM}" = "qcs8300" ]; then
	   oe_runmake ${EXTRA_OEMAKE} qcs8275-addons-iq-8275-evk_sailmb
	fi
}

do_install() {
	:
}

do_deploy() {
    echo "DTBO Staging path -> " ${DEPLOYDIR}/tech_dtbs
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 ${S}/*.dtbo ${DEPLOYDIR}/tech_dtbs
}
addtask do_deploy after do_install
