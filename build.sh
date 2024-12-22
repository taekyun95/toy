#!/bin/bash

./gradlew build --parallel && \
docker build -t taekyun95/toy-web . && \
docker push taekyun95/toy-web:latest
