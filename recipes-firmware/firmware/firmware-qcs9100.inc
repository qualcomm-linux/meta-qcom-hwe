FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00112.0/qcs9100-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS9100_FW_BUILD_ID ?= "r1.0_00112.0/qcs9100-le-1-0"
QCS9100_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS9100_HLOSFW_SHA256SUM ?= "d566c8d00988ae70ede40fe34af85aeaf2fe21bd2d0ebcff93216e368979f616"
QCS9100_DSPSO_SHA256SUM ?= "44738bc7cd56d95e3de2f489584b33cc6553a2f7521aee5f14be5ed371bb82e7"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
