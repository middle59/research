#!/bin/bash
export WHERE=$HOME
export CLASSPATH=.:$WHERE/HADS-Maven

cd $WHERE/HADS-Maven

mvn compile
mvn package


cd target/classes
pwd

for i in 10.0.2.15; do
   echo $i
   tar cf - HADS/*/*.class | ssh $i 'cd $HOME; tar xpf -; ls -al HADS; du -s HADS'
   sleep 3
   echo $i
   ssh $i 'CLASSPATH=.:$HOME rmiregistry </dev/null 2>/dev/null 1>/dev/null &'
done