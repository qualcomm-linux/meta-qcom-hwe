DESCRIPTION = "Recipe to install GPU firmware on rootfs"

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

COMPATIBLE_MACHINE = "(qcs8300)"

# GPU firmware is not available in linux-firmware recipe yet.
# Fetch from CLO artifacts to mitigate.
SRC_URI = "https://artifacts.codelinaro.org/artifactory/codelinaro-le/Qualcomm_Linux/QCS8300/gpu-firmware.zip"
SRC_URI[sha256sum] = "fb72ad6349753c9ba0c36fae69463beb84c314d37429efaaa0956db60e3f9054"

inherit bin_package

do_install() {
     install -d ${D}${nonarch_base_libdir}/firmware/qcom
     install -d ${D}${nonarch_base_libdir}/firmware/qcom/qcs8300
     install -m 0644 ${WORKDIR}/gpu-firmware/qcom/a623_gmu.bin ${D}${nonarch_base_libdir}/firmware/qcom
     install -m 0644 ${WORKDIR}/gpu-firmware/qcom/a650_sqe.fw ${D}${nonarch_base_libdir}/firmware/qcom
     install -m 0644 ${WORKDIR}/gpu-firmware/qcom/qcs8300/a623_zap.mbn ${D}${nonarch_base_libdir}/firmware/qcom/qcs8300
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INSANE_SKIP:${PN} += "arch"
