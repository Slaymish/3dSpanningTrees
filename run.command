#!/bin/bash
cd "$(dirname "$0")"
cd src/main
javac *.java
cd ../..
java -cp ./src main.SpanningTrees