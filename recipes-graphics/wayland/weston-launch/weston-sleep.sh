#!/bin/bash
# Copyright (c) Qualcomm Technologies, Inc. and/or its subsidiaries.
# SPDX-License-Identifier: BSD-3-Clause-Clear

case $1/$2 in
    pre/*)
        echo "Stopping Weston before suspend..." >> /var/log/weston-sleep.log
        systemctl stop init_display
        ;;
    post/*)
        echo "Starting Weston after resume..." >> /var/log/weston-sleep.log
        systemctl start init_display
        ;;
esac
