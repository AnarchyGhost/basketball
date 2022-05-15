#!/usr/bin/env bash
for f in modules/*/build/libs/*-boot.jar; do
  ADDR=$(echo "$f" | cut -d "/" -f 5)
  NAME=$(echo "$ADDR"| cut -d "." -f 4)
  docker build --build-arg buildPath="$f" . -t anarchyghost/"$(echo "$NAME"| cut -d "-" -f 2)"
  docker push anarchyghost/"$(echo "$NAME"| cut -d "-" -f 2)"
done