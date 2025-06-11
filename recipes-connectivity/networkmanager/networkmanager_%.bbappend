FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom= " file://99-unmanaged-devices.conf \
                       file://10-dnsmasq-port.conf \
"

do_install:append:qcom() {
    install -D -m 0644 ${WORKDIR}/99-unmanaged-devices.conf ${D}${sysconfdir}/NetworkManager/conf.d/99-unmanaged-devices.conf
    install -D -m 0644 ${WORKDIR}/10-dnsmasq-port.conf ${D}${sysconfdir}/NetworkManager/dnsmasq-shared.d/10-dnsmasq-port.conf
}
