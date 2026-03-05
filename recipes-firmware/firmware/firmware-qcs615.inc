QCS615_FW_BUILD_ID ?= "r1.0_00114.0/qcs615-le-1-0"
QCS615_FW_BIN_PATH ?= "common/build/common/bin"
QCS615_HLOSFW_SHA256SUM  ?= "53378e80b2e9d5b33144d46d45f27363084a1dbb3ef9cbf53826d0e989f7210e"
QCS615_DSPSO_SHA256SUM  ?= "f94dbc7fcaa92a8f749a0262ba0caea7f8b8c76d05d8b461d4fa950bb0b2ed4f"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
