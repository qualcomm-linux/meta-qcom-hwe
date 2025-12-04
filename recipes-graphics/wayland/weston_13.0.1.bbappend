LICENSE_FLAGS = ""
LICENSE = "MIT"

FILESEXTRAPATHS:prepend := "${THISDIR}/weston-launch:"

SRC_URI:append:qcom = "   file://weston.png \
              file://weston.desktop \
              file://xwayland.weston-start \
              file://systemd-notify.weston-start"

SRC_URI:append:qcom-custom-bsp = "   \
              file://0001-weston-Add-stack-protector-flag.patch"

SRC_URI:append:qcm6490 = "  file://weston.ini"

SRC_URI:append:qcm6490:qcom-custom-bsp = "  \
              file://0001-weston-add-sdm-option.patch \
              file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
              "

SRC_URI:append:qcs9100 = "  file://0001-weston-add-sdm-option.patch \
                            file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
                            file://weston.ini \
                            file://0001-weston-backport-upstream-fix-for-multi-DPU-hotplug.patch \
                            "

SRC_URI:append:qcs9100:qcom-base-bsp = " file://0001-weston-avoid-duplicate-format.patch"

SRC_URI:append:qcs8300 = "  file://0001-weston-add-sdm-option.patch \
                            file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
                            file://weston.ini \
                            "

SRC_URI:append:qcs8300:qcom-base-bsp = " file://0001-weston-avoid-duplicate-format.patch"

SRC_URI:append:qcs615  = "  file://0001-weston-add-sdm-option.patch \
                            file://0001-weston-avoid-duplicate-format.patch \
                            file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
                            "

DEPENDS:append:qcom-custom-bsp = " property-vault qcom-libdmabufheap"

EXTRA_OEMESON += "-Dbackend-default=auto -Dbackend-rdp=true"

RRECOMMENDS:${PN} = "weston-launch liberation-fonts"

REQUIRED_DISTRO_FEATURES:remove:qcom = "opengl"

# select compositor, enable simple and demo clients and enable EGL
PACKAGECONFIG:qcom = " \
                 egl \
                 clients \
                 shell-desktop \
                 screenshare \
                 shell-fullscreen \
                 shell-ivi \
                 image-jpeg \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11 xwayland', '', d)} \
                 "

#Enable KMS RDP Screenshare for all chipsets
PACKAGECONFIG:append= "kms rdp screenshare"

# Weston on SDM
PACKAGECONFIG[sdm] = "-Dbackend-sdm=true,-Dbackend-sdm=false"
PACKAGECONFIG[rdp] = "-Dbackend-rdp=true,-Dbackend-rdp=false,freerdp"
PACKAGECONFIG[screenshare] = "-Dscreenshare=true,-Dscreenshare=false"
# Weston with disabling display power key
PACKAGECONFIG[disablepowerkey] = "-Ddisable-power-key=true,-Ddisable-power-key=false"

# Enable when sdm downstream is needed
#LDFLAGS:append:qcm6490  = " -ldrmutils -ldisplaydebug -lglib-2.0 -ldmabufheap"

# #meson script's CPP flags
#CXXFLAGS:append:qcm6490  = " -I${STAGING_INCDIR}/sdm"
# CXXFLAGS:append:qcm6490  = " -I${STAGING_INCDIR}/display/display"

do_install:append:qcm6490() {
    install -m 0644 ${WORKDIR}/weston.ini -D ${D}${sysconfdir}/xdg/weston/weston.ini
}

do_install:append:qcs9100() {
    install -m 0644 ${WORKDIR}/weston.ini -D ${D}${sysconfdir}/xdg/weston/weston.ini
}

do_install:append:qcs8300() {
    install -m 0644 ${WORKDIR}/weston.ini -D ${D}${sysconfdir}/xdg/weston/weston.ini
}

FILES:${PN} += "${bindir}/*"
FILES:${PN} += " ${libdir}/libweston-13/*.so"
FILES:${PN} += " ${libdir}/*.so"
FILES:${PN}:append:qcm6490 = " ${sysconfdir}/xdg/weston/weston.ini"
FILES:${PN}:append:qcs9100 = " ${sysconfdir}/xdg/weston/weston.ini"
FILES:${PN}:append:qcs8300 = " ${sysconfdir}/xdg/weston/weston.ini"
