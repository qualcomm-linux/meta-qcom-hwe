DESCRIPTION = "Qualcomm Linux Embedded Camera Server"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

inherit cmake pkgconfig systemd

DEPENDS += "glib-2.0"
DEPENDS += "gtest"
DEPENDS += "protobuf-native protobuf-c protobuf-c-native"
DEPENDS:append:qcm6490 = " camx-kt chicdk-kt"
DEPENDS:append:qcs9100 = " camx"
DEPENDS:append:qcs8300 = " camx"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/le-services.git;protocol=https"
SRCBRANCH  = "le-services.lnx.1.0.r1-rel"
SRCREV     = "bc8d77091b55ce79b8010e8d27f6c3009cdfa9bd"

SRC_URI  = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=le-camera-server"

S = "${WORKDIR}/le-camera-server"

SYSTEMD_SERVICE:${PN} = "cam-server.service"

EXTRA_OECMAKE:append:qcm6490 = " -DTARGET_BOARD_PLATFORM=qcm6490 "
EXTRA_OECMAKE:append:qcs9100 = " -DTARGET_BOARD_PLATFORM=qcs9100 "
EXTRA_OECMAKE:append:qcs8300 = " -DTARGET_BOARD_PLATFORM=qcs8300 "
EXTRA_OECMAKE += "-DCAM_SERVER_USER=system"
EXTRA_OECMAKE += "-DCAM_SERVER_GROUP=video"
EXTRA_OECMAKE += "-DCAM_SERVER_SUPP_GROUP=system"

PACKAGE_ARCH = "${MACHINE_ARCH}"
