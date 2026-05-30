LICENSE = "LICENSE.qcom-2"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = " \
        packagegroup-qcom-perf \
        "

RDEPENDS:packagegroup-qcom-perf = "\
    qcom-perf-hal \
    "
