#!/bin/bash
cd $HOME

rm -f src/HADS/*.class src/HADS/*/*.class

for i in austin dallas houston zeus.stockton.edu 64.28.143.30; do
   echo $i
   ssh $i 'killall runServer'
   ssh $i 'killall java'
   ssh $i 'killall rmiregistry'
   sleep 3
   echo $i
   ssh $i 'rm -fr $HOME/HADS; ls -al $HOME; ls -al $HOME/temp; rm -f $HOME/temp/*; ls -al /tmp/*.log; rm -f /tmp/*.log'
done
