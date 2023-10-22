#!/bin/zsh
set -e

imageTag=$1
if [-z "$1"]
  then 
    echo No Image tag provided. Lastet will be used
    imageTag=lastest
fi

imageFullName=$imageTag 

echo [Main App starting building $imageFullName]
echo [Main App] creating jar...

(exec "${BASH_SOURCE%/*}/gradlew" bootjar --no-daemon) 

echo [Main App] creating docker img ....
echo ${BASH_SOURCE%/*}

sudo docker buildx build --platform linux/arm64 --tag "lastest1" .

echo [Main App FiNISHED] image has been build

