require camera-service.inc

inherit systemd

DEPENDS += "qcom-camera-common"

RDEPENDS:${PN} += "qcom-camera-server-libs"

EXTRA_OECMAKE += "-DBUILD_CATEGORY=SERVER"

SYSTEMD_SERVICE:${PN} = "cam-server.service"
SYSTEMD_AUTO_ENABLE = "enable"
