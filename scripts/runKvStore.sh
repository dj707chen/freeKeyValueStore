#!/usr/bin/env zsh

# ScriptHeader
set -e
SHELL_DIR=$(dirname "$0")
SHELL_FULL_PATH=$(cd "${SHELL_DIR}" || exit 99; pwd)/$(basename "$0")
echo "[$(date)]"
echo "Entering: ${SHELL_FULL_PATH}"
echo "Work dir: $(pwd)"
EXIT_MSG=""
function exit_action {
  echo -n
}
function exit_shell {
  EXIT_CODE=$?
  if [[ -n ${EXIT_MSG} ]]; then
    END_MSG=", ${EXIT_MSG}"
  fi
  exit_action
  echo "[$(date)]"
  echo "Work dir: $(pwd)"
  echo "Exiting : ${SHELL_FULL_PATH}"
  echo "ExitCode: ${EXIT_CODE}${END_MSG}"
  echo
}
trap exit_shell EXIT

sbt "runMain kvStore.Main"
