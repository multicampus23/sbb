FROM openjdk:17
ENV APP_HOME=/user/app/
WORKDIR ${APP_HOME}
COPY build/libs/*.jar sbb.jar
EXPOSE 8585
CMD ["java", "-jar", "sbb.jar"]
