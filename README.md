# meta-qcom-hwe

The development branch of meta-qcom-hwe has been merged with the
original meta-qcom BSP layer. As a consequence, this branch is no
longer open for development and all development is now moved to
[meta-qcom](https://github.com/qualcomm-linux/meta-qcom).

Here is a summary of the supported branches for meta-qcom-hwe and
meta-qcom.

1. `meta-qcom-hwe` is the primary Open Embedded BSP layer, developed
and maintained by Qualcomm Innovation Center, Inc. for Qualcomm Linux
releases 
1. all branches in `meta-qcom-hwe` (`kirkstone`, `scarthgap`)
are used in QLI 1.x releases 
1. `meta-qcom/master`: primary
development branch, with focus on upstream support and compatibility
with the most recent Yocto Project release.  
1. `meta-qcom` all stable
branch up until `styhead` (included): legacy branches maintained by
Linaro, prior to the migration to Qualcomm-linux.

For additional details about the transition/merge of meta-qcom-hwe and
meta-qcom, please see 
[this issue](https://github.com/qualcomm-linux/meta-qcom/issues/680).
