def project="${JOB_NAME}".split('/')[0]
pipeline {
  agent any
  environment {
    PROJECT_NAME= "${project}"
  }
  stages {
    stage("Build, Test and Quality Gate Analysis") {
      steps {
          bat 'set JAVA_HOME="c:\\Eee\\jdk-11"'
          bat 'mvn clean verify'
        
      }
    }
   
  }
  
}
