inherit autotools-brokensep pkgconfig qprebuilt systemd

SUMMARY = "Driver providing support for SAIL-CAN via Mailbox communication"
DESCRIPTION = "Provide Sail CAN Driver to communicate between MD and SAIL CAN. SAIL CAN user space binary is used to connect between virtual CAN ports and SAIL CAN controllers in SAIL SS via mailbox in kernel."

LICENSE = "LICENSE.qcom-2"

LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DEPENDS = "glib-2.0 linux-libc-headers can-utils pkgconfig-native cmake-native"

QCS9100_SHA256SUM = "00dea701a5c81119d189e31aa0c63b4be2e9dbe3cebb1625c85e6cb03cd87891"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"

QCS8300_SHA256SUM = "cfa1daf535db33fa9931e5a7df484f4c0bf765de4c58157f7f1d06c6b87aa496"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Explicitly define packages to ensure proper file packaging
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

# Explicit package file definitions (library comes from sail-mailbox)
FILES:${PN} = "${bindir}/sail_can ${sysconfdir}/sail_can/*"
FILES:${PN}-dev = "${includedir} ${libdir}/pkgconfig ${libdir}/*.la ${libdir}/*.a"
FILES:${PN}-dbg = "${bindir}/.debug ${libdir}/.debug"
