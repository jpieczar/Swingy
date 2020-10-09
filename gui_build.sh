#!/bin/sh

mvn clean package
chmod +x target/Fix-1.0-SNAPSHOT.jar
java -cp target/Fix-1.0-SNAPSHOT.jar:classes/* Main g
