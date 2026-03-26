FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00120.0/QCS8300.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS8300_FW_BUILD_ID ?= "r1.0_00120.0/QCS8300.LE.1.0"
QCS8300_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS8300_HLOSFW_SHA256SUM ?= "9ea871e684644f94d5bb8d58a35a08aa527028ff37012ba176a6ccc7a39f164d"
QCS8300_DSPSO_SHA256SUM ?= "29ee04dc3e501c7f4ecae6fcb9b4bd44f3e27bf28e6ec41af3af3f5e67149e9d"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
