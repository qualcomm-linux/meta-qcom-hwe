inherit autotools pkgconfig

DESCRIPTION = "Bluetooth application layer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
QCOM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BT_SRCREV    ?= "90feec7d91fed281eae91e7c7573a7955db19620"

SRCREV_FORMAT = "qcombt"

SRCREV_qcombt = "${QCOM_BT_SRCREV}"
SRCREV_qcombluetooth = "${QCOM_BLUETOOTH_SRCREV}"
SRCREV_systembt = "${QCOM_SYSTEM_BT_SRCREV}"
SRCREV_bluetoothext = "${QCOM_BLUETOOTH_EXT_SRCREV}"

SRC_URI = "${QCOM_BT_SRC};branch=${QCOM_BT_SRCBRANCH};name=qcombt;destsuffix=bluetooth/btapp"

S = "${WORKDIR}/bluetooth/btapp"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'
EXTRA_OEMAKE += "STAGING_INCDIR=${STAGING_INCDIR}"

AUTOTOOLS_SCRIPT_PATH = "${S}/bt-app"

DEPENDS += "btvendorhal glib-2.0 property-vault libchrome fluoride qcom-audioroute qcom-pa-bt-audio libbsd"
RDEPENDS:${PN} = "property-vault"

CPPFLAGS:append = " -DUSE_LIBHW_AOSP -DUSE_GEN_GATT"
CPPFLAGS:qcm6490 = " -DSUPPORT_VENDOR_AP"
SECURITY_CFLAGS = "${SECURITY_NO_PIE_CFLAGS}"

EXTRA_OECONF = " \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
                --with-gengatt \
               "
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} += "${sysconfdir}/bluetooth/*"
FILES:${PN} += "${userfsdatadir}/misc/bluetooth/*"

do_install:append() {
        install -d ${D}${sysconfdir}/bluetooth/

        #create /data/misc/bluetooth/ folder
        #install -d ${D}${userfsdatadir}/misc/bluetooth/

        if [ -f ${S}/bt-app/conf/bt_app.conf ]; then
           install -m 0660 ${S}/bt-app/conf/bt_app.conf ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/bt-app/conf/AdvertiserConfigFile.txt ]; then
           install -m 0660 ${S}/bt-app/conf/AdvertiserConfigFile.txt ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/bt-app/conf/ServerConfigFile.txt ]; then
           install -m 0660 ${S}/bt-app/conf/ServerConfigFile.txt ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/bt-app/conf/ext_to_mimetype.conf ]; then
           install -m 0660 ${S}/bt-app/conf/ext_to_mimetype.conf ${D}${sysconfdir}/bluetooth/
        fi
}
