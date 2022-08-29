#!/usr/bin/env groovy

export IMAGE_NAME=$1
docker-compose docker-compose.yaml up --detach 
echo "------------SUCCESS-------------------"