FROM eclipse-temurin:21-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_ZGBH6XV4VHFG9ZKV /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:21-jdk

COPY --from=buildstage /app/target/producto-1.0-SNAPSHOT.jar /app/app.jar

COPY Wallet_ZGBH6XV4VHFG9ZKV /app/wallet

ENV TNS_ADMIN=/app/wallet
EXPOSE 8080

ENTRYPOINT [ "java", "-jar","/app/app.jar" ]