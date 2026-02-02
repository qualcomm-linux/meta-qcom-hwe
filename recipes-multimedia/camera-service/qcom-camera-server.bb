require camera-service.inc

DEPENDS += "qcom-camera-common"

RDEPENDS:${PN} += "qcom-camera-server-libs"

EXTRA_OECMAKE += "-DBUILD_CATEGORY=SERVER"

do_install:append () {

     install -d ${D}/etc/systemd/system/multi-user.target.wants/

     # enable the service for multi-user.target
     ln -sf /etc/systemd/system/cam-server.service \
        ${D}/etc/systemd/system/multi-user.target.wants/cam-server.service
}
