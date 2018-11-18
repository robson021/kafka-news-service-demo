#!/usr/bin/env bash

docker-compose down
docker rm $(docker ps -a -q)