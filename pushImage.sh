#!/bin/bash
set -e

imageTag=$1
if [-z "$1"]
  then 
    echo No Image tag provided. Lastet will be used
    imageTag=lastest
fi

imageFullName=$imageTag 

echo [Main App starting] pushing image $imageFullName

docker push $imageFullName

echo [Main App finished]  image $imageFullName pushed
