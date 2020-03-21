VERSION=0.1
PROJECT=asinatra
APP=instant

build:
	cp ../target/${APP}-${VERSION}.jar .
	docker build  -t ${PROJECT}/${APP}:${VERSION} -t ${PROJECT}/${APP}:latest .
	docker build -t ${PROJECT}/rabbitmq rabbitmq
	rm ${APP}-${VERSION}.jar
build-docker:
	docker-compose -f docker-compose-dev.yaml build 	
push: 
	docker push ${PROJECT}/${APP}:${VERSION}
	docker push ${PROJECT}/${APP}:latest
	docker push asinatra/rabbitmq:latest	
run:
	docker network create instant || true
	docker run -d --name docker-rabbitmq  -p 15672:15672 -p 5672:5672 -p 1883:1883 --net=instant  asinatra/rabbitmq
	docker exec docker-rabbitmq /docker-rabbitmq/init.sh
	docker-compose -f docker-compose.yaml  up -d
run-dev:
	docker network create instant || true
	docker run -d --name docker-rabbitmq  -p 15672:15672 -p 5672:5672 -p 1883:1883 --net=instant  asinatra/rabbitmq
	docker exec docker-rabbitmq /docker-rabbitmq/init.sh
	docker-compose -f docker-compose-dev.yaml  up -d		
stop:
	docker-compose stop 
	docker stop docker-rabbitmq	 || true
	docker rm docker-rabbitmq  || true
