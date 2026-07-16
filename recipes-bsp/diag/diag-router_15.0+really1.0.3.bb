inherit qprebuilt pkgconfig systemd

LICENSE          = "LICENSE.qcom-2"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=165287851294f2fb8ac8cbc5e24b02b0"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "3afc65431a140431fd39c4c45adf6717c39df772a89d8355ee7c02b01f866e6c"
QCS9100_SHA256SUM = "5215427be4c7eb8fe6abeb01dd0dc872104850c818b3976716774ced2a6e3e12"
QCS8300_SHA256SUM = "5ac117d64368cbd67721efcc403e111c2f0088b2beda5a4c978c367fc805c1fd"
QCS615_SHA256SUM = "15a0bd6e6381f4c9eea2166f219c22aa8c1761aabed4681539e6d072b03100a9"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

