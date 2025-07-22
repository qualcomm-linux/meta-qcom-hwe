HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
DESCRIPTION = "U-Boot, a boot loader for Embedded boards based on PowerPC, \
ARM, MIPS and several other processors, which can be installed in a boot \
ROM and used to initialize and test the hardware or to download and run \
application code."
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native python3-setuptools-native"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
PE = "1"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "6d41f0a39d6423c8e57e92ebbe9f8c0333a63f72"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"

SRC_URI += "file://0001-PENDING-arm64-dts-qcom-qcs9100-Add-support-for-the-Q.patch \
	    file://0002-PENDING-configs-add-qcm6490_defconfig.patch \
	    file://0003-PENDING-clk-qcom-add-initial-clock-driver-for-sa8775.patch \
	    file://0004-PENDING-qcom_defconfig-enable-SA8775P-clocks.patch \
	    file://0005-PENDING-arm64-dts-qcom-qcs9100-Add-memory-entry-to-b.patch \
	    file://0006-PENDING-phy-qcom-Import-QMP-phy-related-header-files.patch \
	    file://0007-PENDING-phy-qcom-Add-QMP-UFS-PHY-driver-for-QCS9100.patch \
	    file://0008-PENDING-smem-Added-socinfo-header-file.patch \
	    file://0009-PENDING-arm64-dts-qcom-qcs9100-Added-memory-region-f.patch \
	    file://0010-PENDING-smem-Updated-smem-driver-to-support-QCS9100.patch \
	    file://0011-PENDING-efi_loader-Add-multidt-support.patch \
	    file://0012-PENDING-arm64-dts-qcom-qcs9100-Update-memory-map-as-.patch \
	    file://0013-PENDING-pinctr-qcom-sa8775p-Added-initial-support.patch \
	    file://0014-PENDING-config-qcom-add-sa8775p-to-qcom_defconfig.patch \
	    file://0015-PENDING-arm64-dts-qcom-qcs9100-Add-missed-DDR-memory.patch \
	    file://0016-PENDING-mach-snapdragon-update-environment-variable-.patch \
	    file://0017-PENDING-arm-lib-Add-EDL-reset-support-in-U-Boot-Enab.patch \
            file://0019-PENDING-mach-snapdragon-Add-OF_LIVE-fixup-handlers.patch \
            file://0020-PENDING-qcom_defconfig-Enable-CONFIG_OF_LIBFDT_OVERL.patch \
            file://0021-PENDING-Changes-to-enable-USB-HS-mode-in-Lemans.patch \
            file://0022-PENDING-Changes-to-enable-Fastboot-in-Lemans.patch \
            file://0023-PENDING-Changes-to-fix-bugs-related-to-Lemans-in-U-B.patch \
            file://0024-PENDING-mach-snapdragon-Save-environment-to-partitio.patch \
            file://0025-PENDING-mach-snapdragon-Support-for-boot-command-boo.patch \
            file://0026-PENDING-Changes-to-enable-fastboot-erase-command-for.patch \
            file://0027-PENDING-Changes-to-enable-scsi-erase-command-for-Lem.patch \
            file://0028-PENDING-Setting-bootargs-in-bootqli-command.patch \
            file://0029-PENDING-Change-to-enable-flashing-of-boot_a-and-syst.patch \
            file://0030-PENDING-Changes-to-enable-Verified-Boot-for-Lemans-i.patch \
            file://0031-PENDING-Update-memory-partition-info-in-dts-from-sme.patch \
            file://0032-PENDING-Fallback-action-for-u-boot-env.patch \
            file://0033-BACKPORT-UFS-multiple-LUNs-read-fix.patch \
            file://0034-PENDING-Verified-Boot-Add-public-key-info-in-u-boot-.patch \
            file://0035-PENDING-Add-a-weak-extra-fixup_dt_handler.patch \
            file://0036-PENDING-I2C-driver-and-IO-Expander-support-for-qcs91.patch \
            file://0037-PENDING-Enable-pmm8654au-rpmh-regulators-support.patch \
            file://0038-PENDING-Added-Reboot-bootloader-support.patch \
            file://0039-PENDING-Enable-QUP-SE-clocks-for-I2C-support-in-qcs9.patch \
            file://0041-PENDING-Enable-IO-Expander-for-Display-support-in-qc.patch \
            file://0042-PENDING-Add-QCOM-RPMH-Power-Domain-Driver-Support.patch \
            file://0043-PENDING-Add-Display-driver-clock-support.patch \
            file://0044-PENDING-Add-fastboot-select-active-lun-command-suppo.patch \
            file://0045-PENDING-Update-fastboot-oem-format-command-to-flash-.patch \
            file://0046-PENDING-Fix-for-UFS-write-issue.patch \
            file://0047-PENDING-Added-I2C-read-issue-fix-in-qcs9100.patch \
            file://0048-PENDING-Removed-preboot-default-env-variable.patch \
            file://0049-PENDING-Create-Mask-for-gpio-input-bit-read.patch \
            file://0050-PENDING-Add-QCOM-Watchdog-Driver-Support.patch \
            file://0051-PENDING-Remove-unwanted-DTS-entries-and-configs-in-q.patch \
            file://0052-PENDING-Add-fastboot-variables-to-read-security-feat.patch \
            file://0053-PENDING-drivers-rng-Enable-hardware-random-number-ge.patch"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit pkgconfig

do_configure[cleandirs] = "${B}"
