# While hexagon dsp binaries are ideally sourced from `hexagon-dsp-binaries.git`
# there are cases where they may not be up to date and specific packages need to
# be picked from downstream repos. This bbappend is to install them under `/usr/lib/dsp`
require firmware-qcm6490.inc
require firmware-qcs9100.inc
require firmware-qcs8300.inc
require firmware-qcs615.inc

QCM6490_SRC_URI ?= "${FW_ARTIFACTORY}/${QCM6490_FW_BIN_PATH}/QCM6490_dspso.zip;name=QCM6490_dspso"
QCS9100_SRC_URI ?= "${FW_ARTIFACTORY}/${QCS9100_FW_BIN_PATH}/QCS9100_dspso.zip;name=QCS9100_dspso"
QCS8300_SRC_URI ?= "${FW_ARTIFACTORY}/${QCS8300_FW_BIN_PATH}/QCS8300_dspso.zip;name=QCS8300_dspso"
QCS615_SRC_URI  ?= "${FW_ARTIFACTORY}/${QCS615_FW_BIN_PATH}/QCS615_dspso.zip;name=QCS615_dspso"

SRC_URI[QCM6490_fw.sha256sum] = "${QCM6490_DSPSO_SHA256SUM}"
SRC_URI[QCS9100_fw.sha256sum] = "${QCS9100_DSPSO_SHA256SUM}"
SRC_URI[QCS8300_fw.sha256sum] = "${QCS8300_DSPSO_SHA256SUM}"
SRC_URI[QCS615_fw.sha256sum] = "${QCS615_DSPSO_SHA256SUM}"
SRC_URI += "${QCM6490_SRC_URI}"
SRC_URI += "${QCS9100_SRC_URI}"
SRC_URI += "${QCS8300_SRC_URI}"
SRC_URI += "${QCS615_SRC_URI}"

do_install:append:qcom (){
    for dir in "${WORKDIR}"/*_dspso/; do
        [ -d "$dir" ] || continue
        fwdir=$(basename "$dir")
        bbdebug 1 "Processing firmware directory: ${fwdir}"

        socdir=$(printf '%s' "$fwdir" | sed 's/_dspso//g' | tr '[:upper:]' '[:lower:]')
        install -d ${D}/${socdir}${nonarch_base_libdir}/dsp
        [ -d "${WORKDIR}/$fwdir/usr/lib/dsp" ] && \
        find "${WORKDIR}/$fwdir/usr/lib/dsp" -maxdepth 1 -type d \
                -exec cp -r {} ${D}/${socdir}${nonarch_base_libdir}/dsp \;
    done
}

do_hexagon_dsp_binaries_pkgd_fixup() {
    for soc in qcm6490 qcs615 qcs8300 qcs9100; do
        if [ -d "${PKGD}/${soc}${nonarch_base_libdir}/dsp" ]; then
            install -d "${PKGD}${nonarch_base_libdir}/dsp/"
            cp -a "${PKGD}/${soc}${nonarch_base_libdir}/dsp/." "${PKGD}${nonarch_base_libdir}/dsp/"
        fi

    done

}

addtask hexagon_dsp_binaries_pkgd_fixup after do_package before do_package_write_rpm

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
