# While hexagon dsp binaries are ideally sourced from `hexagon-dsp-binaries.git`
# there are cases where they may not be up to date and specific packages need to
# be picked from downstream repos. This bbappend is to install them under `/usr/lib/dsp`

FW_ARTIFACTORY = "https://softwarecenter.qualcomm.com/nexus/generic/software/chip"
HLOSFW_COMPONENT ?= "qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public"
HLOSFW_BUILD_ID ?= "r1.0_00108.0"
HLOSFW_ARTIFACTORY ?= "${FW_ARTIFACTORY}/${HLOSFW_COMPONENT}/${HLOSFW_BUILD_ID}"

QCM6490_UFS_BIN_PATH ?= "qcm6490-le-1-0/common/build/ufs/bin"
QCS9100_UFS_BIN_PATH ?= "qcs9100-le-1-0/common/build/ufs/bin"
QCS8300_UFS_BIN_PATH ?= "qcs8300-le-1-0/common/build/ufs/bin"
QCS615_CMN_BIN_PATH ?= "qcs615-le-1-0/common/build/common/bin"

QCM6490_VPU_SHA256SUM ?= "9f7651efc5a79f68d060e01e04ab6b807b5c185fded59d3d416c0d3b0f1a7b35"
QCS9100_VPU_SHA256SUM ?= "9d3dc8fb72a3ace8034e2623d5ab2298089027a2228af30f44f59d32560af0d3"
QCS8300_VPU_SHA256SUM ?= "f963bb2116d338e407676039724d42fa6242be91214ad16a4c4e635e8bc180a5"
QCS615_VPU_SHA256SUM  ?= "1247a5585346408a82bdd44f248676bed22be70dfd481d938c40ce8e2448200c"

QCM6490_SRC_URI ?= "${HLOSFW_ARTIFACTORY}/${QCM6490_UFS_BIN_PATH}/QCM6490_fw.zip;name=QCM6490_fw"
QCS9100_SRC_URI ?= "${HLOSFW_ARTIFACTORY}/${QCS9100_UFS_BIN_PATH}/QCS9100_fw.zip;name=QCS9100_fw"
QCS8300_SRC_URI ?= "${HLOSFW_ARTIFACTORY}/${QCS8300_UFS_BIN_PATH}/QCS8300_fw.zip;name=QCS8300_fw"
QCS615_SRC_URI  ?= "${HLOSFW_ARTIFACTORY}/${QCS615_CMN_BIN_PATH}/QCS615_fw.zip;name=QCS615_fw"

SRC_URI[QCM6490_fw.sha256sum] = "${QCM6490_VPU_SHA256SUM}"
SRC_URI[QCS9100_fw.sha256sum] = "${QCS9100_VPU_SHA256SUM}"
SRC_URI[QCS8300_fw.sha256sum] = "${QCS8300_VPU_SHA256SUM}"
SRC_URI[QCS615_fw.sha256sum] = "${QCS615_VPU_SHA256SUM}"
SRC_URI += "${QCM6490_SRC_URI}"
SRC_URI += "${QCS9100_SRC_URI}"
SRC_URI += "${QCS8300_SRC_URI}"
SRC_URI += "${QCS615_SRC_URI}"

do_install:append:qcom (){
    for dir in "${WORKDIR}"/*_fw/; do
        [ -d "$dir" ] || continue
        fwdir=$(basename "$dir")
        bbdebug 1 "Processing firmware directory: ${fwdir}"

        socdir=$(echo "${fwdir/_fw/}" | tr '[:upper:]' '[:lower:]')
        install -d ${D}/${socdir}${nonarch_base_libdir}/dsp
        [ -d "${WORKDIR}/$fwdir/usr/lib/dsp" ] && \
        find "${WORKDIR}/$fwdir/usr/lib/dsp" -maxdepth 1 -type d \
                -exec cp -r {} ${D}/${socdir}${nonarch_base_libdir}/dsp \;
    done
}

DSP_UPDATE_PACKAGES = " \
    ${PN}-qcm6490-updates \
    ${PN}-qcs9100-updates \
    ${PN}-qcs8300-updates \
    ${PN}-qcs615-updates \
"
PACKAGES:prepend = "${DSP_UPDATE_PACKAGES}"

FILES:${PN}-qcm6490-updates = "qcm6490/*"
FILES:${PN}-qcs9100-updates = "qcs9100/*"
FILES:${PN}-qcs8300-updates = "qcs8300/*"
FILES:${PN}-qcs615-updates  = "qcs615/*"

python populate_packages:append() {
    import os
    import shutil

    pkgdest =  d.getVar('PKGDEST')
    dsp_update_pkgs = d.getVar('DSP_UPDATE_PACKAGES').split()

    for pkg in dsp_update_pkgs:
        soc = pkg.rsplit("-", 2)[1]
        soc_dir = '%s/%s/%s' % (pkgdest, pkg, soc)
        parent_dir = os.path.dirname(soc_dir)

        for item in os.listdir(soc_dir):
            src = os.path.join(soc_dir, item)
            dest = os.path.join(parent_dir, item)
            shutil.move(src, dest)

        os.rmdir(soc_dir)
}

INSANE_SKIP:${PN}-qcm6490-updates = "arch libdir file-rdeps textrel"
INSANE_SKIP:${PN}-qcs9100-updates = "arch libdir file-rdeps textrel"
INSANE_SKIP:${PN}-qcs8300-updates = "arch libdir file-rdeps textrel"
INSANE_SKIP:${PN}-qcs615-updates = "arch libdir file-rdeps textrel"
