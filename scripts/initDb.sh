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

#SHELL_DIR=/Users/dj.chen/repoMy/freeKeyValueStore/scripts
PG_CONTAINER=test-db-pg
PG_DB_NAME=test_db
PG_USER=tester
docker cp "$SHELL_DIR/initDb.sql" $PG_CONTAINER:/initDb.sql
docker exec -d $PG_CONTAINER psql --username=$PG_USER --dbname=$PG_DB_NAME --file=/initDb.sql

#docker exec -it $PG_CONTAINER /bin/bash
#docker exec -it $PG_CONTAINER psql --username=$PG_USER $PG_DB_NAME

#psql --host=localhost --port 5432 --username=tester test_db -f initDb.sql