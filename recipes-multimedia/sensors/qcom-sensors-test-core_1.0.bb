inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-test-core Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber protobuf diag qcom-sensors-api qcom-sensinghub qcom-sensors-utils qcom-sensors-core"

QCM6490_SHA256SUM = "520085fa48d1babdfc3f37fc0bd87a3aed96d6fe186f04eac5dca777ac781913"
QCS615_SHA256SUM = "bea0c6e631bfcaa27513df058ebb6a18a6c2e14ed210312a277e69e5affb0d6f"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"


INSANE_SKIP:${PN} = "dev-so"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
