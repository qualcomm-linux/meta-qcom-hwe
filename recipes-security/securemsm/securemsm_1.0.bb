inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 glibc linux-kernel-qcom-headers qcom-libdmabufheap"

QCM6490_SHA256SUM = "a87a0688068b0686df5c28cad0023aebb79f68440d603feda5788eee20fa55db"
QCS9100_SHA256SUM = "c17d31201657cf4a39ac4a937b03c7af63a4548ea966bedfa6d4738188d4976e"
QCS8300_SHA256SUM = "b01620e1a3e3653806f851c3e78520e303d09b231740683a26ad16bc67f52604"
QCS615_SHA256SUM = "29123c31e54f9368f0329c6f2663f4945097f34d5494f445df0b642d63473df6"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum] = "${QCS615_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/* /var/local/*"

INSANE_SKIP:${PN} += "debug-files"

