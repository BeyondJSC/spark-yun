FROM openjdk:8

VOLUME /etc/zhiqingyun/conf
VOLUME /var/lib/zhiqingyun

ARG ADMIN_PASSWORD='admin123'
ARG ACTIVE_ENV='default'
ARG LOG_LEVEL='info'

COPY ./spark-yun-backend/spark-yun-main/build/libs/zhiqingyun.jar /opt/zhiqingyun/zhiqingyun.jar
COPY ./spark-yun-backend/spark-yun-main/src/main/resources/application-default.yml /etc/zhiqingyun/conf/

EXPOSE 8080

ENV ADMIN_PASSWORD=${ADMIN_PASSWORD}
ENV ACTIVE_ENV=${ACTIVE_ENV}
ARG LOG_LEVEL=${LOG_LEVEL}

CMD java -jar /opt/zhiqingyun/zhiqingyun.jar --logging.level.root=${LOG_LEVEL} --spring.profiles.active=${ACTIVE_ENV} --spark-yun.admin-passwd=${ADMIN_PASSWORD} --spring.config.additional-location=/etc/zhiqingyun/conf/