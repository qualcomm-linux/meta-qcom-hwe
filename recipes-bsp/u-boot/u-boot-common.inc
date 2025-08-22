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
SRCREV = "bd0ade7d090a334b3986936d63a34001d99722ad"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"

SRC_URI += " \
            file://0001-PENDING-smem-Added-socinfo-header-file.patch \
            file://0002-PENDING-arm64-dts-qcom-qcs9100-Added-memory-region-f.patch \
            file://0003-PENDING-smem-Updated-smem-driver-to-support-QCS9100.patch \
            file://0004-PENDING-efi_loader-Add-multidt-support.patch \
            file://0005-PENDING-mach-snapdragon-Add-OF_LIVE-fixup-handlers.patch \
            file://0006-PENDING-qcom_defconfig-Enable-CONFIG_OF_LIBFDT_OVERL.patch \
            file://0007-PENDING-Update-memory-partition-info-in-dtb-from-sme.patch \
            file://0008-PENDING-mach-snapdragon-update-environment-variable-.patch \
            file://0009-PENDING-Changes-to-enable-USB-HS-mode-in-Lemans.patch \
            file://0010-SUBMITTED-Changes-to-fix-bugs-related-to-Lemans-in-U.patch \
            file://0011-SUBMITTED-arm-psci-Add-API-to-check-for-support-of-s.patch \
            file://0012-SUBMITTED-drivers-sysreset-Add-sysreset-op-that-can-.patch \
            file://0013-SUBMITTED-sysreset-Implement-PSCI-based-reset-to-EDL.patch \
            file://0014-SUBMITTED-cmd-boot-Add-edl-option-to-reset-command-d.patch \
            file://0015-SUBMITTED-configs-qcs9100_defconfig-Enable-SYSRESET.patch \
            file://0016-SUBMITTED-power-domain-Add-QCOM-RPMH-Power-Domain-Dr.patch \
            file://0017-SUBMITTED-soc-qcom-cmd-db-Add-cmd_db_read_slave_id-c.patch \
            file://0018-SUBMITTED-mach-snapdragon-fixup-power-domains.patch \
            file://0019-SUBMITTED-configs-qcom-Add-QCOM_POWER_DOMAIN-config.patch \
            file://0020-PENDING-Add-qcs9075-support.patch \
            file://0021-PENDING-Added-Reboot-bootloader-support.patch \
            file://0022-SUBMITTED-watchdog-qcom-Add-max-timeout-check-to-pre.patch \
            file://0023-SUBMITTED-fs-fat-Handle-FAT-sector-size-mismatch.patch \
            file://0024-PENDING-Make-OF_FIXUP-soc-specific.patch \
            file://0025-PENDING-dts-qcs615-ride-u-boot.dtsi-Add-support-for-.patch \
            file://0026-PENDING-dts-qcs615-ride-u-boot.dtsi-Add-support-for-.patch \
            file://0027-PENDING-Add-Save-ENV-support-for-qcs615.patch \
            file://0028-PENDING-Add-PMIC-regulators-support.patch \
            file://0029-SUBMITTED-efi_loader-Remove-authentication-header-in.patch \
            file://0030-SUBMITTED-efi_loader-improve-device-path-matching-fo.patch \
            file://0031-SUBMITTED-capsule_update-fix-partition-match-to-excl.patch \
          "

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit pkgconfig

do_configure[cleandirs] = "${B}"
