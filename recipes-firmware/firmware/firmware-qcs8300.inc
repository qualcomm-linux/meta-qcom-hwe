FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00112.0/qcs8300-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS8300_FW_BUILD_ID ?= "r1.0_00112.0/qcs8300-le-1-0"
QCS8300_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS8300_HLOSFW_SHA256SUM ?= "8567a9b67a5bac7194494344ab540858c6712e8b2af1bf475c7f69d06242992d"
QCS8300_DSPSO_SHA256SUM ?= "a95d5d9f63e6a8d8539b025eaacbade66fc839d037819d7df6a8d98998a54ac9"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
