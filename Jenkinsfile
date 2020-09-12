pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('Source') {
      steps {
        git(url: 'git@github.com:kalphageek/jenkins-simple-batch.git', branch: 'master', credentialsId: 'jenkins-private-key')
      }
    }

    stage('Build') {
      steps {
        tool 'maven'
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "Deploy succeed"'
      }
    }

  }
}