FW_ARTIFACTORY = "softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00108.0/qcs615-le-1-0"
FW_BIN_PATH    = "common/build/emmc/bin"

QCS615_FW_BUILD_ID ?= "r1.0_00108.0/qcs615-le-1-0"
QCS615_FW_BIN_PATH ?= "common/build/common/bin"
QCS615_HLOSFW_SHA256SUM  ?= "1247a5585346408a82bdd44f248676bed22be70dfd481d938c40ce8e2448200c"
QCS615_DSPSO_SHA256SUM  ?= "e54879bd77377a85819e761617cbce0d1424ac7a797623bc52f7189eb06a0aba"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
