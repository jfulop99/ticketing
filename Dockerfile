#FROM adoptopenjdk:16-jre-hotspot
#RUN mkdir /opt/app
#COPY target/ticketing-0.0.1-SNAPSHOT.jar /opt/app/ticketing.jar
#CMD ["java", "-jar", "/opt/app/ticketing.jar"]

FROM adoptopenjdk:16-jre-hotspot as builder
WORKDIR application
COPY target/ticketing-0.0.1-SNAPSHOT.jar ticketing.jar
RUN java -Djarmode=layertools -jar ticketing.jar extract

FROM adoptopenjdk:16-jre-hotspot
WORKDIR application
RUN apt update \
    && apt-get install wget \
    && apt-get install -y netcat \
    && wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x ./wait-for-it.sh
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]