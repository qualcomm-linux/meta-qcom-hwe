inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Example for Secure DSP usecase."

DEPENDS += "fastrpc qcom-libvmmem securemsm-features"

PBT_ARCH = "aarch64"

AARCH64_SHA256SUM = "cd779cbdf9da2743476f0615f561e08cd927335fd41ef3cb11d8f87e3d569491"
SRC_URI[aarch64.sha256sum] = "${AARCH64_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${libdir}/*.so* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"

INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"