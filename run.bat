@echo off
cd %~dp0
cd src\main
javac *.java
cd ..\..
java -cp ./src main.SpanningTrees
pause
