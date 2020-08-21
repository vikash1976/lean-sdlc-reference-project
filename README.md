# Lean SDLC Reference SpringBoot Project

A sample reference project that aims to meet Lean SDLC code quality standards.  
If you are writing a net new application in J2EE space you may like to use this project as sample.
These are recommendations as per [Lean SDLC CI Quality Gate] and **not a hard requirement**.  
In case you prefer to have a different set of measures and threshold values, we recoomend you  define a suitable qualify gate for your needs.

The Quality Gate measures and the threshold values are shown below:
``` json
{
	"name": "Lean SDLC",
	"conditions": [
		{
			"id": 18,
			"metric": "coverage",
			"op": "LT",
			"error": "100"
		},
		{
			"id": 19,
			"metric": "dc5_mutationAnalysis_mutations_coverage",
			"op": "LT",
			"error": "100"
		},
		{
			"id": 20,
			"metric": "sqale_rating",
			"op": "GT",
			"error": "1"
		},
		{
			"id": 21,
			"metric": "reliability_rating",
			"op": "GT",
			"error": "1"
		},
		{
			"id": 22,
			"metric": "code_smells",
			"op": "GT",
			"error": "0"
		},
		{
			"id": 23,
			"metric": "security_rating",
			"op": "GT",
			"error": "1"
		},
		{
			"id": 24,
			"metric": "blocker_violations",
			"op": "GT",
			"error": "0"
		},
		{
			"id": 25,
			"metric": "critical_violations",
			"op": "GT",
			"error": "0"
		},
		{
			"id": 26,
			"metric": "major_violations",
			"op": "GT",
			"error": "0"
		},
		{
			"id": 27,
			"metric": "minor_violations",
			"op": "GT",
			"error": "0"
		}
	]
}
```
This projet uses following plugins and tools - 
-	jacoco to collect and publish covergare metrics.
-	[jacoco maven plugin](https://www.eclemma.org/jacoco/trunk/doc/maven.html) is enabled to break the build post unit and integration test (if enabled) if threshold numbers defined in pom.xml is not met, by including below listed execution as part of jacoco-maven plugin configuration.
```xml
<execution>
		<id>default-check</id>
		<goals>
			<goal>check</goal>
		</goals>
		<configuration>
			<destFile>${sonar.jacoco.reportPath}</destFile>
			<append>true</append>
			<rules>
			<rule>
					<element>CLASS</element>
					<excludes>
						<exclude>*Test</exclude>
						<exclude>*IT</exclude>
						<exclude>*SpringBootTestingApplication</exclude>
						
					</excludes>
					<limits>
						<limit>
							<counter>LINE</counter>
							<value>COVEREDRATIO</value>
							<minimum>100%</minimum>
						</limit>
						<limit>
							<counter>BRANCH</counter>
							<value>COVEREDRATIO</value>
							<minimum>100%</minimum>
						</limit>
					</limits>
				</rule>
				
			</rules>
		</configuration>
	</execution>

```
**This is enabled to catch the deviations from expected threshold as early as possible in CI cycle.**
-	It uses [errorprone](https://github.com/google/error-prone/) to catch common Java programming mistake during compile time.
-	[pit-test](https://pitest.org/quickstart/maven/) plugin to enable **mutation testing** as part of the CI pipeline.
-	integration testing is enabled as part of a profile called **'integrationTest'**. You can turn it ON/OFF per your need.

Post successfull compilation the coverage metrics is sent to SonarQube for validation of metrics against quality gate (LEAN SDLC) metrics.

The pipeline is designed to break at any stage faulire.
The build logs are streamed into **Splunk**, so that one has an oppotunity to go back and audit any particular build of interest.
