# Lean SDLC Reference SpringBoot Project

A sample reference project that aims to meet Lean SDLC code quality standards.  
If you are writing a completely new application in J2EE space you may like to use this project as sample.
These are recommendations as per Lean SDLC CI Quality Gate and **not a hard requirement** for projects that are on their modernization journey.

In case you prefer to have a different set of measures and threshold values, we recommend you  define a suitable qualify gate for your needs in SonarQube.

This project uses following plugins and tools - 
-	It uses [errorprone](https://github.com/google/error-prone/) to catch common Java programming mistake during compile time.
-	It uses PMD Check and CPD check to catch static code analysis issue during **validate** phase even before compiling the code. It breaks the pipeline at very early stage if its not meeting the PMD default static code expectation. To verify this uncomment line# 19 of Palindrom.java.
```java
	//return (firstChar == lastChar) && isPalindrome(center);
    	return firstChar == lastChar && isPalindrome(center);
```
```
 >>> maven-pmd-plugin:3.13.0:check (default) > :pmd @ leansdlc-reference-project >>>
[INFO] 
[INFO] --- maven-pmd-plugin:3.13.0:pmd (pmd) @ leansdlc-reference-project ---
[INFO] 
[INFO] <<< maven-pmd-plugin:3.13.0:check (default) < :pmd @ leansdlc-reference-project <<<
[INFO] 
[INFO] 
[INFO] --- maven-pmd-plugin:3.13.0:check (default) @ leansdlc-reference-project ---
[INFO] PMD version: 6.21.0
[INFO] 
[INFO] 
[INFO] >>> maven-pmd-plugin:3.13.0:cpd-check (default) > :cpd @ leansdlc-reference-project >>>
[INFO] 
[INFO] --- maven-pmd-plugin:3.13.0:cpd (cpd) @ leansdlc-reference-project ---
[INFO] 
[INFO] <<< maven-pmd-plugin:3.13.0:cpd-check (default) < :cpd @ leansdlc-reference-project <<<
[INFO] 
[INFO] 
[INFO] --- maven-pmd-plugin:3.13.0:cpd-check (default) @ leansdlc-reference-project ---
[INFO] PMD version: 6.21.0
```
-	[Spotbugs](https://spotbugs.github.io/), **the spiritual successor of findbugs**, to catch bugs and break build. spotbugs check goal runs at **verify** phase. SpotBugs checks for more than 400 bug patterns. Bug descriptions can be found [here](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html)
```
[INFO] >>> spotbugs-maven-plugin:4.0.4:check (default) > :spotbugs @ leansdlc-reference-project >>>
[INFO] 
[INFO] --- spotbugs-maven-plugin:4.0.4:spotbugs (spotbugs) @ leansdlc-reference-project ---
[INFO] Fork Value is true
[INFO] Done SpotBugs Analysis....
[INFO] 
[INFO] <<< spotbugs-maven-plugin:4.0.4:check (default) < :spotbugs @ leansdlc-reference-project <<<
[INFO] 
[INFO] 
[INFO] --- spotbugs-maven-plugin:4.0.4:check (default) @ leansdlc-reference-project ---
[INFO] BugInstance size is 0
[INFO] Error size is 0
[INFO] No errors/warnings found
```

To see **Spotbugs** in action, uncomment below function in ShortSet.java:
```java
public Boolean returnBool() {
		return null;
	}
```
and the test function in ShortSetTest.java:
```java
@Test
	void returnBool() {
		Boolean expectedValue = null;
		Set<Short> s = new HashSet<>();
		Boolean actualValue = new ShortSet(s).returnBool();

		assertEquals(expectedValue, actualValue);
	}
```
You will fnd **Spotbugs** reporting that the code is in violation as shown below:
```
[INFO] >>> spotbugs-maven-plugin:4.0.4:check (default) > :spotbugs @ leansdlc-reference-project >>>
[INFO] 
[INFO] --- spotbugs-maven-plugin:4.0.4:spotbugs (spotbugs) @ leansdlc-reference-project ---
[INFO] Fork Value is true
[INFO] Done SpotBugs Analysis....
[INFO] 
[INFO] <<< spotbugs-maven-plugin:4.0.4:check (default) < :spotbugs @ leansdlc-reference-project <<<
[INFO] 
[INFO] 
[INFO] --- spotbugs-maven-plugin:4.0.4:check (default) @ leansdlc-reference-project ---
[INFO] BugInstance size is 1
[INFO] Error size is 0
[INFO] Total bugs: 1
[ERROR] Medium: id.test.springboottesting.model.ShortSet.returnBool() has Boolean return type and returns explicit null [id.test.springboottesting.model.ShortSet] At ShortSet.java:[line 30] NP_BOOLEAN_RETURN_NULL
[INFO] 
```
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

-	[pit-test](https://pitest.org/quickstart/maven/) plugin to enable **mutation testing** as part of the CI pipeline.
If you like to break the build before for mutation coverage missing mutoationThreshold, you may like to append **-DmutationThreshold=100** to the *`mvn clean verify sonar:sonar -PintegrationTest`* as *`mvn clean verify sonar:sonar -PintegrationTest -DmutationThreshold=100`*.
-	integration testing is enabled as part of a profile called **'integrationTest'**. You can turn it ON/OFF per your need.

Post successfull compilation the coverage metrics is sent to SonarQube for validation of metrics against quality gate (LEAN SDLC) metrics.

The pipeline is designed to break at any build stage faulire.  
The build logs are streamed into **Splunk**, so that one has an oppotunity to go back and audit any particular build of interest.

**The Quality Gate measures and the threshold values are shown below** ![Quality Gate](/src/main/resources/Lean_SDLC_QG.JPG)  
Quality Gate as JSON:
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


@vikash1976@gmail.com
