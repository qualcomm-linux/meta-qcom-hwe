inherit pkgconfig systemd
include wpa-supplicant.inc

DEFAULT_PREFERENCE = "-1"

LT_MAJOR = "1"
LT_MINOR = "0"
LT_PATCH = "0"

FILESEXTRAPATHS:prepend := " ${THISDIR}/files:"

DEPENDS += "glib-2.0 dbus libnl"
RRECOMMENDS:${PN} = "wpa-supplicant-passphrase wpa-supplicant-cli"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[gnutls] = ",,gnutls libgcrypt"
PACKAGECONFIG[openssl] = ",,openssl"


SYSTEMD_SERVICE:${PN} = "wpa_supplicant.service"
SYSTEMD_AUTO_ENABLE = "disable"

SRC_URI = "git://w1.fi/hostap.git;protocol=git;branch=main \
           file://defconfig \
           file://wpa-supplicant.sh \
           "
SRC_URI += "file://0001-WNM-Extend-workaround-for-broken-AP-operating-class-behavior.patch;patchdir=${WORKDIR}/git/"
SRC_URI += "file://0001-Use-helper-functions-to-access-RSNE-RSNXE-from-BSS-e.patch;patchdir=${WORKDIR}/git/"
SRC_URI += "file://0001-FT-Do-not-omit-RSNXE-from-FT-initial-mobility-domain.patch;patchdir=${WORKDIR}/git/"
SRC_URI += "file://0001-wpa_supplicant-stop-wpa_supplicant-as-part-of-device.patch;patchdir=${WORKDIR}/git/"
SRC_URI += "file://wpa_supplicant_override.conf"

SRCREV = "9716bf1160beb677e965d9e6475d6c9e162e8374"

CVE_PRODUCT = "wpa_supplicant"

S = "${WORKDIR}/git/wpa_supplicant"

PACKAGES:prepend = "wpa-supplicant-passphrase wpa-supplicant-cli "
FILES:wpa-supplicant-passphrase = "${bindir}/wpa_passphrase"
FILES:wpa-supplicant-cli = "${bindir}/wpa_cli"
FILES:${PN} += "${datadir}/dbus-1/system-services/* ${systemd_system_unitdir}/*"

do_configure () {
        install -m 0755 ${WORKDIR}/defconfig .config

        if echo "${PACKAGECONFIG}" | grep -qw "openssl"; then
                ssl=openssl
        elif echo "${PACKAGECONFIG}" | grep -qw "gnutls"; then
                ssl=gnutls
        fi
        if [ -n "$ssl" ]; then
                sed -i "s/%ssl%/$ssl/" .config
        fi

        # For rebuild
        rm -f *.d dbus/*.d

        sed -i 's/libwpa_client.so/libwpa_client.so.${LT_MAJOR}.${LT_MINOR}.${LT_PATCH}/g' Makefile
        sed -i '/\($(Q)$(CC) $(LDFLAGS) -o $@ $(CFLAGS) -shared -fPIC $^\)/  s/\(-shared\)/\1 -Wl,-soname,libwpa_client.so.${LT_MAJOR}/' Makefile
        sed -i '/install -m 0644 -D ..\/src\/common\/wpa_ctrl.h $(DESTDIR)\/$(INCDIR)\/wpa_ctrl.h/ i\
	ln -sf libwpa_client.so.${LT_MAJOR}.${LT_MINOR}.${LT_PATCH} $(DESTDIR)/$(LIBDIR)/libwpa_client.so.${LT_MAJOR}\
	ln -sf libwpa_client.so.${LT_MAJOR}.${LT_MINOR}.${LT_PATCH} $(DESTDIR)/$(LIBDIR)/libwpa_client.so' Makefile
        sed -i '/rm -f libwpa_test1 libwpa_test2/ i\
	rm -f libwpa_client.so.${LT_MAJOR}\
	rm -f libwpa_client.so' Makefile
}

export EXTRA_CFLAGS = "${CFLAGS}"
export BINDIR = "${sbindir}"

do_install:append() {
        install -d ${D}${sysconfdir}/network/if-pre-up.d/
        install -d ${D}${sysconfdir}/network/if-post-down.d/
        install -d ${D}${sysconfdir}/network/if-down.d/
        install -m 755 ${WORKDIR}/wpa-supplicant.sh ${D}${sysconfdir}/network/if-pre-up.d/wpa-supplicant
        cd ${D}${sysconfdir}/network/ && \
        ln -sf ../if-pre-up.d/wpa-supplicant if-post-down.d/wpa-supplicant

        install -d ${D}/${sysconfdir}/dbus-1/system.d
        install -m 644 ${S}/dbus/dbus-wpa_supplicant.conf ${D}/${sysconfdir}/dbus-1/system.d
        install -d ${D}/${datadir}/dbus-1/system-services
        install -m 644 ${S}/dbus/*.service ${D}/${datadir}/dbus-1/system-services

        if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
                install -d ${D}/${systemd_system_unitdir}
                install -m 644 ${S}/systemd/*.service ${D}/${systemd_system_unitdir}
		install -d ${D}${sysconfdir}/systemd/system/wpa_supplicant.service.d
		install -m 0644 ${WORKDIR}/wpa_supplicant_override.conf \
			${D}${sysconfdir}/systemd/system/wpa_supplicant.service.d/wpa_supplicant_override.conf

        fi
}

pkg_postinst:${PN} () {
        # If we're offline, we don't need to do this.
        if [ "x$D" = "x" ]; then
                killall -q -HUP dbus-daemon || true
        fi

}

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

FILES:${PN} += "/usr/include/*"
FILES:${PN} += "${sysconfdir}/systemd/system/wpa_supplicant.service.d/override.conf"
