# Install watchdog-keepalive and watchdog-config into the OSTree initramfs
# so that wd_keepalive is available to arm the hardware watchdog early in boot.
# CR: 4501258

PACKAGE_INSTALL:append:qcom = " watchdog-keepalive watchdog-config"
