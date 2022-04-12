#!/usr/bin/env zsh

psql --host=localhost --port 5432 --username=tester test_db -f initDb.sql