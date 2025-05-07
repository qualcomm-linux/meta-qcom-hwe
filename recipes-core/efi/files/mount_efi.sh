#!/bin/sh

# If boot.mount fails to mount efi,then  mount /dev/mmcblk[0-9]p1 to /boot
SD_DEVICE=$(lsblk -l -o NAME | grep -E '^mmcblk[0-9]p1' | head -n 1)
if [[ -n "$SD_DEVICE" ]]; then
    mount /dev/$SD_DEVICE /boot
    echo "Mounted /dev/$SD_DEVICE to /boot"
else
    echo "No SD card detected!"
fi
