inherit systemd

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"


RRECOMMENDS:${PN} += " \
    glibc-gconv-utf-16 \
    glibc-gconv-utf-32 \
"

SRC_URI:append:qcom = " file://0001-Setting-default-values-in-main.conf.patch \
                        file://0003-Bluez-unregister-includes-option-not-working.patch \
                        file://0004-gen_metadata-used-to-test-metadata-over-avrcp.patch \
                        file://0005-Set-variable-MAP_ROOT-map-messages-to-create-MAP-sdp.patch \
                        file://0006-obex-Move-size-emit-signal-to-plugins-instead-of-obe.patch \
                        file://0007-Fix-discoverable-property-not-emitted-on-updating-va.patch \
                        file://0008-Set-BREDR-not-supported-bit-in-AD-Flag-when-discover.patch \
                        file://0009-advertising-Do-not-crash-in-case-of-adv-update-failu.patch \
                        file://0010-device-Add-btd_device_bearer_is_connected.patch \
                        file://0011-device-Fix-marking-device-as-temporary.patch \
                        file://0012-adapter-Cancel-the-service-authorization-when-remote.patch \
                        file://0013-Manage-device-state-of-cross-transport-SMP-keys.patch \
                        file://0018-device-Remove-device-after-all-bearers-are-disconnec.patch \
                        file://0019-device-only-use-the-address-type-selection-algorithm.patch \
                        file://qca_set_bdaddr.service \
                        file://qca_set_bdaddr.sh \
                        file://0019-UPSTREAM-Fix-triggering-disconnect_timeout.patch \
                        file://0020-BACKPORT-shared-att-Fix-failing-to-set-security-level.patch \
                        file://0021-UPSTREAM-Resolve-obex-SRM-issue-for-PTS-testcases.patch \
                        file://0022-advertising-Fix-for-broadcaster-role.patch \
                        file://0023-UPSTREAM-Fix-the-obex-PBAP-GET-request-in-PTS-testin.patch \
                        file://0024-UPSTREAM-Add-Newmissedcalls-tag-in-obex-PBAP-GET-res.patch \
                        file://0025-UPSTREAM-obex-Check-for-supported-features-bit-value.patch \
                        file://0026-UPSTREAM-obex-Update-the-FTP-version-to-1.3-in-SDP-r.patch \
                        file://0027-UPSTREAM-obexctl-Enable-Map-folderlisting-operation-.patch \
                        file://0028-Use-system-bus-instead-of-session-for-obexd.patch \
                        file://0029-obexd-Add-system-bus-support-for-obexd.patch \
                        file://0030-tools-obexctl-Add-support-for-system-session-bus.patch \
                        file://0031-UPSTREAM-obex-Implement-support-for-message-listing-.patch \
                        file://0032-UPSTREAM-obexctl-Implement-support-for-message-listi.patch \
                        file://0033-UPSTREAM-Doc-Obex-Implement-support-for-message-list.patch \
                        file://0034-UPSTREAM-obex-Update-the-MAP-Client-version-to-1.4-i.patch \
                        file://0035-UPSTREAM-Audio-Profiles-version-upgrade.patch \
                        file://0036-device-Fix-device_is_connected-checking-for-services.patch \
                        file://0037-UPSTREAM-BNEP-Fix-BNEP-Unknown-Control-Message-in-PTS-.patch \
                        file://0038-UPSTREAM-obex-Add-null-check-for-data-pointer-for-PC.patch \
                        file://0039-UPSTREAM-obex-Add-messages_get_message-implementatio.patch \
                        file://0042-UPSTREAM-obex-Publish-SDP-record-for-Phonebook-Acces.patch \
                        file://0043-UPSTREAM-obex-Add-base-implementation-for-get_mas_in.patch \
                        file://0044-UPSTREAM-obex-Add-supported-features-tag-in-MAP-Clie.patch \
                        file://0045-Backport-bluez5-Setting-default-adapter-status.patch \
                        file://0046-Backport-bluez5-Remove-Endpoints-cache-entries-on.patch \
"

#Include obex to support obex related profiles like OPP, FTP, MAP, PBAP
RDEPENDS:${PN} += "${PN}-obex"

#Include only desired tools that are conditional on READLINE in bluez
INST_TOOLS_READLINE = " \
    tools/bluetooth-player \
    tools/obexctl \
    tools/btmgmt \
"

#Remove desired tools from noinst-tools
NOINST_TOOLS:remove = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'readline', '${INST_TOOLS_READLINE}', '', d)} \
"

do_install:append:qcom() {
    install -d ${D}${sysconfdir}/bluetooth/
    install -v -m 0644  ${S}/src/main.conf ${D}${sysconfdir}/bluetooth/

    #Install desired tools that upstream leaves in build area
    for f in ${INST_TOOLS_READLINE} ; do
        install -m 755 ${B}/$f ${D}/${bindir}
    done

    install -m 0644 ${B}/obexd/src/obex.service ${D}${systemd_system_unitdir}/obex.service

    #Create below directory which is used by obex service
    install -d ${D}${localstatedir}/bluetooth

    # Install script to set unique BDA
    install -d ${D}${sysconfdir}/initscripts
    install -m 755 ${WORKDIR}/qca_set_bdaddr.sh ${D}${sysconfdir}/initscripts/

    # Install service that will run qca_set_bdaddr.sh script on boot
    install -m 0644 ${WORKDIR}/qca_set_bdaddr.service -D ${D}${systemd_system_unitdir}/qca_set_bdaddr.service
    install -d ${D}${systemd_system_unitdir}/bluetooth.target.wants/
    ln -sf ${systemd_system_unitdir}/qca_set_bdaddr.service ${D}${systemd_system_unitdir}/bluetooth.target.wants/qca_set_bdaddr.service
}

SYSTEMD_PACKAGES += "${PN}-obex"
SYSTEMD_SERVICE:${PN}-obex += "obex.service"

SYSTEMD_SERVICE:${PN} += "qca_set_bdaddr.service"
FILES:${PN}:append = "${systemd_system_unitdir}/qca_set_bdaddr.service \
                           ${sysconfdir}/initscripts/qca_set_bdaddr.sh \
"
