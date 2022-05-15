FROM openjdk:11
ARG buildPath
ENV PORT=8080
RUN ls -a
COPY $buildPath app.jar
EXPOSE 8080
RUN ls -a
ENTRYPOINT ["java", "-jar", "app.jar"]