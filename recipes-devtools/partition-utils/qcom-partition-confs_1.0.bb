SUMMARY = "Machine specific partition configurations"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

DEPENDS += "qcom-gen-partitions-tool-native"

inherit python3native

PROVIDES += "virtual/partconf"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI = " \
    file://ufs/qcm6490-partitions.conf \
    file://ufs/qcs9100-partitions.conf \
    file://ufs/qcs8300-partitions.conf \
    file://emmc/qcm6490-partitions.conf \
    file://emmc/qcs9100-partitions.conf \
    file://emmc/qcs8300-partitions.conf \
"

S = "${WORKDIR}"

do_configure[noexec] = "1"

# Current partitions configuration is SOC_FAMILY specific.
# When extending to machines make sure PACKAGE_ARCH is updated
PARTCONF ?= ""
PARTCONF:qcm6490 = "ufs/qcm6490-partitions.conf emmc/qcm6490-partitions.conf"
PARTCONF:qcs9100 = "ufs/qcs9100-partitions.conf emmc/qcs9100-partitions.conf"
PARTCONF:qcs8300 = "ufs/qcs8300-partitions.conf emmc/qcs8300-partitions.conf"

PACKAGE_ARCH = "${SOC_ARCH}"

do_compile[cleandirs] += "${B}/partition_emmc ${B}/partition_ufs"
do_compile() {
    set -- ${PARTCONF}

    # Generate partition.xml using gen_partition utility for ufs and emmc
    ${PYTHON} ${STAGING_BINDIR_NATIVE}/gen_partition.py \
        -i ${WORKDIR}/"$1" \
        -o ${B}/partition_ufs/partition.xml

    ${PYTHON} ${STAGING_BINDIR_NATIVE}/gen_partition.py \
        -i ${WORKDIR}/"$2" \
        -o ${B}/partition_emmc/partition.xml
}

inherit deploy
do_deploy() {
    mkdir -p ${DEPLOYDIR}/partition_ufs
    mkdir -p ${DEPLOYDIR}/partition_emmc
    install -m 0644 ${B}/partition_ufs/partition.xml -D ${DEPLOYDIR}/partition.xml
    install -m 0644 ${B}/partition_ufs/partition.xml -D ${DEPLOYDIR}/partition_ufs/partition.xml
    install -m 0644 ${B}/partition_emmc/partition.xml -D ${DEPLOYDIR}/partition_emmc/partition.xml
}
addtask deploy before do_build after do_install
