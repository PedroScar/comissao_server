
FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM amazoncorretto:17.0.12 AS runtime
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/decorapi.jar
ENTRYPOINT ["java","-jar","/app/decorapi.jar"]