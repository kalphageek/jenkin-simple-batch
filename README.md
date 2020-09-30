# Jenkins 등록 정보
### profile:postgres
* VM Options : -Dspring.profiles.active=postgres
* Program Arguments : --job.name=task1Job requestDate=20200801
### Build 매개변수
```
매개변수명 : appName
Default Value : jenkins-simple-batch
``` 
### 빌드유발
* Build Periodical (2시간 마다 실행)
```
* H/2 * * *
```  
### Pipeline
```groovy
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'git@github.com:kalphageek/jenkins-simple-batch.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage('Delivery') {
            steps {
                sh '''
                    cd target
                    JAR_NAME=$(ls *.jar | tail -n 1)
                    echo ".jar name : $JAR_NAME"
                    ssh jjd@api1.deogi mkdir -p workspace/${appName}/target
                    scp ../service-start.sh jjd@api1.deogi:workspace/${appName}/
                    ssh jjd@api1.deogi chmod 755 workspace/${appName}/service-start.sh
                    scp $JAR_NAME jjd@api1.deogi:workspace/${appName}/target/
                    ssh jjd@api1.deogi "cd workspace/${appName}/target; ln -sf $JAR_NAME application.jar"
                '''
            }
        }
    }
}
```