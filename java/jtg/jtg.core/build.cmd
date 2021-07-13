@echo off
echo Build
call mvn clean install
echo copy to jtg.core.bundle
copy target\jtg.core.jar ..\jtg.core.bundle\lib\jtg.core.jar
