::Example - extract some rows for testing  - use of filters makes this an extract
::        - OR copy a table for backup - simply remove filters
::		 
set cmdHome=%CD%
echo off
::clear environmnet of previous run
set input=
set inputType= 
set table=
set output=
set outputType=
set outputTable=
set columns=
set sequencers=
set filters=
set cacheOnly=

echo on
::JDBC input
set input=jdbc:hsqldb:hsql://localhost:9001/mdb
set inputType=JDBC
set table=copyTable

::JDBC output
set output=jdbc:sqlserver://CHUCK-PC\SQLSERVERADVSP2;database=chuckDB;integratedSecurity=true;user=Chuck-PC\Chuck;
set outputType=jdbc
set outputTable=copyTable

::optional parameters
set columns=ID,AGE,START,FIRST
set sequencers=AGE
set filters=OR,ID,NE,100

::execute batch interface
cd \Users\Chuck\Documents\workspace-sidacoja\sidacoja-utils\target\
java -cp sidacoja-utils.0.1.0-SNAPSHOT.jar; com.sidacoja.utils.BatchApplication

::go back where you started
cd /D %cmdHome%
set cmdHome =
