#!/bin/bash

#Set at jenkins executor
appName=$1

TODAY=$(date +%Y%m%d%H%M)
ORIGIN_JAR=$(readlink -f workspace/${appName}/target/application.jar)
echo "> TODAY : $TODAY"
echo "> ORIGIN_JAR : $ORIGIN_JAR"

#기존 버전으로 실행중인 프로세스에 영향을 주지 않기위해 applicaton.jar로부터 새로 등록된 jenkins-simple-batch-0.0.1-SNAPSHOT.jar 이름을 찾아와서 그것으로 실행한다.
java -jar $ORIGIN_JAR --job.name=task1Job requestDate=$TODAY