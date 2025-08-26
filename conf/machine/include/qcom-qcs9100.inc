DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

SOC_FAMILY = "qcs9100"
require conf/machine/include/qcom-base.inc

# Additional Kernel cmdline parameters for debug builds
DBG_CMDLINE = "${@oe.utils.conditional('DEBUG_BUILD','1','earlycon reboot=panic_warm page_owner=on qcom_scm.download_mode=1 slub_debug=FZP,zs_handle,zspage;FZPU','',d)}"

KERNEL_CMDLINE_EXTRA ?= "pcie_pme=nomsi net.ifnames=0 pci=noaer kpti=off kasan=off kasan.stacktrace=off swiotlb=128 ${DBG_CMDLINE} mitigations=auto rcupdate.rcu_expedited=1 rcu_nocbs=0-7 no-steal-acc vfio_iommu_type1.allow_unsafe_interrupts=1 fw_devlink.strict=1 vfio_platform.reset_required=0 amc6821.pwminv=1"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    fastrpc \
    kernel-modules \
    pd-mapper \
    modemmanager \
    networkmanager-nmcli \
    networkmanager-wifi \
    qrtr \
"

MACHINE_EXTRA_RDEPENDS += " \
    packagegroup-firmware-qcs9100 \
"

MACHINE_EXTRA_RRECOMMENDS:append:qcom-custom-bsp = " \
    qcom-subsystem-ramdump \
    trace-cmd \
    remote-debug-agent \
    securedsp-example \
"
