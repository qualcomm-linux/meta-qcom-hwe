# Conditionally remove selinux-related dependencies for qcom
DEPENDS:remove:qcom = "${@bb.utils.contains('PACKAGECONFIG', 'selinux', 'clang-native', '', d)}"
