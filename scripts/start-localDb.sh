#!/bin/bash

#docker container ls | grep test-db-pg > /dev/null && (docker stop test-db-pg; docker rm test-db-pg)
#docker stop test-db-pg
#docker rm test-db-pg

docker run -d \
  --publish 5432:5432 \
  --env POSTGRES_DB=test_db \
  --env POSTGRES_USER=tester \
  --env POSTGRES_PASSWORD=password \
  --env POSTGRES_ROLE=tester \
  --name test-db-pg postgres:10

# christopherdavenport/postgres-db-with-role:latest exits right started,
# changing to postgres:10, on which christopherdavenport/postgres-db-with-role:latest is based.

# For --publish <host port>:<container port>, refer
#   https://docs.docker.com/language/java/run-containers/#:~:text=To%20publish%20a%20port%20for%20our%20container%2C%20we%E2%80%99ll,we%20would%20pass%208080%3A8000%20to%20the%20--publish%20flag.
# <container port> must be 5432
