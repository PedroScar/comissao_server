
#FROM gradle:latest AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle buildFatJar --no-daemon

#FROM amazoncorretto:20 AS runtime
#EXPOSE 8080:8080
#RUN mkdir /app
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/decorapi.jar
#ENTRYPOINT ["java","-jar","/app/decorapi.jar"]



############################
# Build stage
############################
FROM gradle:8.7-jdk17 AS build

ENV GRADLE_USER_HOME=/home/gradle/.gradle

WORKDIR /home/gradle/src

COPY --chown=gradle:gradle gradle gradle
COPY --chown=gradle:gradle gradlew gradlew
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts ./

# Baixa dependências (cache layer)
RUN gradle dependencies --no-daemon || true

# Agora copia o restante do código
COPY --chown=gradle:gradle . .

# Build do fat jar
RUN gradle buildFatJar --no-daemon


############################
# Runtime stage
############################
FROM amazoncorretto:17-alpine AS runtime

# Usuário não-root (mais seguro)
RUN addgroup -S app && adduser -S app -G app
USER app

WORKDIR /app

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
