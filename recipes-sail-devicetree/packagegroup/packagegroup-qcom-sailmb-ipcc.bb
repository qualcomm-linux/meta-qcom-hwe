LICENSE = "BSD-3-Clause-Clear"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

SAIL_MAILBOX_KERNEL:qcs9100 = 'True'
SAIL_MAILBOX_KERNEL ?= 'False'


SAIL_MB_KERNEL:qcs8300 = 'True'
SAIL_MB_KERNEL ?= 'False'

# Sensors Image and Debugging utilities
RDEPENDS:${PN}:append:qcom-custom-bsp:qcs9100 = "\
     ${@oe.utils.conditional('SAIL_MAILBOX_KERNEL', 'True', 'sail-mailbox-ipcc', '', d)} \
     ${@oe.utils.conditional('SAIL_MAILBOX_KERNEL', 'True', 'sail-devicetree', '', d)} \
"


# Sensors Image and Debugging utilities
RDEPENDS:${PN}:append:qcom-custom-bsp:qcs8300 = "\
     ${@oe.utils.conditional('SAIL_MB_KERNEL', 'True', 'sail-mailbox-ipcc', '', d)} \
     ${@oe.utils.conditional('SAIL_MB_KERNEL', 'True', 'sail-devicetree', '', d)} \
"
