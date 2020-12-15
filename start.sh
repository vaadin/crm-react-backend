#!/bin/sh

# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar app.jar
