FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://0001-QCLINUX-bluez5-HFP-version-upgrade-to-1.8.patch \
"
