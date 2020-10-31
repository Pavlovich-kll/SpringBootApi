#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/sweater-london.pem \
    target/serving-web-content-0.0.1-SNAPSHOT.jar \
    ec2-user@ec2-3-9-132-56.eu-west-2.compute.amazonaws.com:/home/ec2-user/

echo 'Restart server...'

ssh -i ~/.ssh/sweater-london.pem ec2-user@ec2-3-9-132-56.eu-west-2.compute.amazonaws.com << EOF
pgrep java | xargs kill -9
nohup java -jar serving-web-content-0.0.1-SNAPSHOT.jar> log.txt &
EOF

echo 'Bye'