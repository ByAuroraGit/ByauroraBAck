# Use a imagem do OpenJDK 11 como base
FROM adoptopenjdk:11-jre-hotspot

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR do Quarkus para o diretório de trabalho no contêiner
COPY target/delivery.jar /app/

# Expõe a porta 8080 (ou a porta que seu aplicativo Quarkus está configurado para usar)
EXPOSE 8080

# Comando de inicialização do aplicativo Quarkus
CMD ["java", "-jar", "delivery.jar"]
