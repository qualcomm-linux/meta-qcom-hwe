# Image files for Uboot
UBOOT_IMAGETYPE ?= "bin"
UBOOT_ELF_SUFFIX ?= "elf"
UBOOT_ELF = "u-boot.elf"
UBOOT_ELF_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.${UBOOT_ELF_SUFFIX}"
UBOOT_ELF_BINARY ?= "u-boot.${UBOOT_ELF_SUFFIX}"

do_deploy:append() {
        cp u-boot-${MACHINE}-${PV}-${PR}.${UBOOT_ELF_SUFFIX} u-boot-${MACHINE}-${PV}-${PR}-stripped.${UBOOT_ELF_SUFFIX}
}
