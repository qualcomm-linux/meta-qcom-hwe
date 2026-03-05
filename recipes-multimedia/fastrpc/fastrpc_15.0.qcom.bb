inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${SOC_ARCH}"

QCM6490_SHA256SUM = "e2998d63d11dfba72c7985092b5b59e5e31e9c3d93ab4580d438c69f128a6d07"
QCS9100_SHA256SUM = "cb797f734185142a02abf8b109f7ff56af64531fb0144836567ed5bed0bd413b"
QCS8300_SHA256SUM = "beccf66c835c1bf33eedde052d5aa128c5636fe1fa733deb723b302df2439455"
QCS615_SHA256SUM = "4f2c8ff490a0b25c1b77527fbacf26002e26fb234ab76b77a5dd3f9773a0b205"

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
