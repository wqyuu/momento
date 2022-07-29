FROM openjdk:8-alpine
# 该镜像的维护者
MAINTAINER wqy
ENV LANG en_US.UTF-8
VOLUME ["/tmp"]
ADD ./target/momento-0.0.1-SNAPSHOT.jar /opt/app.jar
WORKDIR /opt/
# 端口需要修改
EXPOSE 8080
# 执行环境
ENTRYPOINT ["java","-jar","app.jar"]