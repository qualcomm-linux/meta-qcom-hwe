inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "9e63db469d6c0c582010fc4445ecbca6d044ca708084b9c04d28deaf0710623a"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "9ceb91bc6ea28b8dbaa12b0a83a7afa9eade002674235ce4cb57297fe23c56bf"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "aa79d0cc664650001e79f9bc23596e9d460c4111080ec4cb32b96f53723ae8ea"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "0c8511e5bf507e397d2405ea256de0c23be884a033c4815c06c0a65c84607db4"
QCS8300_RIDE_SX_SHA256SUM = "9734ea4235c608914456d88d78b89353a71e1b321d968f3c21dfa59d1ecc0c52"
QCS9100_RIDE_SX_SHA256SUM = "5da92a12f5ecb1e0085ce5c6d34c68db92071c1eb7e95fefd11d908569482349"
QCS9075_RIDE_SX_SHA256SUM = "fb1513bf8b88916c51c35a6d2c3dc1fb84177f1614e9a8a438fb0f07cf71759f"
QCS9075_RB8_CORE_KIT_SHA256SUM = "b832a670685bd8329731fe3537917b1d346af16681a6b740a7f2d23f7d73957a"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9075_RB8_CORE_KIT_SHA256SUM}"


SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

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

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"

SYSTEMD_SERVICE:${PN} = " adsprpcd.service cdsprpcd.service"
SYSTEMD_SERVICE:${PN}:append:qcs9100 = " cdsp1rpcd.service"
