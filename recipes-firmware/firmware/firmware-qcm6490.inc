FW_ARTIFACTORY = "softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00108.0/qcm6490-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCM6490_FW_BUILD_ID ?= "r1.0_00108.0/qcm6490-le-1-0"
QCM6490_FW_BIN_PATH ?= "common/build/ufs/bin"
QCM6490_HLOSFW_SHA256SUM ?= "9f7651efc5a79f68d060e01e04ab6b807b5c185fded59d3d416c0d3b0f1a7b35"
QCM6490_DSPSO_SHA256SUM ?= "7de597fe2a4ee455ba2dac1217d1c2efd93e43110405abb2bcbd7c82c02c2ede"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
