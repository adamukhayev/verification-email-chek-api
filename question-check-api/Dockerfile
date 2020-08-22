FROM amazoncorretto:11.0.7

WORKDIR /app

COPY build/libs/question-check-api-0.0.1.jar /app/question-check-api.jar

EXPOSE 8080

ENTRYPOINT ["java"]

CMD [ "-XX:+PrintFlagsFinal", "-XX:+UnlockExperimentalVMOptions", \
      "-XX:+UseContainerSupport", "-XshowSettings:vm", \
      "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/question-check-api.jar" ]
