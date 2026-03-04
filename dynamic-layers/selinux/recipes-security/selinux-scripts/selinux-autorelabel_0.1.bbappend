#FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/${BPN}:"

FILESEXTRAPATHS:append := "${THISDIR}:"

SRC_URI += "file://selinux-qcom-autorelabel.sh  \
"

do_install:append:qcom(){
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sota', 'true', 'false', d)}; then
        install -d ${D}${bindir}
        install -m 755 ${WORKDIR}/selinux-qcom-autorelabel.sh ${D}${bindir}
        sed -i 's|ExecStart=/usr/bin/selinux-autorelabel.sh|ExecStart=/usr/bin/selinux-qcom-autorelabel.sh|g' ${D}${systemd_unitdir}/system/selinux-autorelabel.service
        sed -i '/Before=sysinit.target/a After=local-fs.target' ${D}${systemd_unitdir}/system/selinux-autorelabel.service
        sed -i 's|Before=sysinit.target|Before=sysinit.target systemd-tmpfiles-setup.service|g' ${D}${systemd_unitdir}/system/selinux-autorelabel.service
    fi
}
