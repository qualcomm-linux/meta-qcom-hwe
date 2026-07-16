inherit autotools-brokensep pkgconfig qprebuilt systemd

SUMMARY = "Driver providing support for SAIL-CAN via Mailbox communication"
DESCRIPTION = "Provide Sail CAN Driver to communicate between MD and SAIL CAN. SAIL CAN user space binary is used to connect between virtual CAN ports and SAIL CAN controllers in SAIL SS via mailbox in kernel."

LICENSE = "LICENSE.qcom-2"

LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DEPENDS = "glib-2.0 linux-libc-headers can-utils pkgconfig-native cmake-native"

QCS9100_SHA256SUM = "162bd7be97a77a625e6a378eb1a4b60d4986ffc3ad6aeea12e1622118abc9efb"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"

QCS8300_SHA256SUM = "f51e6591d64f25dd1d529eea0d59fbe6445d50d814cdfb97be66d78add5a9adf"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Explicitly define packages to ensure proper file packaging
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

# Explicit package file definitions (library comes from sail-mailbox)
FILES:${PN} = "${bindir}/sail_can ${sysconfdir}/sail_can/*"
FILES:${PN}-dev = "${includedir} ${libdir}/pkgconfig ${libdir}/*.la ${libdir}/*.a"
FILES:${PN}-dbg = "${bindir}/.debug ${libdir}/.debug"
