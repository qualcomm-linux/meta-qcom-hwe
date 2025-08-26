FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad/1.22:"

SRC_URI:append:qcs615  = "\
  file://0001-waylandsink-Add-DRM-modifiers-support.patch \
"

