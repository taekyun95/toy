#!/bin/bash

./gradlew build --parallel && \
docker build -t taekyun95/toy-ecommerce . && \
docker push taekyun95/toy-ecommerce:latest
