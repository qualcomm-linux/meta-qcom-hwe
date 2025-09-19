inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm libxcb libx11 xcb-util-image"

PROVIDES  = "  virtual/libgles1 virtual/libgles2 virtual/egl adrenocl"
RPROVIDES:${PN} = " libegl libgles1 libgles2 adrenocl"

QCM6490_SHA256SUM = "8dff3aa2346c14394b50faf0f30e672f4e4ed54252986e357f840786f488052d"
QCS9100_SHA256SUM = "03cbfd47ad728be5acc5e833a421f96119605bad5fb445d3940e31393c11a7ae"
QCS8300_SHA256SUM = "cf97afee7d67897fcab907d6002a20ba673e1d175a08983c03da33c67d775654"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

reinstall_files () {
    install -d ${D}/${libdir}
    cp ${THISDIR}/glesv1_cm.pc ${D}${libdir}/pkgconfig/
    install -d ${D}/usr/share/vulkan/icd.d
    cp ${THISDIR}/adrenovk.json ${D}/usr/share/vulkan/icd.d/
}
do_install[postfuncs] += "reinstall_files"

FILES:${PN} = "${nonarch_base_libdir}/firmware/* \
               ${nonarch_libdir}/lib*.so.* \
               ${base_libdir}/firmware/* \
               ${libdir}/lib*.so.* \
               ${libdir}/firmware \
               /usr/share/vulkan/icd.d/* "
FILES:${PN}-dev = "${includedir}/* \
                   ${nonarch_libdir}/lib*.so \
                   ${bindir}/ \
                   ${libdir}/clang \
                   ${libdir}/pkgconfig \
                   ${libdir}/lib*.so "
FILES:${PN}-dbg = ""


INSANE_SKIP:${PN} = "dev-deps file-rdeps dev-so arch already-stripped"

