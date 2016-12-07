#!/bin/bash

mvn package
chmod 777 ./bfck
rm ./target/brainfuck-18.0.jar