#!/bin/sh

# Check if running as root and run sysctl -p directly
if [ "$(id -u)" = '0' ]; then
    sudo sysctl -w vm.overcommit_memory=1
else
    echo "Permission denied: Cannot set vm.overcommit_memory"
    exit 1
fi

# Execute the original command
exec "$@"