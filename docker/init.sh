#!/bin/bash
INIT_OVERRIDE_FILE=/tmp/init.sh

if [ -f "${INIT_OVERRIDE_FILE}" ] && [[ "${INIT_OVERRIDE_FILE}" != "${BASH_SOURCE}" ]]; then
	echo "Init overriding file found at ${INIT_OVERRIDE_FILE}!"
	source "${INIT_OVERRIDE_FILE}" $*
else
	if [[ -n "$1" ]]; then
			CMD="$@"
	else
			CMD="cd application && ./gradlew bootRun"
	fi

	eval $CMD
fi