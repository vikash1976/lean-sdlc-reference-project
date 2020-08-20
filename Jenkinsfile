def project="${JOB_NAME}".split('/')[0]
pipeline {
  agent any
  environment {
    PROJECT_NAME= "${project}"
  }
  stages {
    stage("Build, Test and Quality Gate Analysis") {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'mvn clean verify sonar:sonar -PintegrationTest'
        }
      }
    }
    stage ("SonarQube Quality Gate Check") { 
      steps { 
        script{
          timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
            def qualitygate = waitForQualityGate() 
              if (qualitygate.status != "OK") { 
                error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}" 
              }
          } 
        }
      }
    }
  }
  post {
    success {
      script {
        jacoco()
      }
    }
  }
}
