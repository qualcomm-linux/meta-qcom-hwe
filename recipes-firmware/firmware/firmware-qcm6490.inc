FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
FW_BUILD_ID    = "r1.0_00117.0/qcm6490-le-1-0"
FW_BIN_PATH    = "common/build/ufs/bin"

QCM6490_FW_BUILD_ID ?= "r1.0_00117.0/qcm6490-le-1-0"
QCM6490_FW_BIN_PATH ?= "common/build/ufs/bin"
QCM6490_HLOSFW_SHA256SUM ?= "9b6b084cb1db56a408e659417e37c6eacb899019b2a11d6bbdcfa055f5fc6099"
QCM6490_DSPSO_SHA256SUM ?= "3c2feec25dc860ab55a5766a69fbff1d992b515412d9b4dba241588af21736ac"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
