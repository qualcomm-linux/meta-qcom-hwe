FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = "file://0001-PENDING-Ofono-Hfp-Client-version-upgrade.patch \
"
do_install:append:qcom () {
  sed -i '/<\/busconfig>/i\ 
  <policy user="\pulse\">\
    <allow send_destination=\"org.ofono\"/>\
  </policy>\
\
  <policy user="\pipewire\">\
    <allow send_destination=\"org.ofono\"/>\
  </policy>' ${D}${sysconfdir}/dbus-1/system.d/ofono.conf
}
