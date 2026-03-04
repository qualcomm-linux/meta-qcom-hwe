inherit cmake pkgconfig qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Chicdk Autogen (KT variant)"

# If KT depends on KT stack, use -kt deps here; adjust as required
DEPENDS:qcom-custom-bsp += "camxapi-kt protobuf-native protobuf"

# Use the actual checksums for KT artifacts
QCM6490_SHA256SUM = "f25c4305dfbd5632c1dd4d40814f559a270ff4c13fdff82eeab66b36ae6d32d0"
QCS9100_SHA256SUM = "31df44e83ef248fa29b20d4352ebe5d6cc098ace01d7c44b77e8e783f0953301"
QCS8300_SHA256SUM = "83f3969b4d474783f5f0fe3e09b10810553705c40c2fc3ddc1fe0ef5ca0e40fb"
QCS615_SHA256SUM  = "c39334e1233a52877131ae2f3165c52d30f5a43e79db99beb17607c187512399"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"
SRC_URI[qcs615.sha256sum]  = "${QCS615_SHA256SUM}"

# Point to the KT tarball/artifact path
SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

do_package_qa[noexec] = "1"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /lib/firmware/* \
"
FILES:${PN}-dev = "/usr/include/*"

INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"
