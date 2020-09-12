def project="${JOB_NAME}".split('/')[0]
pipeline {
  agent any
  environment {
    PROJECT_NAME= "${project}"
  }
  stages {
    stage("Build, Test and Quality Gate Analysis") {
      steps {
          
          bat 'mvn clean verify -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\Eee\jdk-11\bin\javac"'
        
      }
    }
   
  }
  
}
