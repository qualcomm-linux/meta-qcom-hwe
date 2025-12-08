FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00112.0/qcs615-le-1-0"
FW_BIN_PATH    = "common/build/common/bin"

QCS615_FW_BUILD_ID ?= "r1.0_00112.0/qcs615-le-1-0"
QCS615_FW_BIN_PATH ?= "common/build/common/bin"
QCS615_HLOSFW_SHA256SUM  ?= "9d7bd5f2914881f17c38a838dd0b6af056b34482cb4aff3eeeecbf1c8aca2125"
QCS615_DSPSO_SHA256SUM  ?= "f441d81a40e7e458ba62f8d7c677cf69c48f53ff6a0003f27e2e84687d4240be"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
