inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${SOC_ARCH}"

QCM6490_SHA256SUM = "108a3bb3afa194813d02673942ccfb44a7c927aa66a817c907da722ca5159002"
QCS9100_SHA256SUM = "378008f9e984eb607d8cbef834567d930a3be600a2e4589c9a28a199ec657232"
QCS8300_SHA256SUM = "cc14638b0d18a3976379e292ae252229f2050cfb633ac9b4bceb4910696ec57a"
QCS615_SHA256SUM = "6dc82e5b0a861847634027fa3b404c4bb6c2dc0292dde470c60fee4e8112e5b6"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Install systemd unit file at right location
relocate_systemd_unit_files () {
    if [ -d "${D}/lib/systemd" ]; then
        install -d ${D}/usr/lib/
        mv ${D}/lib/systemd  ${D}/usr/lib/systemd
    fi
}
do_install[postfuncs] += "relocate_systemd_unit_files"

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"

PACKAGE_ARCH    ?= "${SOC_ARCH}"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"

SYSTEMD_SERVICE:${PN}:append = " adsprpcd.service cdsprpcd.service cdsp1rpcd.service gdsprpcd.service gdsp1rpcd.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"
