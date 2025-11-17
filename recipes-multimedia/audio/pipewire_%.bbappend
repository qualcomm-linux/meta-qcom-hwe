inherit useradd

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://0001-QCLINUX-bluez5-HFP-version-upgrade-to-1.8.patch \
                        file://0001-QCLINUX-pipewire-Add-dependency-of-audio-service-to-.patch \
"

DEPENDS:append:qcom = " useradd-qcom"

USERADD_PACKAGES = "${PN}"

GROUPADD_PARAM:${PN} = "--system pipewire"

USERADD_PARAM:${PN} = "--system --home / --no-create-home \
                       --comment 'PipeWire multimedia daemon' \
                       --gid pipewire --groups audio,video,input \
                       pipewire"
