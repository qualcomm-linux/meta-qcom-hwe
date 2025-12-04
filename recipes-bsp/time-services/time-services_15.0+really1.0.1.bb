inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"


QCM6490_SHA256SUM = "c4853c95075c5faa492ccda973496b5c3836f16a8dd6ea0e01d4e048b1b383ad"
QCS9100_SHA256SUM = "fbaa8b0a8fdd1aa8150493b255987fa78dcc984e98dd1a63c3210f3943518169"
QCS8300_SHA256SUM = "7a843a00356356d4632981d93d615afb95e062c7520e7fe783f2b805cf276e9c"
QCS615_SHA256SUM = "5874763bd9972e0ea570169347109202cfeb5982aeed15481abc467b6abf690e"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

