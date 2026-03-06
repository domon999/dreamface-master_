# 构建阶段：使用 Maven + Java 8
FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 运行阶段：使用 Java 8 JRE
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/ruoyi-admin/target/ruoyi-admin-*.jar app.jar

# 时区设置
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]