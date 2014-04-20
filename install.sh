#!/bin/sh

mvn install:install-file \
	-Dfile=target/lorem-1.1-SNAPSHOT.jar \
	-DgroupId=com.thedeanda \
	-DartifactId=lorem \
	-Dversion=1.1-SNAPSHOT \
	-Dpackaging=jar


