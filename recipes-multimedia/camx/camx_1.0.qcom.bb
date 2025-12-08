inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxlib chicdk-autogen camx-autogen fastrpc"

QCS9100_SHA256SUM = "5ca5a7873165a2ee91db2be0b23cb7c7611b65b3806b59f157c764fcd3d048ad"
QCS8300_SHA256SUM = "69b618efa9c7aef260e62d9cd19573cece5888ee556b22992a344c24c55967a4"
QCS615_SHA256SUM = "ea89687b8f57b66473f72023d085daddddc54e2cacbe4aab7f2f0d63c8288ad9"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /lib/firmware/*"
FILES:${PN}-dev = "/usr/include/*"

#Skips check for .so symlinks
INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

