inherit qprebuilt pkgconfig

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Audio DAC"

DEPENDS += "qcom-audio-expander"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "ff91eaddbbd5bb033c95448fd29adb64afcfd53acb6a575e3d336259a756e6a4"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
