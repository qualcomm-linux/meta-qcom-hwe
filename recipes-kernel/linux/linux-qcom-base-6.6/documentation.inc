# Patches to be applied on top of kernel documentation
FILESEXTRAPATHS:prepend := "${THISDIR}/documentation:"

SRC_URI:append = " \
    file://common/0039-UPSTREAM-docs-ABI-sysfs-class-led-trigger-netdev-add.patch \
    "