FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00131.0/QCS8300.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS8300_FW_BUILD_ID ?= "r1.0_00131.0/QCS8300.LE.1.0"
QCS8300_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS8300_HLOSFW_SHA256SUM ?= "9fb9956d1e29ae748214a09a2c7f92302e763b2359f307bf1e7cbfb3b0d1b290"
QCS8300_DSPSO_SHA256SUM ?= "56fb5511780337671272964005cf8990252ba11e048f1e7288f798ad811894d7"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
