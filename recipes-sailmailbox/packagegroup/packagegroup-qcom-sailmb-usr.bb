LICENSE = "LICENSE.qcom-2"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"
SAIL_MAILBOX:qcs9100 = 'True'
SAIL_MAILBOX ?= 'False'

SAIL_MB:qcs8300 = 'True'
SAIL_MB ?= 'False'

# Sensors Image and Debugging utilities
RDEPENDS:${PN}:append:qcom-custom-bsp:qcs9100 = "\
     ${@oe.utils.conditional('SAIL_MAILBOX', 'True', 'sail-mailbox', '', d)} \
"

# Sensors Image and Debugging utilities
RDEPENDS:${PN}:append:qcom-custom-bsp:qcs8300 = "\
     ${@oe.utils.conditional('SAIL_MB', 'True', 'sail-mailbox', '', d)} \
"
