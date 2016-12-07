#!/bin/bash

mvn package
chmod 777 ./bfck
cp ./bfck build
