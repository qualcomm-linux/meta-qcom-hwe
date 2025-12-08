inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${SOC_ARCH}"

QCM6490_SHA256SUM = "41c2ef039d67b44451973888a9f6b06de34d98a66f05c9791e90a6c3bb11b4fb"
QCS9100_SHA256SUM = "28d53f7f25830eaba65bee3e723a0e2624a11b100e73b42a5f96130a571fe2f2"
QCS8300_SHA256SUM = "78ab2a1361f258bd18c1acc036c10d9eac52df778ff499e3445d39210d88cbd0"
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
