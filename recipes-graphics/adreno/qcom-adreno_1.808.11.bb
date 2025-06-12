inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm libxcb libx11 xcb-util-image"

PROVIDES  = "  virtual/libgles1 virtual/libgles2 virtual/egl adrenocl"
RPROVIDES:${PN} = " libegl libgles1 libgles2 adrenocl"

QCM6490_SHA256SUM = "5857b6ac23de690bc8faf69e644d87e9f704b025aa9d63fa529930e4810a9070"
QCS9100_SHA256SUM = "ce5b33f66d71bd6b000fc333797dc1362ceb55405e3f06d048498b5de52a40af"
QCS8300_SHA256SUM = "46184f896e3498c0deda495eeffb1c4e68717bdcbf6fed8000509976cb2cf702"

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

