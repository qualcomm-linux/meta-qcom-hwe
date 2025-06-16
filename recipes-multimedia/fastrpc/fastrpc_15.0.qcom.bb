inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "f4ca7b2847b5e469e843c89f9775acf7e8f58e6b5cdf4465df94d8be75d0c14f"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "c45783f32883b899c5b9d4bf25072d2461b00a67bb73b178bfa426a268f1d329"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "ffbd7f6bc67ecffb19a95aa929d48f6df8f205cea6b58bdaf4aa25877b746701"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "384b573ac6c9e870a52d1270a44431c4f3a09da9b22867bd6ad35774ad7ea2fe"
QCS8300_RIDE_SX_SHA256SUM = "acb127b71f69056db26e5a3bc866d61f515451f86579d59d22b151ebba029f3d"
QCS9100_RIDE_SX_SHA256SUM = "e7aa3e2368844827d26640d791e7827335f2658950ec717eeaf3051c5edb9653"
QCS9075_RIDE_SX_SHA256SUM = "2a66f5c3da98f6899fea5e0b158ad6379e016d33541c20906d62e1f609b56955"
QCS9075_RB8_CORE_KIT_SHA256SUM = "2724c978d34c91835185663f8ae46bb5675b279dfb49e7d226728935a3ff31a0"
QCS9075_IQ_9075_EVK_SHA256SUM = "4e9a96c014bff11ab6031d795f4675ea5e949614b7877f330886d809b10d6d89"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9075_RB8_CORE_KIT_SHA256SUM}"
SRC_URI[qcs9075_iq_9075_evk.sha256sum] = "${QCS9075_IQ_9075_EVK_SHA256SUM}"


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

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"

SYSTEMD_SERVICE:${PN}:append:qcom = " adsprpcd.service cdsprpcd.service"
SYSTEMD_SERVICE:${PN}:append:qcs9100 = " cdsp1rpcd.service gdsprpcd.service gdsp1rpcd.service"
