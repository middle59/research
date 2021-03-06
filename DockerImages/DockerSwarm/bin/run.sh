#!/bin/bash

#Note - This requires the virtualbox driver in order to create each machine.
sid=$(docker run swarm create)
echo "Creating Docker Swarm Master..."
docker-machine create -d virtualbox --swarm --swarm-master --swarm-discovery=token://$sid swarm-master

echo "Creating 5 Docker Nodes in our swarm..."
for i in $(seq 1 5); do docker-machine create -d virtualbox --swarm --swarm-discovery=token://$sid  swarm-0$i; done

#Set the environment to swarm-master
eval "$(docker-machine env --swarm swarm-master)"

docker-compose up -d

docker-compose scale worker=5

