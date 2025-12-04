inherit qprebuilt pkgconfig

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Audio Mercury"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "1ef20960e6c1dd774d90bf0bcf7290cf8280f0432a43f0ce8486413a83880b39"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
