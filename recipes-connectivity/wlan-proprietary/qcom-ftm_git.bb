inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ftmdaemon"

DEPENDS += "libnl diag glib-2.0 property-vault qcom-ath6kl-utils"

RDEPENDS:${PN} = "property-vault"


PBT_ARCH = "armv8-2a"

PV = "1.0"

ARMV8_SHA256SUM = "239599900ae56b315b54f37705c2388a23987eabb2334da229946e1dd762583d"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
