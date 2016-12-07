#!/bin/bash

mvn package
mkdir ./build
rm ./target/brainfuck-18.0.jar
mv ./target/brainfuck.jar build
