FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00129.0/QCM6490.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCM6490_FW_BUILD_ID ?= "r1.0_00129.0/QCM6490.LE.1.0"
QCM6490_FW_BIN_PATH ?= "common/build/ufs/bin"
QCM6490_HLOSFW_SHA256SUM ?= "27ef79f60f961b21da10cab618f0d299b5d73b0ff82809d0044fa93199c56a6a"
QCM6490_DSPSO_SHA256SUM ?= "3a6de79a1ed3fb9a60030f7016a2ca7fa1b06eeef247b44b188c21ed71dbdc7e"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
