FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/product/chip/software-product-family/Qualcomm_Linux.SPF.1.0/qualcomm_linux.spf.1.0-test-device-public"
FW_BUILD_ID    = "r1.0_00117.0/QCS9100.LE.1.0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCS9100_FW_BUILD_ID ?= "r1.0_00117.0/QCS9100.LE.1.0"
QCS9100_FW_BIN_PATH ?= "common/build/ufs/bin"
QCS9100_HLOSFW_SHA256SUM ?= "2ab0d18b14a6d8ae4dc08465027683d84881b0c2b094152423ea0c97567bd121"
QCS9100_DSPSO_SHA256SUM ?= "d6533f5209e13f6544d6f2f6b0728ef0689a4e7355d97bab266aed0653eefc9f"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
