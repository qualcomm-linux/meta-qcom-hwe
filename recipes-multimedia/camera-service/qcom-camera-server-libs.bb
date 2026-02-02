require camera-service.inc

DEPENDS += "qcom-camera-common"

DEPENDS:append:qcm6490 = " camx-kt"
DEPENDS:append:qcs9100 = " camx"
DEPENDS:append:qcs8300 = " camx"

EXTRA_OECMAKE += "-DBUILD_CATEGORY=TARGET"

EXTRA_OECMAKE:append:qcm6490 = " -DTARGET_BOARD_PLATFORM=qcm6490 "
EXTRA_OECMAKE:append:qcs9100 = " -DTARGET_BOARD_PLATFORM=qcs9100 "
EXTRA_OECMAKE:append:qcs8300 = " -DTARGET_BOARD_PLATFORM=qcs8300 "
