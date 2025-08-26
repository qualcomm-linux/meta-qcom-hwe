inherit autotools qprebuilt

DESCRIPTION = "audio firmware files"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SRC_URI += "file://qcs6490/qcs6490-rb3gen2-snd-card-tplg.conf \
    file://qcm6490/qcm6490-idp-snd-card-tplg.conf \
    file://sa8775p/qcs9075-rb8-snd-card-tplg.conf \
    file://qcs8275/QCS8275-IQ8-EVK-tplg.conf"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

S = "${WORKDIR}"

do_install() {
        install -d ${D}${nonarch_base_libdir}/firmware/qcom/qcs6490
        install -d ${D}${nonarch_base_libdir}/firmware/qcom/qcm6490
        install -d ${D}${nonarch_base_libdir}/firmware/qcom/sa8775p
        install -d ${D}${nonarch_base_libdir}/firmware/qcom/qcs8275
        install -m 0644 ${S}/qcs6490/* -D ${D}${nonarch_base_libdir}/firmware/qcom/qcs6490
        install -m 0644 ${S}/qcm6490/* -D ${D}${nonarch_base_libdir}/firmware/qcom/qcm6490
        install -m 0644 ${S}/sa8775p/* -D ${D}${nonarch_base_libdir}/firmware/qcom/sa8775p
        install -m 0644 ${S}/qcs8275/* -D ${D}${nonarch_base_libdir}/firmware/qcom/qcs8275
}

FILES:${PN} += "${nonarch_base_libdir}/firmware"
