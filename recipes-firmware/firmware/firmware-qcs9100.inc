FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00131.0/QCS9100.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS9100_FW_BUILD_ID ?= "r1.0_00131.0/QCS9100.LE.1.0"
QCS9100_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS9100_HLOSFW_SHA256SUM ?= "32b9820e0e022d88323104dca7677b20c76f45007f0099ec475301b84ff42384"
QCS9100_DSPSO_SHA256SUM ?= "327e89cf0b0bdc796ad12fde77450f9d5cd1bf8bce1521c9fcbf003a948b72d7"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
