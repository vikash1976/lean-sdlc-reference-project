def project="${JOB_NAME}".split('/')[0]
pipeline {
  agent any
  environment {
    PROJECT_NAME= "${project}"
    //NEXUS_VERSION = "nexus3"
    //NEXUS_PROTOCOL = "http"
    //NEXUS_URL = "localhost:8081"
    NEXUS_RELEASES_REPOSITORY = "maven-releases"
    NEXUS_SNAPSHOTS_REPOSITORY = "maven-snapshots"
    //NEXUS_CREDENTIAL_ID = "nexus_cred"
  }
  stages {
    stage("Build, Test and Quality Gate Analysis") {
      steps {
          bat 'set'
          bat 'mvn clean verify -Dmaven.test.skip=true -Djacoco.skip=true -Dpmd.skip=true -Dcpd.skip=true -Dspotbugs.skip=true -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\\Eee\\jdk-11\\bin\\javac"'
        
      }
    }
   
  
    stage("Publish to Nexus Repository Manager") {
            steps {
               bat 'mvn deploy -Dmaven.test.skip=true -Djacoco.skip=true -Dpmd.skip=true -Dcpd.skip=true -Dspotbugs.skip=true -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\\Eee\\jdk-11\\bin\\javac"'
            }
        }
  }
}
