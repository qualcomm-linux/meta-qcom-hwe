inherit autotools pkgconfig

DESCRIPTION = "Bluetooth certification tool"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV ?= "339830ec33c244ca1747b1e7dce971f2a5050a4d"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCREV ?= "5be31e3233046135a6913b396a21c04f82414680"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "feac37f59b09e012753bb7f4f48121619d227f2b"

SRC_URI = "${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};rev=${QCOM_BLUETOOTH_EXT_SRCREV};destsuffix=bluetooth/stack/bluetooth_ext \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};rev=${QCOM_SYSTEM_BT_SRCREV};destsuffix=bluetooth/stack/system/bt \
           ${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};rev=${QCOM_BLUETOOTH_SRCREV};destsuffix=bluetooth/bt_audio"

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/bluetooth_ext/certification_tools"

DEPENDS  += "glib-2.0 btvendorhal libchrome fluoride libbsd"

CPPFLAGS:qcm6490 = " -DSUPPORT_ESL_AP"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

EXTRA_OECONF = " \
                --with-common-includes="${S}/bt_audio/hal/include/" \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "
