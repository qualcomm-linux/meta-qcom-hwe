inherit autotools-brokensep

DESCRIPTION = "Header-only recipe for bt_audio"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "b908f6524be00474ceb402381f546d90ef15f330"

SRCREV_FORMAT = "bluetooth"

SRCREV_bluetooth = "${QCOM_BLUETOOTH_SRCREV}"

SRC_URI = "${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};name=bluetooth;destsuffix=bluetooth/bt_audio"

S = "${WORKDIR}/bluetooth/bt_audio"

AUTOTOOLS_SCRIPT_PATH = "${S}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/bt_audio
    cd ${S} && find ./ -name '*.h' | tar czf ${D}${includedir}/bt_audio/bt_audio.tgz -T -
    tar zxvf ${D}${includedir}/bt_audio/bt_audio.tgz -C ${D}${includedir}/bt_audio
    rm -f ${D}${includedir}/bt_audio/bt_audio.tgz
}
