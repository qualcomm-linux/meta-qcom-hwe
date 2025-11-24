FW_ARTIFACTORY = "softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00058.0/qcs8300-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS8300_FW_BUILD_ID ?= "r1.0_00108.0/qcs8300-le-1-0"
QCS8300_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS8300_HLOSFW_SHA256SUM ?= "f963bb2116d338e407676039724d42fa6242be91214ad16a4c4e635e8bc180a5"
QCS8300_DSPSO_SHA256SUM ?= "54943efcfe81155b53953112be341da4c0febc30057c4d163ad1781978c6773c"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
