FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = "file://0001-QCLINUX-Wireplumber-Enable-bluez-plugin.patch \
"
