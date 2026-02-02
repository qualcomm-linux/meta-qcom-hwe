require camera-service.inc

DEPENDS += "camera-metadata gtest protobuf-native protobuf-c protobuf-c-native"

EXTRA_OECMAKE += "-DBUILD_CATEGORY=COMMON"
