inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm libxcb libx11 xcb-util-image libxshmfence"

RDEPENDS:${PN} += "libxcb-dri3 libxcb-present libxcb-sync"

PROVIDES  = "  virtual/libgles1 virtual/libgles2 virtual/egl adrenocl"
RPROVIDES:${PN} = " libegl libgles1 libgles2 adrenocl"

QCM6490_SHA256SUM = "e253441af0b27fa6a2a3c178d282161b532275c15b3046ffeae1b4eafe918907"
QCS9100_SHA256SUM = "134f93f38a07a724eb9f4fd40d9d4cc92965d4b76cdd5c659f88a7f0d2da9f36"
QCS8300_SHA256SUM = "487414237eeab0c2dc2367cf56194b66f0eab7b17b42e61122100eb61552aa2a"
QCS615_SHA256SUM = "97a78deef90858f937fb4130d3796c86106c9eb20ee3ea23a577fd7cd0fc2f0d"

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

FILES:${PN} = "${nonarch_libdir}/lib*.so.* \
               ${libdir}/lib*.so.* \
               /usr/share/vulkan/icd.d/* "
FILES:${PN}-dev = "${includedir}/* \
                   ${nonarch_libdir}/lib*.so \
                   ${bindir}/ \
                   ${libdir}/clang \
                   ${libdir}/pkgconfig \
                   ${libdir}/lib*.so "
FILES:${PN}-dbg = ""


INSANE_SKIP:${PN} = "dev-deps file-rdeps dev-so arch already-stripped"

