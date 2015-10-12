#!/bin/bash
echo "Cleaning containers..."
sleep 1s
docker rm test
echo "Building image..."
sleep 1s
docker build -t="middle59/testubuntu" .
echo "Creating container..."
sleep 1s
docker run -d --name test middle59/testubuntu
echo "Monitoring logs.."
sleep 1s
docker logs -f test
