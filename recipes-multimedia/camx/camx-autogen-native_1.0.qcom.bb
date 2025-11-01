inherit qprebuilt native

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx Autogen"

# Required for native build
DEPENDS:class-native += "libxml-simple-perl-native chicdk-autogen-native"

# Added inhibiting dependencies of prebuilts.
# If any no-ship dependencies are added, they have to inherit prebuilt.
# If not, need to remove their dependency from compiling in ship variant.
PREBUILT_INHIBIT_DEPS    = "chicdk-autogen-native"

QCS9100_SHA256SUM = "d7beeb979eeeaf5c9916e5af7de95fbe97e51e13f42d5937307271c3e512aa00"
QCS8300_SHA256SUM = "fec7b88beac994b53bd5ef1bad2ad93b8f286d190a97b2fbe2485c03eba274bc"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

