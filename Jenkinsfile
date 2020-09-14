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
                script {
                    pom = readMavenPom file: "pom.xml";
                    if(pom.packaging == 'pom'){
	                    	echo "***** ${pom.modules} And ${pom.modules.size()}"
	                    	pom.modules.each { moduleName ->
	                    		echo "***** Processing module ${moduleName}"
	                    		modulePom = readMavenPom file: "${moduleName}/pom.xml";
	                    		
	                    		filesByGlob = findFiles(glob: "${moduleName}/target/*.${modulePom.packaging}");
			                    echo "***** ${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
			                    artifactPath = filesByGlob[0].path;
			                    artifactExists = fileExists artifactPath;
			                    NEXUS_REPOSITORY = modulePom.version.endsWith('SNAPSHOT') ? NEXUS_SNAPSHOTS_REPOSITORY : NEXUS_RELEASES_REPOSITORY
			                    if(artifactExists) {
			                        echo "***** File: ${artifactPath}, group: ${modulePom.groupId}, packaging: ${modulePom.packaging}, version ${modulePom.version}";
			                        nexusArtifactUploader(
			                            nexusVersion: NEXUS_VERSION,
			                            protocol: NEXUS_PROTOCOL,
			                            nexusUrl: NEXUS_URL,
			                            groupId: modulePom.groupId,
			                            version: modulePom.version,
			                            repository: NEXUS_REPOSITORY,
			                            credentialsId: NEXUS_CREDENTIAL_ID,
			                            artifacts: [
			                                [artifactId: modulePom.artifactId,
			                                classifier: '',
			                                file: artifactPath,
			                                type: modulePom.packaging],
			                                [artifactId: modulePom.artifactId,
			                                classifier: '',
			                                file: "pom.xml",
			                                type: "pom"]
			                            ]
			                        );
			                    } else {
			                        error "***** File: ${artifactPath}, could not be found";
			                    }
                    	}
                    	nexusArtifactUploader(
					    nexusVersion: NEXUS_VERSION,
					    protocol: NEXUS_PROTOCOL,
					    nexusUrl: NEXUS_URL,
					    groupId: pom.groupId,
					    version: pom.version,
					    repository: NEXUS_REPOSITORY,
					    credentialsId: NEXUS_CREDENTIAL_ID,
					    artifacts: [
						[artifactId: pom.artifactId,
						classifier: '',
						file: "pom.xml",
						type: "pom"]
					    ]
					);
                    }
                    else {
	                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
	                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
	                    artifactPath = filesByGlob[0].path;
	                    artifactExists = fileExists artifactPath;
	                    NEXUS_REPOSITORY = pom.version.endsWith('SNAPSHOT') ? NEXUS_SNAPSHOTS_REPOSITORY : NEXUS_RELEASES_REPOSITORY
	                    if(artifactExists) {
	                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
	                        nexusArtifactUploader(
	                            nexusVersion: NEXUS_VERSION,
	                            protocol: NEXUS_PROTOCOL,
	                            nexusUrl: NEXUS_URL,
	                            groupId: pom.groupId,
	                            version: pom.version,
	                            repository: NEXUS_REPOSITORY,
	                            credentialsId: NEXUS_CREDENTIAL_ID,
	                            artifacts: [
	                                [artifactId: pom.artifactId,
	                                classifier: '',
	                                file: artifactPath,
	                                type: pom.packaging],
	                                [artifactId: pom.artifactId,
	                                classifier: '',
	                                file: "pom.xml",
	                                type: "pom"]
	                            ]
	                        );
	                    } else {
	                        error "*** File: ${artifactPath}, could not be found";
	                    }
                    }
                }
            }
        }
  }
}
