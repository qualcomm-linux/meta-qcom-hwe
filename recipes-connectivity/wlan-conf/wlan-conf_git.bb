inherit autotools systemd
DESCRIPTION = "Device specific config"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/qcom-opensource/mdm-init.git;protocol=https"
SRCBRANCH  = "wlan-os-service.qclinux.1.1.r1-rel"
SRCREV     = "335db4f88b4aa8c601f3ee93cb043f1c3fe27545"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=mdm-init \
           file://wlan_daemon.service \
           file://multi-vif.service \
           file://wifi.conf"

SYSTEMD_SYSUSERS = "wifi.conf"
SYSTEMD_SUPPORTS_SYSUSERS = "1"

S = "${WORKDIR}/mdm-init"

do_install:append: () {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}/etc/initscripts
		cp ${D}/etc/init.d/wlan ${D}/etc/initscripts/wlan
		install -d ${D}/etc/systemd/system/
		install -m 0644 ${WORKDIR}/multi-vif.service -D ${D}/etc/systemd/system/multi-vif.service
		install -m 0644 ${WORKDIR}/wlan_daemon.service -D ${D}/etc/systemd/system/wlan_daemon.service
		install -d ${D}/etc/systemd/system/multi-user.target.wants/
		ln -sf /etc/systemd/system/wlan_daemon.service \
			${D}/etc/systemd/system/multi-user.target.wants/wlan_daemon.service
		install -d ${D}${nonarch_libdir}/sysusers.d
		install -m 0644 ${WORKDIR}/wifi.conf ${D}${nonarch_libdir}/sysusers.d/wifi.conf
	fi
}

FILES:${PN} += "${sysconfdir}/systemd/system/*"
FILES:${PN} += "${nonarch_base_libdir}/firmware/wlan/qca_cld/* ${sysconfdir}/init.d/* "
FILES:${PN} += "${nonarch_libdir}/sysusers.d/wifi.conf"

EXTRA_OECONF:append:qcm6490 = " --enable-qcm6490=yes "
EXTRA_OECONF:append:qcs9100 = " --enable-upstream=yes "
EXTRA_OECONF:append:qcs8300 = " --enable-upstream=yes "
EXTRA_OECONF:append:qcs615 = " --enable-upstream=yes "

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}  = "wlan_daemon.service"
