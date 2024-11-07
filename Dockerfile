# Etapa 1: Build da aplicação
# Usamos uma imagem Maven para compilar o projeto
FROM maven:3.8.5-openjdk-17 AS build

# Definimos o diretório de trabalho dentro do container
WORKDIR /app

# Copiamos o arquivo pom.xml e baixamos as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos o código-fonte da aplicação
COPY src ./src

# Compilamos o projeto e geramos o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para execução
# Usamos uma imagem base Linux da AWS com Java 17
FROM amazoncorretto:17-alpine

# Definimos variáveis de ambiente para segurança
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Duser.timezone=UTC"
RUN apk update && apk add curl
# Definimos o diretório de trabalho
WORKDIR /app

# Copiamos o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Definimos o usuário não root para segurança
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expondo a porta da aplicação (ajuste conforme necessário)
EXPOSE 9090

# Definimos o ponto de entrada para o container
ENTRYPOINT ["java", "-Dspring.profiles.active=aws", "-jar", "app.jar"]

# Melhor prática: Definir um healthcheck para monitorar a saúde do container
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
  CMD curl --fail http://localhost:9090/actuator/health || exit 1