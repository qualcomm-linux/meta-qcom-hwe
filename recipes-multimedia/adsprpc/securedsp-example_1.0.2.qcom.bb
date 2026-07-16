inherit qprebuilt

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Example for Secure DSP usecase."

DEPENDS += "fastrpc qcom-libvmmem securemsm-features"

PBT_ARCH = "aarch64"

AARCH64_SHA256SUM = "e3685835ba8a2e35d0b3e544cf0133cfa0704fe9a6c7dee9d91106f3459e2b19"
SRC_URI[aarch64.sha256sum] = "${AARCH64_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${libdir}/*.so* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"

INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"