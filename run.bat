@echo off
if not exist lib\mysql-connector-j-8.4.0.jar (
  echo MySQL JDBC driver not found in lib\mysql-connector-j-8.4.0.jar
  echo Download it as explained in README.md, then run this file again.
  pause
  exit /b 1
)
if not exist out mkdir out
javac --add-modules jdk.httpserver -cp "lib\mysql-connector-j-8.4.0.jar" -d out src\util\*.java src\model\*.java src\dao\*.java src\service\*.java src\controller\*.java
if errorlevel 1 pause & exit /b 1
java --add-modules jdk.httpserver -cp "out;lib\mysql-connector-j-8.4.0.jar" controller.WebServer
