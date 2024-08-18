#!/bin/bash
mkdir ~/ladon
cd .. && mvn clean package && cd -
docker build -t ladon-ce .
docker-compose up
