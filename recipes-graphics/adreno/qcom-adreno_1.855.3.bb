inherit qprebuilt pkgconfig

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm libxcb libx11 xcb-util-image libxshmfence"

RDEPENDS:${PN} += "libxcb-dri3 libxcb-present libxcb-sync"

PROVIDES  = "  virtual/libgles1 virtual/libgles2 virtual/egl adrenocl"
RPROVIDES:${PN} = " libegl libgles1 libgles2 adrenocl"

QCM6490_SHA256SUM = "39d6e508e1cb5cd65a6f704cc4a7d53e8582cc12e52c82a23e608c0960079f8a"
QCS9100_SHA256SUM = "cf39bfb1772d77a6c4cf54178799234fc872ef5dcdad998788cdca8e74109d3c"
QCS8300_SHA256SUM = "9be7a9386b19ebf2ad8c1e38b0389a6067ed371b83afffd78d80ed77858e5b4b"
QCS615_SHA256SUM = "cd27f4966fa64b9ba4d1527e87403acca64efa989c3d631fcf32a469397eaa74"

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

