FROM openjdk:19
ENV TZ "Asia/Ho_Chi_Minh"
RUN rm -rf /var/cache/apk/*
ARG JAR_FILE=noron-api/target/*.jar
COPY ${JAR_FILE} /usr/app/
WORKDIR /usr/app
ENTRYPOINT exec java $JAVA_OPTS -jar $JAVA_ARGS noron-api-0.0.1-SNAPSHOT.jar