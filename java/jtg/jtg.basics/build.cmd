@echo off
echo Build
call mvn clean install
echo copy to jtg.core.bundle
copy target\jtg.basics.jar ..\jtg.basics.bundle\lib\jtg.basics.jar
