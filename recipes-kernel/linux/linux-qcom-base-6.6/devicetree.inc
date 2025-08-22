#Patches to be applied on top of devicetree
FILESEXTRAPATHS:prepend := "${THISDIR}/devicetree:"

# qcs6490 dtsi patches
SRC_URI:append = " \
    file://qcs6490/0001-arm64-dts-qcom-sc7280-Add-UFS-nodes-for-sc7280-soc.patch \
    file://qcs6490/0001-arm64-dts-qcom-sc7280-Move-video-firmware-to-chrome-.patch \
    file://qcs6490/0001-arm64-dts-qcom-Use-QCOM_SCM_VMID-defines-for-qcom-vm.patch \
    file://qcs6490/0001-UPSTREAM-arm64-dts-qcom-sc7280-Add-capacity-and-DPC-.patch \
    file://qcs6490/0004-UPSTREAM-arm64-dts-qcom-sc7280-Remove-unused-second-.patch \
    file://qcs6490/0008-UPSTREAM-arm64-dts-qcom-sc7280-Use-WPSS-PAS-instead-.patch \
    file://qcs6490/0014-BACKPORT-arm64-dts-qcom-qcm6490-fairphone-fp5-Add-PM.patch \
    file://qcs6490/0015-UPSTREAM-arm64-dts-qcom-sc7280-Add-ports-subnodes-in.patch \
    "

# qcs6490 board dts patches
SRC_URI:append = " \
    file://qcs6490/0001-arm64-dts-qcom-Add-base-qcm6490-idp-board-dts.patch \
    file://qcs6490/0002-arm64-dts-qcom-Add-base-qcs6490-rb3gen2-board-dts.patch \
    file://addons/0019-QCLINUX-arm64-dts-qcom-Add-board-id-and-msm-id-for-R.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcm6490-idp-Correct-the-voltage-setti.patch \
    file://qcs6490/0002-arm64-dts-qcom-qcs6490-rb3gen2-Correct-the-voltage-s.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcm6490-idp-Update-protected-clocks-l.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcs6490-rb3gen2-Declare-GCC-clocks-pr.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcs6490-rb3gen2-Update-the-LPASS-audi.patch \
    file://qcs6490/0005-BACKPORT-arm64-dts-qcom-sc7280-move-MPSS-and-WPSS-me.patch \
    file://qcs6490/0009-BACKPORT-arm64-dts-qcom-sc7280-Add-ADSP-node.patch \
    file://qcs6490/0010-BACKPORT-arm64-dts-qcom-sc7280-Add-CDSP-node.patch \
    file://qcs6490/0011-UPSTREAM-arm64-dts-qcom-qcs6490-rb3gen2-Enable-adsp-.patch \
    file://qcs6490/0012-UPSTREAM-arm64-dts-qcom-qcs6490-rb3gen2-Add-DP-outpu.patch \
    file://qcs6490/0013-UPSTREAM-arm64-dts-qcom-qcs6490-rb3gen2-Introduce-US.patch \
    file://qcs6490/0016-UPSTREAM-arm64-dts-qcom-qcs6490-rb3gen2-Enable-USB-T.patch \
    file://qcs6490/0017-UPSTREAM-arm64-dts-qcom-qcs6490-rb3gen2-Enable-UFS.patch \
    file://qcs6490/0018-BACKPORT-arm64-dts-qcom-qcs6490-rb3gen2-Enable-vario.patch \
    "

# qcs9100 dtsi patches
SRC_URI:append = " \
    file://qcs9100/0001-Revert-arm64-dts-qcom-sa8775p-Update-sleep_clk-frequ.patch \
    file://qcs9100/0002-Revert-arm64-dts-qcom-move-common-parts-for-sa8775p-.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-add-ADSP-CDSP-and-GP.patch \
    file://qcs9100/0002-BACKPORT-arm64-dts-qcom-sa8775p-Add-llcc-support-for.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-Add-capacity-and-DPC.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-add-TRNG-node.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-enable-the-inline-cr.patch \
    file://qcs9100/0001-BACKPORT-arm64-dts-qcom-sa8775p-add-a-dedicated-memo.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-Add-CPU-and-LLCC-BWM.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-Add-coresight-nodes-for-SA87.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-Add-support-for-multimedia-c.patch \
    file://qcs9100/0002-QCLINUX-Revert-FROMLIST-arm64-dts-qcom-sa8775p-add-A.patch \
    file://qcs9100/0005-BACKPORT-arm64-dts-qcom-sa8775p-add-ADSP-CDSP-and-GP.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-fix-the-fastrpc-labe.patch \
    file://qcs9100/0002-UPSTREAM-arm64-qcom-sa8775p-Add-ADSP-and-CDSP0-fastr.patch \
    file://qcs9100/0003-FROMLIST-arm64-dts-qcom-sa8775p-Add-TCSR-halt-regist.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-sa8775p-pmics-enable-rtc.patch \
    file://qcs9100/0001-arm64-dts-qcom-sa8775p-Add-new-memory-map-updates-to.patch \
    file://qcs9100/0007-FROMLIST-arm64-dts-qcom-sa8775p-add-display-dt-nodes.patch \
    file://qcs9100/0006-FROMLIST-arm64-dts-qcom-sa8775p-add-DisplayPort-devi.patch \
    file://qcs9100/0002-PENDING-arm64-dts-qcom-iris-video-driver-for-sa8775p.patch \
    file://qcs9100/0003-FROMLIST-arm64-dts-qcom-sa8775p-Add-gpu-and-gmu-node.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-add-CPU-idle-states.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-sa8775p-Add-CPUs-to-psci-pow.patch \
    file://qcs9100/0001-FROMGIT-arm64-dts-qcom-sa8775p-add-QCrypto-nodes.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-fix-the-secure-devic.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-extend-the-register-.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-Populate-additional-.patch \
    file://qcs9100/0001-PENDING-arm64-dts-qcom-Add-support-for-MBG-TM-for-pm.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-add-mst-support-for-pixel-1-.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-add-mst-support-for-pixel-st.patch \
    file://qcs9100/0002-PENDING-arm64-dts-qcom-Add-L3-interconnect-device-fo.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-Add-CPU-OPP-tables-t.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-sa8775p-Add-LMH-interrupts-f.patch \
    file://qcs9100/0001-PENDING-arm64-dts-qcom-sa8775p-Add-gpr-node.patch \
    file://qcs9100/0002-Revert-PENDING-arm64-dts-qcom-iris-video-driver-for-.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-sa8775p-add-support-for-vide.patch \
    file://qcs9100/0011-UPSTREAM-arm64-dts-qcom-sa8775p-add-Display-Serial-Interface-device-nodes.patch \
    "
# qcs9100 board dts patches
SRC_URI:append = " \
    file://addons/0001-QCLINUX-arm64-dts-qcom-sa8775p-ride-Add-board-id-and.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-ride-enable-remotepr.patch \
    file://workarounds/0001-QCLINUX-arm64-dts-qcom-sa8775p-ride-Change-firmware-.patch \
    file://qcs9100/0004-FROMLIST-arm64-dts-qcom-sa8775p-ride-Add-QCS9100-com.patch \
    file://addons/0002-QCLINUX-arm64-dts-qcom-sa8775p-ride-Add-board-id-and.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-Update-sleep_clk-frequency-t.patch \
    file://workarounds/0001-QCLINUX-Revert-QCLINUX-arm64-dts-qcom-sa8775p-ride-C.patch \
    file://qcs9100/0003-QCLINUX-Revert-FROMLIST-arm64-dts-qcom-sa8775p-ride-.patch \
    file://qcs9100/0006-BACKPORT-Enable-all-remoteproc-nodes-on-the-sa8775p-.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-ride-add-WiFi-BT-nod.patch \
    file://qcs9100/0001-Revert-FROMLIST-arm64-dts-qcom-sa8775p-ride-add-WiFi.patch \
    file://qcs9100/0003-FROMGIT-BACKPORT-arm64-dts-qcom-move-common-parts-fo.patch \
    file://qcs9100/0004-FROMGIT-arm64-dts-qcom-sa8775p-ride-enable-remotepro.patch \
    file://qcs9100/0005-FROMGIT-arm64-dts-qcom-sa8775p-Add-PCIe-bridge-node.patch \
    file://qcs9100/0006-FROMLIST-BACKPORT-arm64-dts-qcom-sa8775p-ride-add-Wi.patch \
    file://qcs9100/0007-FROMGIT-arm64-dts-qcom-sa8775p-ride-r3-add-new-board.patch \
    file://qcs9100/0010-FROMLIST-arm64-dts-qcom-qcs9100-Add-support-for-the-.patch \
    file://qcs9100/0011-QCLINUX-arm64-dts-qcom-Add-Modify-board-id-and-msm-i.patch \
    file://qcs9100/0007-FROMLIST-arm64-dts-qcom-sa8775p-ride-Enable-Display-.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-Update-iommu-map-ent.patch \
    file://qcs9100/0005-FROMLIST-arm64-dts-qcom-sa8775p-ride-Add-PSCI-SYSTEM.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-qcs9100-Update-memory-map.patch \
    file://qcs9100/0004-arm64-dts-qcom-Add-support-for-QCS9075-RB8.patch \
    file://qcs9100/0005-arm64-dts-qcom-Add-support-for-QCS9075-Ride-Ride-.patch \
    file://qcs9100/0001-PENDING-arm64-dts-qcom-Enable-usb0-support-on-QCS907.patch \
    file://qcs9100/0012-FROMLIST-arm64-dts-qcom-sa8775p-ride-Add-firmware-name-in-BT-.patch \
    file://qcs9100/0001-QCLINUX-arm64-dts-qcom-sa8775p-Add-EL2-dtb-support.patch \
    file://qcs9100/0001-Revert-FROMLIST-arm64-dts-qcom-qcs9100-Update-memory.patch \
    file://qcs9100/0002-PENDING-arm64-dts-qcom-qcs9xxx-Update-memory-map-for.patch \
    file://qcs9100/0003-FROMLIST-arm64-dts-qcom-sa8775p-ride-enable-video.patch \
    file://qcs9100/0012-UPSTREAM-arm64-dts-qcom-sa8775p-ride-add-anx7625-DSI-to-DP-bridge-nodes.patch \
    "

# qcs615 dtsi patches
SRC_URI:append = " \
    file://qcs615/0020-FROMLIST-arm64-dts-qcom-add-initial-support-for-QCS6.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-add-the-SCM-node.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-add-the-APPS-SMMU-nod.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-LLCC-support-for-.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-QUPv3-configurati.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-add-UFS-node.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-SDHC1-and-SDHC2.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-add-AOSS_QMP-node.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-Add-coresight-nodes-for-QCS6.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-Add-clock-nodes-for-multimed.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Adds-SPMI-support.patch \
    file://qcs615/0005-FROMLIST-arm64-dts-qcom-qcs615-enable-pcie-for-qcs61.patch \
    file://qcs615/0001-BACKPORT-FROMLIST-arm64-dts-qcom-Add-support-for-usb.patch \
    file://qcs615/0008-FROMLIST-arm64-dts-qcom-Add-display-support-for-QCS615.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-add-venus-node-for-the-qcs61.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-Add-cpu-scaling-clock-node.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-ethernet-node.patch \
    file://qcs615/0001-QCLINUX-Revert-arm64-dts-qcom-qcs615-enable-PCIe-for.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-CPU-capacity-and-.patch \
    file://qcs615/0007-FROMLIST-arm64-dts-qcom-Add-DisplayPort-support-for-.patch \
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-qcs615-add-the-GPU-SMMU-node.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-dma-coherent-for-t.patch \
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-qcs615-Add-CPU-and-LLCC-BWMO.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-Add-gpu-and-gmu-nodes.patch \
    file://qcs615/0001-BACKPORT-FROMLIST-arm64-dts-qcom-qcs615-Add-support-.patch \
    file://qcs615/0003-FROMGIT-arm64-dts-qcom-correct-gpio-ranges-for-QCS61.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-TRNG-node.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-QCrypto-nodes.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-Command-DB-suppor.patch \
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-Enable-TSENS-support-for.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-Add-mproc-node-for-SE.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-Add-IMEM-and-PIL-info.patch \
    file://qcs615/0005-FROMLIST-arm64-dts-qcom-qcs615-add-ADSP-and-CDSP-nod.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-fix-a-crash-issue-caused-by-.patch \
    file://qcs615/0001-PENDING-arm64-dts-qcom-Enable-cpu-and-gpu-mitigation.patch \
    file://qcs615/0001-QCLINUX-Revert-FROMLIST-arm64-dts-qcom-qcs615-add-SD.patch \
    file://qcs615/0001-UPSTREAM-arm64-dts-qcom-qcs615-add-SDHC1-and-SDHC2.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-disable-the-CTI-devic.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-ADSP-and-CDSP-fas.patch \
    file://qcs615/0001-PENDING-arm64-dts-qcom-qcm615-Enable-tsens-thermal-z.patch \
    "

# qcs615 board dts patches
SRC_URI:append = " \
    file://qcs615/0021-FROMLIST-arm64-dts-qcom-add-base-QCS615-RIDE-dts.patch \
    file://addons/0001-QCLINUX-arm64-dts-qcom-qcs615-ride-add-board-id-and-.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-UFS-node.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-ethernet-.patch \
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-SDHC1-and-SDHC2.patch\
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-PMIC-peri.patch \
    file://qcs615/0006-FROMLIST-arm64-dts-qcom-qcs615-enable-pcie-for-qcs61.patch \
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-Enable-Primary-USB-controlle.patch \
    file://qcs615/0009-FROMLIST-arm64-dts-qcom-Add-display-support-for-QCS615-RIDE-b.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-ride-enable-venus-nod.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-WiFi-BT-nodes.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-ride-Fixing-power-iss.patch \
    file://qcs615/0008-FROMLIST-arm64-dts-qcom-Enable-DisplayPort-on-QCS615.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-Adreno-61.patch \
    file://qcs615/0002-BACKPORT-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-ride-Tune-RX-sampling.patch \
    file://qcs615/0022-FROMLIST-arm64-dts-qcom-Add-firmware-name-in-BT-no.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-ride-Add-PSCI-SYSTEM_.patch \
    file://qcs615/0006-FROMLIST-arm64-dts-qcom-qcs615-ride-enable-remotepro.patch \
    file://qcs615/0002-QCLINUX-Revert-FROMLIST-arm64-dts-qcom-qcs615-ride-E.patch \
    file://qcs615/0002-UPSTREAM-arm64-dts-qcom-qcs615-ride-enable-SDHC1-and.patch \
    "

# qcs8300 dtsi patches
SRC_URI:append = " \
    file://qcs8300/0025-FROMLIST-arm64-dts-qcom-add-initial-support-for-QCS8.patch \
    file://qcs8300/0005-FROMLIST-arm64-dts-qcom-qcs8300-Add-LLCC-support-for.patch \
    file://qcs8300/0006-FROMLIST-arm64-dts-qcom-qcs8300-Add-PMU-support-for-.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-Add-coresight-nodes-for-QCS8.patch \
    file://qcs8300/0002-FROMLIST-arm64-dts-qcom-qcs8300-Add-watchdog-node.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-Add-support-for-usb-nodes-on.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-Add-ADSP-and-CDSP0-f.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-Correct-IRQ-number-of-EL2-non-secure-.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-add-TRNG-node.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-add-QCrypto-nodes.patch \
    file://qcs8300/0002-FROMLIST-arm64-dts-qcom-qcs8300-Add-QUPv3-configurat.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-Add-capacity-and-DPC.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-Add-support-for-cloc.patch \
    file://qcs8300/0001-PENDING-arm64-dts-qcom-qcs8300-add-video-codec-node.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-enable-the-inline-cr.patch \
    file://qcs8300/0006-FROMLIST-arm64-dts-qcom-qcs8300-add-display-dt-nodes.patch \
    file://qcs8300/0001-arm64-dts-qcom-qcs8300-add-the-first-2.5G-ethernet.patch \
    file://qcs8300/0005-arm64-dts-qcom-qcs8300-enable-pcie0-for-qcs8300-soc.patch \
    file://qcs8300/0007-arm64-dts-qcom-qcs8300-enable-pcie1-for-qcs8300-soc.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-add-the-pcie-smmu-no.patch \
    file://qcs8300/0004-FROMLIST-arm64-dts-qcom-qcs8300-add-support-for-stre.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-Adds-SPMI-support.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-add-a-PCIe-port-for-.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-qcs8300-Add-device-node-for-.patch \
    file://qcs8300/0004-FROMLIST-arm64-dts-qcom-qcs8300-Add-gpu-and-gmu-node.patch \
    file://qcs8300/0004-PENDING-arm64-dts-qcom-Add-L3-interconnect-device-fo.patch \
    file://qcs8300/0003-PENDING-arm64-dts-qcom-qcs8300-Add-support-for-cpufr.patch \
    file://qcs8300/0004-PENDING-arm64-dts-qcom-qcs8300-Add-LMH-interrupts-fo.patch \
    file://qcs8300/0005-PENDING-arm64-dts-qcom-Add-static-vote-for-QCS8300.patch \
    file://qcs8300/0006-PENDING-arm64-dts-qcom-qcs8300-Add-support-to-scale-.patch \
    file://qcs8300/0001-UPSTREAM-arm64-dts-qcom-Add-support-for-usb-nodes-on.patch \
    file://qcs8300/0001-Revert-PENDING-arm64-dts-qcom-qcs8300-add-video-code.patch \
    file://qcs8300/0004-FROMLIST-arm64-dts-qcom-qcs8300-add-support-for-vide.patch \
    "

# qcs8300 board dts patches
SRC_URI:append = " \
    file://qcs8300/0026-FROMLIST-arm64-dts-qcom-add-base-QCS8300-RIDE-dts.patch \
    file://addons/0001-QCLINUX-arm64-dts-qcom-qcs8300-ride-add-board-id-and.patch \
    file://qcs8300/0003-FROMLIST-arm64-dts-qcom-qcs8300-ride-Add-watchdog-no.patch \
    file://qcs8300/0002-FROMLIST-arm64-dts-qcom-Enable-USB-controllers-for-Q.patch \
    file://qcs8300/0001-PENDING-arm64-dts-qcom-qcs8300-ride-Add-PSCI-SYSTEM_.patch \
    file://qcs8300/0007-BACKPORT-FROMLIST-arm64-dts-qcom-qcs8300-ride-Enable.patch \
    file://qcs8300/0002-arm64-dts-qcom-qcs8300-ride-enable-ethernet0.patch \
    file://qcs8300/0006-arm64-dts-qcom-qcs8300-enable-pcie0-for-qcs8300-plat.patch \
    file://qcs8300/0008-arm64-dts-qcom-qcs8300-enable-pcie1-for-qcs8300-plat.patch \
    file://qcs8300/0002-FROMLIST-arm64-dts-qcom-qcs8300-ride-Enable-PMIC-per.patch \
    file://qcs8300/0001-PENDING-arm64-dts-qcom-Add-support-for-PMIC-peripher.patch \
    file://qcs8300/0002-FROMLIST-arm64-dts-qcom-qcs8300-ride-enable-WLAN-on-.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-add-BT-node-in-QCS8300-RIDE-.patch \
    file://qcs8300/0005-FROMLIST-arm64-dts-qcom-qcs8300-ride-Enable-Adreno-6.patch \
    file://qcs8300/0001-UPSTREAM-arm64-dts-qcom-qcs8300-ride-Enable-second-U.patch \
    file://qcs8300/0005-FROMLIST-arm64-dts-qcom-qcs8300-ride-enable-video.patch \
    "
# qcs9075 board dts patches
SRC_URI:append = " \
    file://qcs9075/0001-UPSTREAM-media-qcom-camss-Amalgamate-struct-resource.patch \
    file://qcs9075/0002-UPSTREAM-media-qcom-camss-use-1X16-formats-instead-o.patch \
    file://qcs9075/0003-UPSTREAM-media-qcom-camss-Rename-camss-struct-resour.patch \
    file://qcs9075/0004-UPSTREAM-media-qcom-camss-Start-to-move-to-module-co.patch \
    file://qcs9075/0005-UPSTREAM-media-qcom-camss-Pass-icc-bandwidth-table-a.patch \
    file://qcs9075/0006-UPSTREAM-media-qcom-camss-Pass-remainder-of-variable.patch \
    file://qcs9075/0007-UPSTREAM-media-qcom-camss-Pass-line_num-from-compat-.patch \
    file://qcs9075/0008-UPSTREAM-media-qcom-camss-Pass-CAMSS-subdev-callback.patch \
    file://qcs9075/0009-UPSTREAM-media-qcom-camss-Assign-the-correct-number-.patch \
    file://qcs9075/0010-UPSTREAM-media-qcom-camss-Remove-special-case-for-VF.patch \
    file://qcs9075/0011-UPSTREAM-media-qcom-camss-Untangle-if-else-spaghetti.patch \
    file://qcs9075/0012-UPSTREAM-media-qcom-camss-Allow-clocks-vfeN-vfe_lite.patch \
    file://qcs9075/0013-UPSTREAM-media-qcom-camss-Functionally-decompose-CSI.patch \
    file://qcs9075/0014-UPSTREAM-media-qcom-camss-Fix-support-for-setting-CS.patch \
    file://qcs9075/0015-UPSTREAM-media-qcom-camss-Support-RDI3-for-VFE-17x.patch \
    file://qcs9075/0016-UPSTREAM-media-qcom-camss-Move-vfe_disable-into-a-co.patch \
    file://qcs9075/0017-UPSTREAM-media-qcom-camss-Propagate-vfe_reset-error-.patch \
    file://qcs9075/0018-UPSTREAM-media-qcom-camss-Add-per-sub-device-type-re.patch \
    file://qcs9075/0019-UPSTREAM-media-qcom-camss-Attach-formats-to-VFE-reso.patch \
    file://qcs9075/0020-UPSTREAM-media-qcom-camss-Attach-formats-to-CSID-res.patch \
    file://qcs9075/0021-UPSTREAM-media-qcom-camss-Move-format-related-functi.patch \
    file://qcs9075/0022-PENDING-media-qcom-camss-Designate-lite-subdevices-i.patch \
    file://qcs9075/0023-UPSTREAM-media-qcom-camss-Split-testgen-RDI-and-RX-f.patch \
    file://qcs9075/0024-UPSTREAM-media-qcom-camss-Decompose-register-and-lin.patch \
    file://qcs9075/0025-UPSTREAM-media-qcom-camss-clean-up-a-check.patch \
    file://qcs9075/0026-UPSTREAM-media-qcom-camss-vfe-17x-Rename-camss-vfe-1.patch \
    file://qcs9075/0027-UPSTREAM-media-qcom-camss-Convert-to-per-VFE-pointer.patch \
    file://qcs9075/0028-UPSTREAM-media-qcom-camss-Use-common-VFE-pm_domain_o.patch \
    file://qcs9075/0029-UPSTREAM-media-qcom-camss-Move-VFE-power-domain-spec.patch \
    file://qcs9075/0030-UPSTREAM-media-qcom-camss-Flag-CSID-lites-to-support.patch \
    file://qcs9075/0031-UPSTREAM-media-qcom-camss-Add-sm8250-named-power-dom.patch \
    file://qcs9075/0032-UPSTREAM-media-qcom-camss-csiphy-3ph-Add-Gen2-v1.1-t.patch \
    file://qcs9075/0033-UPSTREAM-media-qcom-camss-Flag-which-VFEs-require-a-.patch \
    file://qcs9075/0034-UPSTREAM-media-qcom-camss-Add-support-for-named-powe.patch \
    file://qcs9075/0035-UPSTREAM-media-qcom-camss-Flag-VFE-lites-to-support-.patch \
    file://qcs9075/0036-UPSTREAM-media-qcom-camss-Attach-formats-to-CSIPHY-r.patch \
    file://qcs9075/0037-UPSTREAM-media-qcom-camss-Decouple-VFE-from-CSID.patch \
    file://qcs9075/0038-UPSTREAM-media-qcom-camss-reducing-the-repitious-err.patch \
    file://qcs9075/0039-UPSTREAM-media-qcom-camss-Restructure-camss_link_ent.patch \
    file://qcs9075/0040-PENDING-media-dt-bindings-Add-qcom-sa8775p-camss.patch \
    file://qcs9075/0041-PENDING-media-qcom-camss-Add-support-for-camss-drive.patch \
    file://qcs9075/0042-PENDING-arm64-dts-qcom-sa8775p-Add-support-for-camss.patch \
    file://qcs9075/0043-PENDING-add-TPG-driver-on-camss-for-sa8775p.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-Enable-camss-for-sa8775p-ride.patch \
    file://qcs9075/0001-PENDING-media-dt-bindings-Add-qcom-qcs8300-camss.patch \
    file://qcs9075/0002-PENDING-arm64-dts-qcom-qcs8300-Add-support-for-camss.patch \
    file://qcs9075/0003-PENDING-media-qcom-camss-Add-support-for-camss-drive.patch \
    file://qcs9075/0004-PENDING-add-TPG-support-on-camss-for-qcs8300.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-Enable-camss-for-qcs8300-ride.patch \
    file://qcs9075/0008-PENDING-arm64-dts-qcom-qcs9075-rb8-QUP-Firmware-load.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-rb8-Enable-PCIe0-PCIe.patch \
    file://addons/0001-QCLINUX-arm64-dts-qcom-Add-msm-id-board-id-for-qcs90.patch \
    file://qcs9075/0002-PENDING-arm64-dts-qcom-qcs9075-rb8-Add-support-for-r.patch \
    file://qcs9075/0003-PENDING-arm64-dts-qcom-qcs9075-rb8-Enable-llcc-node.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-Enable-all-3-usb-controllers-.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-Add-UFS-support-for-Q.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-Enable-ethernet-on-QC.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-Enable-camss-for-qcs9075-rb8.patch \
    file://qcs9075/0044-PENDING-arm64-dts-qcom-qcs9075-rb8-Add-support-for-W.patch \
    file://qcs9075/0002-PENDING-arm64-dts-qcom-Rename-qcs9075-rb8-to-qcs9075.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-iq-9075-evk-Add-PSCI-SYSTEM_R.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-iq-9075-evk-Add-sound.patch \
    file://qcs9075/0045-FROMLIST-arm64-qcs9075-evk-remove-uart-bt-en-and-b.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-iq-9075-evk-Add-firmw.patch \
    file://qcs9075/0046-PENDING-arm64-dts-qcom-Add-sdcard-support-for-qcs907.patch \
    file://qcs9075/0047-PENDING-arm64-dts-qcom-Enable-vqmmc-voltage-switchin.patch \
    "

# qcs8275 board dts patches
SRC_URI:append = " \
    file://qcs8275/0001-PENDING-arm64-dts-qcom-Add-support-for-QCS8275-IQ8-E.patch \
    file://addons/0002-QCLINUX-arm64-dts-qcom-Add-msm-id-board-id-for-qcs82.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-iq-9075-evk-Enable.patch \
    file://qcs9075/0001-PENDING-arm64-dts-qcom-qcs9075-iq-9075-evk-enable-vi.patch \
    file://qcs8275/0001-QCLINUX-arm64-dts-fix-base-BSP-compilation-issue-for.patch \
    file://qcs8275/0001-PENDING-arm64-dts-qcom-Enable-usb0-support-on-QCS827.patch \
    file://qcs8275/0001-PENDING-arm64-dts-qcom-qcs8275-iq-8275-evk-Add-firmw.patch \
    "
