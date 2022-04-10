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

# Restored DB backup as "old"
export OLD_DB_USER="jabberwocky"
export OLD_DB_PASS="password"

# Current prod data("postgres-jabberwocky0-az-centralus.production.banno-internal.com:5432"),
# run the below command then copy from there
#   pgJxConfig --show json
export NEW_DB_USER="jabberwocky"
export NEW_DB_PASS="password"

sbt run
