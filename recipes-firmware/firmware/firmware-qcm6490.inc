FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00131.0/QCM6490.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCM6490_FW_BUILD_ID ?= "r1.0_00131.0/QCM6490.LE.1.0"
QCM6490_FW_BIN_PATH ?= "common/build/ufs/bin"
QCM6490_HLOSFW_SHA256SUM ?= "1214a6405d152d4fd319f556d5afcf173d22722efc1363ad88cef7578e9e744a"
QCM6490_DSPSO_SHA256SUM ?= "ef97031a8b07bd8f47e02d4eca54b34e10f851d1f6b8057a97f719a38cc96db2"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
