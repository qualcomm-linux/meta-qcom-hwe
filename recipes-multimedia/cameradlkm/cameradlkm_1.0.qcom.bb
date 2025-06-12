inherit module

DESCRIPTION = "QCOM Camera drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "linux-kernel-headers-install-native"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-kernel.git;protocol=https"
SRCBRANCH  = "camera-kernel.qclinux.1.0.r1-rel"
SRCREV     = "06694f26bf073580553601bfa0dbfebd98c81db4"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/camera-kernel"

S = "${WORKDIR}/vendor/qcom/opensource/camera-kernel"

MODULES_INSTALL_TARGET = "modules_install headers_install"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300"

python get_soc_family() {
    need_machine = d.getVar('COMPATIBLE_MACHINE')
    if need_machine:
        import re
        compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
        for m in compat_machines:
            if re.match(need_machine, m):
                d.appendVar('SOC_FAM', m)
                break
    soc_family =  d.getVar('SOC_FAM')

    if re.match(soc_family, 'qcm6490'):
        d.appendVar('CAMERA_ARCH', soc_family);
        d.appendVar('HEADERS_DIR', 'camera_kt');
    else:
        d.appendVar('CAMERA_ARCH', 'all');
        d.appendVar('HEADERS_DIR', 'camera');
}

do_compile[prefuncs] += "get_soc_family"
do_install[prefuncs] += "get_soc_family"

EXTRA_OEMAKE += "CAMERA_ARCH='${CAMERA_ARCH}' SOC_FAM='${SOC_FAM}' HEADERS_DIR='${HEADERS_DIR}'"

do_install[postfuncs] += "do_module_signing"

do_install:append() {
	install -d ${D}${includedir}/camera/media
        install -d ${D}${includedir}/camera_kt/media
        install -m 0755 ${B}/sanitized_headers/camera_kt/media/*.h -D ${D}${includedir}/camera_kt/media/
        install -m 0755 ${B}/sanitized_headers/camera/media/*.h -D ${D}${includedir}/camera/media/
        install -d ${D}${includedir}/dt-bindings
        install -m 0644 ${S}/${HEADERS_DIR}/dt-bindings/*.h -D ${D}${includedir}/dt-bindings/
        install -m 0644 ${THISDIR}/camera.rules -D ${D}${sysconfdir}/udev/rules.d/camera.rules
        install -d ${D}${sysconfdir}/modprobe.d
        install -m 0644 ${THISDIR}/*.conf -D ${D}${sysconfdir}/modprobe.d/
}

FILES:${PN} += "${sysconfdir}/udev/rules.d \
		${sysconfdir}/modprobe.d"
