require camera-service.inc

DEPENDS += "qcom-camera-common"

# Build client libraries
EXTRA_OECMAKE += "-DBUILD_CATEGORY=CLIENT"

# Disable Debian-style auto-renaming for all packages.
# Useful here because the recipe produces only one library and we want to keep
# the original package names instead of Debian auto-generated ones.
python __anonymous() {
    for p in (d.getVar('PACKAGES') or '').split():
        d.setVar('DEBIAN_NOAUTONAME:%s' % p, '1')
}
