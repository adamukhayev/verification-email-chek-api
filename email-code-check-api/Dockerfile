FROM amazoncorretto:11.0.7

WORKDIR /app

COPY build/libs/email-code-check-api-0.0.1.jar /app/email-code-check-api.jar

EXPOSE 8080

ENTRYPOINT ["java"]

CMD [ "-XX:+PrintFlagsFinal", "-XX:+UnlockExperimentalVMOptions", \
      "-XX:+UseContainerSupport", "-XshowSettings:vm", \
      "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/email-code-check-api.jar" ]
