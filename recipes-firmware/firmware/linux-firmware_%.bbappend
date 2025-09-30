# While all HLOS firmware is ideally sourced from `linux-firmware.git`,
# there are cases where it may not be up to date. In such scenarios,
# specific firmware components need to be picked from downstream repos.
# This bbappend enables inclusion of such downstream firmware and
# installs it under `/lib/firmware/updates`, allowing to override or
# supplement upstream firmware as needed.

FIRMWARE_UPDATES_DIR = "${nonarch_base_libdir}/firmware/updates"
FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/download/software/chip/component"

require linux-firmware-updates-gpu.inc
require linux-firmware-updates-vpu.inc
require linux-firmware-updates-hlosfw.inc
