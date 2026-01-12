inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "Audio Calibration Library"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audioreach-conf.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "0069beaf1dca5ff5aec17b2b8cf2fb69a3cb3394"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audioreach-conf"

S = "${WORKDIR}/audio/opensource/audioreach-conf/ar-acdb/acdbdata"

do_install:append:qcm6490() {
    mkdir -p -m 0777 ${D}${sysconfdir}/acdbdata
    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/QCM6490_IDP
    install -m 0644 ${S}/qcm6490/qcm6490_idp/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/QCM6490_IDP/acdb_cal.acdb
    install -m 0644 ${S}/qcm6490/qcm6490_idp/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/QCM6490_IDP/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/QCS6490_RB3Gen2
    install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/QCS6490_RB3Gen2/acdb_cal.acdb
    install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/QCS6490_RB3Gen2/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs6490_rb3gen2_video
    install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2_video/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs6490_rb3gen2_video/acdb_cal.acdb
    install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2_video/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs6490_rb3gen2_video/workspaceFileXml.qwsp

    # Install vision configuration for multiple device variants
    for variant in qcs6490_rb3gen2_vision qcom_Inc.RoboticsRB3gen2; do
        mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/${variant}
        install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2_vision/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/${variant}/acdb_cal.acdb
        install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2_vision/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/${variant}/workspaceFileXml.qwsp
    done

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs6490_rb3gen2_ia
    install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2_ia/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs6490_rb3gen2_ia/acdb_cal.acdb
    install -m 0644 ${S}/qcs6490/qcs6490_rb3gen2_ia/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs6490_rb3gen2_ia/workspaceFileXml.qwsp
}

do_install:append:qcs8300() {
    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/MONACO_EVK
    install -m 0644 ${S}/qcs8275/iq8_8275_evk/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/MONACO_EVK/acdb_cal.acdb
    install -m 0644 ${S}/qcs8275/iq8_8275_evk/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/MONACO_EVK/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/monaco_monza
    install -m 0644 ${S}/qcs8275/monaco_monza/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/monaco_monza/acdb_cal.acdb
    install -m 0644 ${S}/qcs8275/monaco_monza/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/monaco_monza/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs8300_ridesx
    install -m 0644 ${S}/qcs8300/qcs8300_ridesx/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs8300_ridesx/acdb_cal.acdb
    install -m 0644 ${S}/qcs8300/qcs8300_ridesx/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs8300_ridesx/workspaceFileXml.qwsp
}

do_install:append:qcs9100() {
    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs9100_ridesx
    install -m 0644 ${S}/qcs9100/qcs9100_ridesx/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs9100_ridesx/acdb_cal.acdb
    install -m 0644 ${S}/qcs9100/qcs9100_ridesx/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs9100_ridesx/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/LEMANS_EVK
    install -m 0644 ${S}/qcs9075/qcs9075_rb8/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/LEMANS_EVK/acdb_cal.acdb
    install -m 0644 ${S}/qcs9075/qcs9075_rb8/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/LEMANS_EVK/workspaceFileXml.qwsp
}

DEPENDS = "qcom-kvh2xml"

SOLIBS = ".so*"

FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} = "dev-so"
