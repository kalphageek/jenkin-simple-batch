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
## Execute Shell
> "Job Name"과 "년월일시분" 2개 파라미터 전달
```bash
#./mvnw clean package
pwd
cd target
JAR_NAME=$(ls -r |grep '.jar' | tail -n 1)
echo "jar name : $JAR_NAME"
TODAY=$(date +%Y%m%d%H%M)
echo "today : $TODAY"
echo $WORKSPACE
yes|cp -rf $JAR_NAME ../../$JAR_NAME
java -jar -Dspring.active.profiles=postgres $JAR_NAME --job.name=task1Job requestDate=$TODAY
```  