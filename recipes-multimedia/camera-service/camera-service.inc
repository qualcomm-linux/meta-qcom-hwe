inherit cmake pkgconfig

DESCRIPTION = "Qualcomm Linux Embedded Camera Server"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/le-services.git;protocol=https"
SRCBRANCH  = "le-services.lnx.1.0.r1-rel"
SRCREV     = "bc8d77091b55ce79b8010e8d27f6c3009cdfa9bd"

SRC_URI  = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=le-camera-server"

S = "${WORKDIR}/le-camera-server"

PACKAGE_ARCH = "${MACHINE_ARCH}"
