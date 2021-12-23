# maven build
mvn clean package

# delete containers & images
docker rm -f $(docker container ls -a | grep so-coin | awk '{print $1}')
docker rmi -f $(docker images | grep so-coin | awk '{print $3}')

# build docker image
docker build -t so-coin/eureka-server:1.0 eureka-server/
docker build -t so-coin/neo4j-service:1.0 neo4j-service/
docker build -t so-coin/api-gateway:1.0 api-gateway/
docker build -t so-coin/user-service:1.0 user-service/

# docker-compose up
docker-compose up -d

## docker save
#docker save -o so-coin.tar so-coin/user-service:1.0 so-coin/eureka-server:1.0 so-coin/neo4j-service:1.0 so-coin/api-gateway:1.0
## docker load
#docker load -i so-coin.tar
