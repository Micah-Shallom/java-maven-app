#!/usr/bin/env groovy

export IMAGE_NAME=$1
docker-compose -f docker-compose.yaml up --detach 
echo "------------SUCCESS-------------------"