FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00117.0/QCS8300.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS8300_FW_BUILD_ID ?= "r1.0_00117.0/QCS8300.LE.1.0"
QCS8300_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS8300_HLOSFW_SHA256SUM ?= "cfed3eba0457b47c4fbf6f0037ba45d24b18c38c26dcb960f90440833c8376de"
QCS8300_DSPSO_SHA256SUM ?= "ae353f5560c372d72bcf14c87e59c29711e983b18fad35028d78127510976c13"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
