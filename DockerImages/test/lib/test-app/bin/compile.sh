#!/bin/bash
export WHERE=$HOME
export HOMEDIR=test-app

cd $WHERE/$HOMEDIR

mvn compile
mvn package

