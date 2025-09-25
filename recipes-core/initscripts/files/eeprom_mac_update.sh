#!/bin/sh
# Copyright (c) Qualcomm Technologies, Inc. and/or its subsidiaries.
# SPDX-License-Identifier: BSD-3-Clause-Clear

EEPROM_PATH="/sys/bus/i2c/devices/18-0050/eeprom"
RAW_HEX=$(hexdump -v -n 6 -e '6/1 "%02X "' "$EEPROM_PATH")

# Convert RAW_HEX string to array using set and IFS
IFS=' ' set -- $RAW_HEX
HEX_0=$1
HEX_1=$2
HEX_2=$3
HEX_3=$4
HEX_4=$5
HEX_5=$6

if [ "$HEX_2" = "3A" ] && [ "$HEX_5" = "3A" ]; then
    mac=$(head -c 17 "$EEPROM_PATH")
    # Replace colons with \x and prepend \x to the first byte
    converted_mac=$(echo "$mac" | sed 's/:/\\x/g' | sed 's/^/\\x/')
    echo -ne "$converted_mac" > "$EEPROM_PATH"

    echo "<2> Updating the MAC address format in EEPROM, dwmac_qcom_ethqos driver will be reloaded." > /dev/kmsg
    # Reload the Ethernet driver module
    rmmod dwmac_qcom_ethqos
    modprobe dwmac_qcom_ethqos
fi
