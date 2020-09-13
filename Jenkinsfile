def project="${JOB_NAME}".split('/')[0]
pipeline {
  agent any
  environment {
    PROJECT_NAME= "${project}"
  }
  stages {
    stage("Build, Test and Quality Gate Analysis") {
      steps {
          bat 'mvn clean verify -Dmaven.test.skip=true -Djacoco.skip=true -Dpmd.skip=true -Dcpd.skip=true -Dspotbugs.skip=true -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\\Eee\\jdk-11\\bin\\javac"'  
      }
    }
   
  
    stage("Publish to Nexus Repository - mvn") {
            steps {
               bat 'mvn deploy -Dmaven.test.skip=true -Djacoco.skip=true -Dpmd.skip=true -Dcpd.skip=true -Dspotbugs.skip=true -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\\Eee\\jdk-11\\bin\\javac"'
            }
        }
  }
}
