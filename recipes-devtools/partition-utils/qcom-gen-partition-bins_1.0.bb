SUMMARY = "Generate and place partition artifacts in DEPLOYDIR"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = " file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DEPENDS += "qcom-ptool-native"

inherit python3native

do_configure[noexec] = "1"

do_compile[depends] += " \
    virtual/partconf:do_deploy \
   "
do_compile[cleandirs] += "${B}/partition_emmc ${B}/partition_ufs"
do_compile() {
    # Run ptool to generate partition bins for all storage types
    ${PYTHON} ${STAGING_BINDIR_NATIVE}/ptool.py -x ${DEPLOY_DIR_IMAGE}/partition.xml

    if [ -f ${DEPLOY_DIR_IMAGE}/partition_emmc/partition.xml ]; then
        cd ${B}/partition_emmc && \
        ${PYTHON} ${STAGING_BINDIR_NATIVE}/ptool.py -x ${DEPLOY_DIR_IMAGE}/partition_emmc/partition.xml
    fi

    if [ -f ${DEPLOY_DIR_IMAGE}/partition_ufs/partition.xml ]; then
        cd ${B}/partition_ufs && \
        ${PYTHON} ${STAGING_BINDIR_NATIVE}/ptool.py -x ${DEPLOY_DIR_IMAGE}/partition_ufs/partition.xml
    fi
}

inherit deploy
do_deploy() {
    install -m 0644 ${B}/gpt_backup*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/gpt_both*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/gpt_empty*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/gpt_main*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/patch*.xml -D ${DEPLOYDIR}
    install -m 0644 ${B}/rawprogram*.xml -D ${DEPLOYDIR}
    install -m 0644 ${B}/zeros_*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/wipe_rawprogram_PHY*.xml -D ${DEPLOYDIR}

    if [ -f ${DEPLOY_DIR_IMAGE}/partition_emmc/partition.xml ]; then
        mkdir -p ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/gpt_backup*.bin -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/gpt_both*.bin -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/gpt_empty*.bin -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/gpt_main*.bin -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/patch*.xml -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/rawprogram*.xml -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/zeros_*.bin -D ${DEPLOYDIR}/partition_emmc
        install -m 0644 ${B}/partition_emmc/wipe_rawprogram_PHY*.xml -D ${DEPLOYDIR}/partition_emmc
    fi

    if [ -f ${DEPLOY_DIR_IMAGE}/partition_ufs/partition.xml ]; then
        mkdir -p ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/gpt_backup*.bin -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/gpt_both*.bin -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/gpt_empty*.bin -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/gpt_main*.bin -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/patch*.xml -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/rawprogram*.xml -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/zeros_*.bin -D ${DEPLOYDIR}/partition_ufs
        install -m 0644 ${B}/partition_ufs/wipe_rawprogram_PHY*.xml -D ${DEPLOYDIR}/partition_ufs
    fi
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${SOC_ARCH}"
