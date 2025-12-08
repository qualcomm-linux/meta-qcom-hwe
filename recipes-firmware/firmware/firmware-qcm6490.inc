FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00112.0/qcm6490-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCM6490_FW_BUILD_ID ?= "r1.0_00112.0/qcm6490-le-1-0"
QCM6490_FW_BIN_PATH ?= "common/build/ufs/bin"
QCM6490_HLOSFW_SHA256SUM ?= "29d7217061b68b74f019971d3a4f6155509758175fba4c2e77c62d28040613f6"
QCM6490_DSPSO_SHA256SUM ?= "8125b62696f713e6469db03d5c3b7d5d0768b9ea4fbf6eca2bb915973476a575"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
