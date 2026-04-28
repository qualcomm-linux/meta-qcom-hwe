# Qualcomm platform additions to the OSTree initramfs init script.
# Patches init.sh to start wd_keepalive early so the hardware watchdog
# is armed during initramfs boot.
# CR: 4501258

FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/files:"

SRC_URI:append:qcom = " file://0001-ostree-initrd-Start-wd_keepalive-early-in-initramfs.patch"
