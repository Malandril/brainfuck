#!/bin/bash

mvn package
chmod 777 ./bfck
mkdir build
rm ./target/brainfuck-18.0.jar
mv ./target/brainfuck.jar build
mv ./bfck build
