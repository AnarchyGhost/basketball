#!/usr/bin/env bash

echo 'Copy files...'

for f in modules/*/build/libs/*-boot.jar; do
  scp -i ~/.ssh/id_rsa \
    "$f" \
    anarchyghost@185.253.217.230:/home/anarchyghost/basketball/
done

ssh -i ~/.ssh/id_rsa anarchyghost@185.253.217.230 << EOF

pgrep java | xargs kill -9 &
touch eureka.txt
nohup java -jar basketball/ru.anarchyghost.basketball.modules-eureka-1.0-SNAPSHOT-boot.jar > eureka.txt &
touch config.txt
nohup java -jar basketball/ru.anarchyghost.basketball.modules-config-1.0-SNAPSHOT-boot.jar  > config.txt &
nohup java -jar basketball/*-boot.jar &
EOF
