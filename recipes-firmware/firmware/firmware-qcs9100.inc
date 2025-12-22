FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00114.0/qcs9100-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS9100_FW_BUILD_ID ?= "r1.0_00114.0/qcs9100-le-1-0"
QCS9100_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS9100_HLOSFW_SHA256SUM ?= "58ac271f34f7deadf56a28b7ef2711fcad3b1f7e4b66caf764f2724ec9dc27b9"
QCS9100_DSPSO_SHA256SUM ?= "f952828c9dc19f6508365f3c2875c5374db0e57f95ef08236ad6b303de58df67"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
