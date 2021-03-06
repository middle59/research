#!/bin/bash
export curUser=$(whoami)
if [ X$1 = "X" ]; then echo "No container name argument."; exit; fi
if [ $curUser = "root" ]; then
	docker run -d -it --name $1 middle59/hads-docker-minimal /bin/bash
	docker exec -d -t rmi1 rmiregistry
	docker cp lib/RMI-1 $1:/root/
else
	echo "Not logged in as root. Try sudo -s"
fi
