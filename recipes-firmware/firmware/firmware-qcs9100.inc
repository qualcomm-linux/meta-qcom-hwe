FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00129.0/QCS9100.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS9100_FW_BUILD_ID ?= "r1.0_00129.0/QCS9100.LE.1.0"
QCS9100_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS9100_HLOSFW_SHA256SUM ?= "55664228c84e89693f6bdcbaadb457532d765f7b00f5181d966481200bbace3e"
QCS9100_DSPSO_SHA256SUM ?= "acfb43cadab0894e6d1218d4ab4a005761000c05165a44be9bef814898a714e3"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
