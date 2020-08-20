# Lean SDLC Reference SpringBoot Project

A sample reference project inline with lean sdlc quality demands like
-	100% code coverage
-	100 muttaion coverage
-	Build should break if-
	-	any common programming mistakes are caught at compile-time. It uses [errorprone](https://github.com/google/error-prone/) for this.
	-	Any stage failure breaks the build pipeline	
-	pit-test maven plugin to achieve mutation testing
-	uses jacoco plugin to for coverage metrics. Build breaks if jacoco line and branch limits don't meet at build time.
-	sends code metrics to SonarQube for evaluation, if numbers don't match build breaks.
-	builds a runnable jar