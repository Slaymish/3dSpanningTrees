#!/bin/bash
cd "$(dirname "$0")"
cd ../.. # navigate to the root of your project directory
javac src/main/*.java
java src/main/SpanningTrees
