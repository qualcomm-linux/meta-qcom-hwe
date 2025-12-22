FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00114.0/qcs8300-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS8300_FW_BUILD_ID ?= "r1.0_00114.0/qcs8300-le-1-0"
QCS8300_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS8300_HLOSFW_SHA256SUM ?= "e4a6abfe78ed81c63b97cf35c4b26969b85bbfdde518c1c0459fcfaa84c15a3b"
QCS8300_DSPSO_SHA256SUM ?= "af1a9bbc78de97bc31a16cc64c344597a7e259824e177e874c5dcd27dce3312f"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
