FROM rabbitmq

RUN rabbitmq-plugins enable --offline rabbitmq_management

ENV RABBITMQ_USER hub-iot
ENV RABBITMQ_PASSWORD hub-iot

RUN mkdir /docker-rabbitmq/
ADD config/init.sh /docker-rabbitmq/

EXPOSE 15671 15672 5672 1883

CMD rabbitmq-server



