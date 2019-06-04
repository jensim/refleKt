#!/usr/bin/env bash

echo "Building Example project with benchmarks"

JAR="reflekt-benchmark-1.0-SNAPSHOT-jar-with-dependencies.jar"
MAIN="com.example.annotations.Main"

cd target
java -cp ${JAR} ${MAIN} 3 null                    reflekt         org.reflections
java -cp ${JAR} ${MAIN} 3 null                    org.reflections reflekt
java -cp ${JAR} ${MAIN} 3 com.example.annotations reflekt         org.reflections
java -cp ${JAR} ${MAIN} 3 com.example.annotations org.reflections reflekt