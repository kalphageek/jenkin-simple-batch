# jenkins-simple-batch
## 참고
https://jojoldu.tistory.com/328?category=902551

## profile:local
* VM Options : -Dspring.profiles.active=local
* Program Arguments : --job.name=decider version=1

#profile:postgres
* VM Options : -Dspring.profiles.active=postgres
* Program Arguments : --job.name=task1Job requestDate=20200801

## 빌드유발
* Build Periodical (30분 마다 실행)
H/30 * * * *  
## Build
```bash
echo ">>project build start!"
mvn clean package

echo ">> pwd"
pwd

JAR_NAME=$(ls target/*.jar | tail -n 1)
echo "jar name : $JAR_NAME"

TODAY=$(date +%Y%m%d)
echo "today : $TODAY"
```
## 빌드 후 조치
```bash
$vi jenkins-simple-batch.sh
#!/bin/bash
#-------
REGEX='\w+.sh'
[[ $0 =~ $REGEX ]]
SH=${BASH_REMATCH[1]}

JAR_NAME=$(ls -tr ${SH//.sh/}*.jar | tail -n 1)
echo "jar : $JAR_NAME"

TODAY=$(date +%Y%m%d)
echo "today : $TODAY"
#-------

java -jar $JAR_NAME -Dspring.profiles.active=prostgres --job.name=task1Job requestDate=$TODAY
```