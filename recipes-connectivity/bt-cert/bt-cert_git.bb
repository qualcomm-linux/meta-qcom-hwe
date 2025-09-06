inherit autotools pkgconfig

DESCRIPTION = "Bluetooth certification tool"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV ?= "eaec2f1535cc20362db170631d79b698f3a13fbc"

SRCREV_FORMAT = "bluetoothext"

SRCREV_bluetoothext = "${QCOM_BLUETOOTH_EXT_SRCREV}"

SRC_URI = "${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};name=bluetoothext;destsuffix=bluetooth/stack/bluetooth_ext"

S = "${WORKDIR}/bluetooth/stack/bluetooth_ext"

AUTOTOOLS_SCRIPT_PATH = "${S}/certification_tools"

DEPENDS  += "glib-2.0 btvendorhal libchrome fluoride libbsd"

CPPFLAGS:qcm6490 = " -DSUPPORT_VENDOR_AP"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'
EXTRA_OEMAKE += "STAGING_INCDIR=${STAGING_INCDIR}"

EXTRA_OECONF = " \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "
