#!/bin/sh

find -name *.java > sources.txt
javac -sourcepath . -d runMe @sources.txt
./rungame.sh
