inherit autotools pkgconfig systemd useradd

DESCRIPTION = "property vault managment"
SUMMARY = "property vault managment"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

COMPATIBLE_MACHINE = "(qcom)"

SRCPROJECT = "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https"
SRCBRANCH  = "le-utils.qclinux.1.0.r2-rel"
SRCREV     = "b1ccc95e5c0794b91389b4174fe5960710173437"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=le-utils"

S = "${WORKDIR}/le-utils/property-vault"

DEPENDS += "libselinux syslog-plumber glib-2.0"

PACKAGECONFIG ??= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"
PACKAGECONFIG[systemd] = "--with-systemdunitdir=${systemd_system_unitdir}/,--with-systemdunitdir="

SYSTEMD_SERVICE:${PN} = "property-vault.service persist-property-vault.service"

do_install:append() {
    install -b -m 0644 /dev/null -D ${D}${sysconfdir}/build.prop 
    chown system:system ${D}${sysconfdir}/build.prop
    install -b -m 0644 /dev/null -D ${D}/var/leutils/build.prop
    chown system:system ${D}/var/leutils/build.prop
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-M system"

FILES:${PN} += " /etc/build.prop /var/leutils/build.prop"

python () {
    mach_overrides = d.getVar('MACHINEOVERRIDES').split(":")
    if ('qcom-base-bsp' in mach_overrides):
        raise bb.parse.SkipRecipe("property-vault not compatible with qcom-base-bsp")
}
