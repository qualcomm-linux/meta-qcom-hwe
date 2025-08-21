
GNOMEBASEBUILDCLASS = "meson"
DEPENDS += " json-glib"

FILESEXTRAPATHS:append := "${THISDIR}/patches:"

SRC_URI:append:qcom = " \
             file://0001-Increase-delay-for-probing.patch \
             file://0002-Add-SMS-storage-support.patch \
             file://0003-Add-MT-SMS-storage-support.patch \
             file://0004-Fix-compilation-error.patch \
             file://0005-Avoid-updation-of-mtu-of-master-interface.patch \
             file://0006-PENDING-Patch-to-enable-MHI-support.patch "

EXTRA_OECONF:append:qcom = " --enable-plugin-qcom-soc"

PACKAGECONFIG:qcom = "vala qmi qrtr \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd polkit', d)} \
"

