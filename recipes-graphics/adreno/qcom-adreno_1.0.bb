inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap gbm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

QCM6490_SHA256SUM = "88525fed526c254873b681e63507150cb0bdeee9b3ba4049b84945303ce89230"
QCS9100_SHA256SUM = "388fef26ffe26eb4cc1d2ba9f00b40c68ef8f3ac1903d6fdf6514777073dcb14"
QCS8300_SHA256SUM = "68d984ed4b8cee53c817a0d6acf6a06aa5513003f9462f1050fc40baa335bcc0"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/* \
               ${nonarch_base_libdir}/firmware/* \
               ${nonarch_libdir}/* \
               ${base_libdir}/firmware/* \
               ${libdir}/* \
               ${bindir}/* \
               /usr/share/vulkan/icd.d/* "
FILES:${PN}-dev = ""
FILES:${PN}-dbg = ""


INSANE_SKIP:${PN} = "dev-deps file-rdeps dev-so arch already-stripped"

