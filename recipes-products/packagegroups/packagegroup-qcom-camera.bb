SUMMARY = "QCOM Camera Package Group"

LICENSE = "BSD-3-Clause-Clear & Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

#qcm6490 is a common SOC_FAMILY name for all Kodiak board
RDEPENDS:${PN}:qcom-custom-bsp:qcm6490 = "camx-kt camxlib-kt chicdk-kt"

RDEPENDS:${PN}:append:qcm6490:qcom-custom-bsp = " qcom-camera-server"

#qcs615 is a common SOC_FAMILY name for all Talos board
RDEPENDS:${PN}:qcom-base-bsp:qcs615 = "libcamera v4l-utils"

#qcs615 is a common SOC_FAMILY name for all Talos board
RDEPENDS:${PN}:qcom-custom-bsp:qcs615= "cameradlkm"

#qcs9100 is a common SOC_FAMILY name for all Lemans board
RDEPENDS:${PN}:qcom-custom-bsp:qcs9100 = "camxcommon camxlib camx chicdk cameradlkm"

#qcs8300 is a common SOC_FAMILY name for all Monaco board
RDEPENDS:${PN}:qcom-custom-bsp:qcs8300 = "camxcommon camxlib camx chicdk cameradlkm"

#qcm6490 is a common SOC_FAMILY name for all Kodiak board
RDEPENDS:${PN}:qcom-base-bsp:qcm6490= "libcamera v4l-utils media-ctl yavta"

#qcs9100 is a common SOC_FAMILY name for all Lemans board
RDEPENDS:${PN}:qcom-base-bsp:qcs9100= "libcamera v4l-utils media-ctl yavta"

#qcs8300 is a common SOC_FAMILY name for all Monaco board
RDEPENDS:${PN}:qcom-base-bsp:qcs8300= "libcamera v4l-utils media-ctl yavta"
