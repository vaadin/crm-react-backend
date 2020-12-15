# -- Part 1: Create a base Java 11 image --
# https://stackoverflow.com/a/57145029
FROM alpine:latest as packager

RUN apk --no-cache add openjdk11-jdk openjdk11-jmods

ENV JAVA_MINIMAL="/opt/java-minimal"

# build minimal JRE
RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --output "$JAVA_MINIMAL"

FROM alpine:latest

ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"

# -- Part 2: Vaadin CRM specific stuff
COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"
COPY target/*.jar app.jar
COPY start.sh start.sh
RUN chmod +x start.sh
CMD ["./start.sh"]
