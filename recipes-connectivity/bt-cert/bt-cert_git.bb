inherit autotools pkgconfig

DESCRIPTION = "Bluetooth certification tool"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV ?= "996bbb712e62c5c22489cd84fad1a93a91d65ddc"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCREV ?= "db943ed562f68d7f3a6d41dc79e0fdb731320e5e"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "1710c237b493454dc93f41de09b50cd8d109f970"

SRCREV_FORMAT = "bluetoothext_systembt_qcombluetooth"

SRCREV_bluetoothext = "${QCOM_BLUETOOTH_EXT_SRCREV}"
SRCREV_systembt = "${QCOM_SYSTEM_BT_SRCREV}"
SRCREV_qcombluetooth = "${QCOM_BLUETOOTH_SRCREV}"

SRC_URI = "${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};name=bluetoothext;destsuffix=bluetooth/stack/bluetooth_ext \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};name=systembt;destsuffix=bluetooth/stack/system/bt \
           ${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};name=qcombluetooth;destsuffix=bluetooth/bt_audio"

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/bluetooth_ext/certification_tools"

DEPENDS  += "glib-2.0 btvendorhal libchrome fluoride libbsd"

CPPFLAGS:qcm6490 = " -DSUPPORT_VENDOR_AP"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

EXTRA_OECONF = " \
                --with-common-includes="${S}/bt_audio/hal/include/" \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "
