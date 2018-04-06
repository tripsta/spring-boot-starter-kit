#!/bin/bash

CURRENT_PATH=`pwd`

if [[ "${FROM_MODULE}" == "" ]]; then
  echo -e 'Usage:\nFROM_MODULE="sbsk" TO_MODULE="tripsta.talos" APPLICATION_NAME="talos" scripts/rename_modules.sh'
  exit 1
fi

if [[ "${TO_MODULE}" == "" ]]; then
  echo "TO_MODULE env variable is needed"
  exit 1
fi

if [[ "${APPLICATION_NAME}" == "" ]]; then
  echo "APPLICATION_NAME env variable is needed"
  exit 1
fi

echo -e "### CURRENT_PATH: ${CURRENT_PATH}"

echo -e "\n\n### Replacing content in files from '${FROM_MODULE}' to '${TO_MODULE}' in '${CURRENT_PATH}'"
egrep -lir "(${FROM_MODULE})" ${CURRENT_PATH} |\
  grep '.java\|.xml' |\
  grep -v ".git" |\
  grep -v '/build/' |\
  while read TARGET; do
    echo "sed -i'.bak' -e 's/${FROM_MODULE}/${TO_MODULE}/g' $TARGET"
  done

echo -e "\n\n### Replacing content in files from '${FROM_MODULE}' to '${TO_MODULE}' in '${CURRENT_PATH}'"
egrep -lir "(${FROM_MODULE})" ${CURRENT_PATH} |\
  grep '.gradle' |\
  grep -v ".git" |\
  grep -v '/build/' |\
  while read TARGET; do
    echo "sed -i'.bak' -e 's/${FROM_MODULE}/${APPLICATION_NAME}/g' $TARGET"
  done

echo -e "\n\n### Removing .bak filers generqted from sed"
echo -e "find . -name '*.bak' -type f -delete"

FROM_PATH="${FROM_MODULE/./\/}"
TO_PATH="${APPLICATION_NAME/./\/}"
echo -e "\n\n### Renaming files from '${FROM_PATH}' to '${TO_PATH}' in '${CURRENT_PATH}'"
find ${CURRENT_PATH} -type d -print |\
  grep "${FROM_PATH}$" |\
  grep -v ".git" |\
  grep -v "build.gradle" |\
  while read FROM; do
    TO="${FROM/${FROM_PATH}/${TO_PATH}}"
    echo -e "mkdir -p `dirname ${TO}` && \ "
    echo -e "cp -R ${FROM} ${TO} && \ "
    echo -e "rm -rf ${FROM}\n"
  done

echo -e "\n\n### Renaming root folder to  ${APPLICATION_NAME}"
echo -e "mv ${CURRENT_PATH}/application ${CURRENT_PATH}/${APPLICATION_NAME}\n"

echo -e "\n\n *** CHECK AND RUN THE COMMANDS ABOVE AT YOUR OWN RISK"

echo -e "\n\n ### Remember to change manually the basename in 'build.gradle', if needed.\nPath: ${CURRENT_PATH}/${APPLICATION_NAME}/build.gradle\nCurrent value:"
cat "${CURRENT_PATH}/${APPLICATION_NAME}/build.gradle" | grep "baseName"

echo -e "\n\n ### Also, remember to change manually the display-name in 'application.yml', if needed.\nPath: ${CURRENT_PATH}/${APPLICATION_NAME}/web/src/main/resources/config/application.yml\nCurrent value:"
cat "${CURRENT_PATH}/${APPLICATION_NAME}/web/src/main/resources/config/application.yml" | grep "display-name"
