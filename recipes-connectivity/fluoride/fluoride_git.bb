inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Fluoride Stack"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "zlib libchrome glib-2.0 property-vault qcom-audioroute libbsd bt-audio-headers"
RDEPENDS:${PN} = "property-vault"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCREV ?= "16ef1a61613a08dc2a0981d84ecb026744ac31ca"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV ?= "eaec2f1535cc20362db170631d79b698f3a13fbc"

SRCREV_FORMAT = "qcomsystembt_qcombluetoothext"

SRCREV_qcomsystembt = "${QCOM_SYSTEM_BT_SRCREV}"
SRCREV_qcombluetoothext = "${QCOM_BLUETOOTH_EXT_SRCBRANCH}"

SRC_URI = "${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};name=qcomsystembt;destsuffix=bluetooth/stack/system/bt \
           ${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};name=qcombluetoothext;destsuffix=bluetooth/stack/bluetooth_ext"

S = "${WORKDIR}/bluetooth/stack/system/bt"
S_EXT = "${WORKDIR}/bluetooth/stack/bluetooth_ext/system_bt_ext"

AUTOTOOLS_SCRIPT_PATH = "${S}"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'
EXTRA_OEMAKE += 'AUDIO_INC_PATH=${STAGING_INCDIR}/bt_audio'

PSEUDO_IGNORE_PATHS = "/dev/,${WORKDIR}/bluetooth,${WORKDIR}/pkgdata-sysroot,${TMPDIR}/sysroots-components"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}"
FILES:${PN} += "${sysconfdir}/bluetooth/*"
INSANE_SKIP:${PN} = "dev-so"

CPPFLAGS:append = " -DUSE_ANDROID_LOGGING -DUSE_LIBHW_AOSP"
CPPFLAGS:append = " -w -I${STAGING_INCDIR}"
CPPFLAGS:qcm6490 = " -DSUPPORT_VENDOR_AP"

CFLAGS:append = " -w -DNDEBUG  -I${STAGING_INCDIR}"

CFLAGS:append = " -ffile-prefix-map=${WORKDIR}=."
CXXFLAGS:append = " -ffile-prefix-map=${WORKDIR}=."

EXTRA_OECONF = " \
                --with-zlib \
                --with-lib-path=${STAGING_LIBDIR} \
                --enable-static=yes \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
                --disable-dependency-tracking \
               "
EXTRA_OECONF:append:qcm6490 = " --with-vendorap"

PACKAGE_ARCH = "${MACHINE_ARCH}"
do_install:append() {

	install -d ${D}${sysconfdir}/bluetooth/

	cd  ${D}/${libdir}/ && ln -s libbluetoothdefault.so bluetooth.default.so
	cd  ${D}/${libdir}/ && ln -s libaudioa2dpdefault.so audio.a2dp.default.so

	if [ -f ${S}/conf/auto_pair_devlist.conf ]; then
	   install -m 0660 ${S}/conf/auto_pair_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/conf/bt_did.conf ]; then
	   install -m 0660 ${S}/conf/bt_did.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/conf/bt_stack.conf ]; then
	   install -m 0660 ${S}/conf/bt_stack.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/interop_database.conf ]; then
		install -m 0660 ${S_EXT}/conf/interop_database.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/bt_profile.conf ]; then
		install -m 0660 ${S_EXT}/conf/bt_profile.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/conf/iot_devlist.conf ]; then
	   install -m 0660 ${S}/conf/iot_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi

    install -d ${D}${includedir}/bluetooth
    install -m 0660 ${S_EXT}/include/bt_testapp.h ${D}${includedir}/fluoride/
    cd ${S} && find ./ -name '*.h'|xargs tar czf ${D}${includedir}/bluetooth/bluetooth.tgz
    tar zxvf ${D}${includedir}/bluetooth/bluetooth.tgz -C ${D}${includedir}/bluetooth && rm -f ${D}${includedir}/bluetooth/bluetooth.tgz
}
