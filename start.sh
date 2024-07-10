#!/bin/bash

docker-compose down
docker pull taekyun95/toy-ecommerce:latest
docker-compose -f docker-compose.yml up