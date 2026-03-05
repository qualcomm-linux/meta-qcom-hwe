inherit module deploy

DESCRIPTION = "Qualcomm Technologies, Inc. WLAN CLD3.0 low latency driver"
LICENSE = "ISC & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d \
                    file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "wlan-platform qcacld-wlan"

MODULE_NAME = "qca6490"

MODULE_NAME:qcm6490 := "qca6490"

QCOM_WLAN_QCACLD_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qcacld-3.0.git;protocol=https"
QCOM_WLAN_QCACLD_SRCBRANCH ?= "wlan-cld3.driver.lnx.2.0.14.r1-rel"
QCOM_WLAN_QCACLD_SRCREV    ?= "0e2f681122619d3537132f4dbb6f83960fcdbf4e"

QCOM_WLAN_HOST_CMN_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qca-wifi-host-cmn.git;protocol=https"
QCOM_WLAN_HOST_CMN_SRCBRANCH ?= "wlan-cmn.driver.lnx.2.0.14.r1-rel"
QCOM_WLAN_HOST_CMN_SRCREV    ?= "54d361cde69bc7d0e418bcbc8dce2de0d8438037"

QCOM_FW_API_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/fw-api.git;protocol=https"
QCOM_FW_API_SRCBRANCH ?= "wlan-api.lnx.1.0.r230-rel"
QCOM_FW_API_SRCREV    ?= "af2afe60cc2b73a5dfce9390bd487bda47e121ee"

QCOM_WLAN_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https"
QCOM_WLAN_SRCBRANCH ?= "wlan-platform.qclinux.1.0.r2-rel"
QCOM_WLAN_SRCREV    ?= "a9e3d31d5a0d08730842186c3cda7cc69560d94a"

SRCREV_FORMAT = "wlanqcacld_wlanhostcmn_fwapi_wlan"

SRCREV_wlanqcacld = "${QCOM_WLAN_QCACLD_SRCREV}"
SRCREV_wlanhostcmn = "${QCOM_WLAN_HOST_CMN_SRCREV}"
SRCREV_fwapi = "${QCOM_FW_API_SRCREV}"
SRCREV_wlan = "${QCOM_WLAN_SRCREV}"

SRC_URI = " \
    ${QCOM_WLAN_QCACLD_SRC};branch=${QCOM_WLAN_QCACLD_SRCBRANCH};name=wlanqcacld;destsuffix=wlan/qcacld-3.0 \
    ${QCOM_WLAN_HOST_CMN_SRC};branch=${QCOM_WLAN_HOST_CMN_SRCBRANCH};name=wlanhostcmn;destsuffix=wlan/qca-wifi-host-cmn \
    ${QCOM_FW_API_SRC};branch=${QCOM_FW_API_SRCBRANCH};name=fwapi;destsuffix=wlan/fw-api \
    ${QCOM_WLAN_SRC};branch=${QCOM_WLAN_SRCBRANCH};name=wlan;destsuffix=wlan/platform \
"
SRC_URI += "file://qcacld-kbuild.patch"

S = "${WORKDIR}/wlan/qcacld-3.0"

RPROVIDES:${PN} += "kernel-module-${MODULE_NAME}"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
EXTRA_OEMAKE += "CONFIG_CNSS_OUT_OF_TREE=y"
EXTRA_OEMAKE += "CONFIG_CNSS_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_PLAT_IPC_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_GENL=m"

python __anonymous () {
    d.setVar('KBUILD_EXTRA_SYMBOLS', d.getVar('STAGING_DIR_HOST') + "/usr/include"  + "/wlan-platform/Module.symvers" )
    machine = d.getVar('MACHINE')
    if "qcs6490" in machine or "qcm6490" in machine:
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_QCA6490=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS2=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS2_QMI=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_UTILS=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_WCNSS_MEM_PRE_ALLOC=m")
        d.appendVar('EXTRA_OEMAKE', " WLAN_CTRL_NAME=qca6490")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_QCA_CLD_WLAN_PROFILE=qca6490")
        d.appendVar('EXTRA_OEMAKE', " MODNAME=${MODULE_NAME}")
}

do_patch() {
     cd ${S}
     patch -p1 < ${WORKDIR}/qcacld-kbuild.patch
}

do_install:append() {
     mkdir -p ${D}/unstripped
     cp ${S}/${MODULE_NAME}.ko ${D}/unstripped/${MODULE_NAME}.ko
}

FILES:${PN}-dbg  = "/unstripped/*"

MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "modules_install"
