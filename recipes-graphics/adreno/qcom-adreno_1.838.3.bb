inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm libxcb libx11 xcb-util-image libxshmfence"

RDEPENDS:${PN} += "libxcb-dri3 libxcb-present libxcb-sync"

PROVIDES  = "  virtual/libgles1 virtual/libgles2 virtual/egl adrenocl"
RPROVIDES:${PN} = " libegl libgles1 libgles2 adrenocl"

QCM6490_SHA256SUM = "e860d0dc3b320f580de1c36cc6337673f61998d98883e3e101b632fbebce0aae"
QCS9100_SHA256SUM = "1c93e9cebfd7b943224ad6e9b4c02844396e679311069d6783e8820dfb3ecdc2"
QCS8300_SHA256SUM = "5273ee92b12dc8e4f5903df36e89e018aa07a68225d3fccd2666d552dea866a7"
QCS615_SHA256SUM = "cac5bff3401587c1df0ee29524d76608b50d197ba46259938a2d037a92bfd3b3"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

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

