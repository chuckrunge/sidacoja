::Example -copy a table to a spreadsheet
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

::XLS output
set output=C:\Users\Chuck\sidacoja.xls
set outputType=XLS

::execute batch interface
cd \Users\Chuck\Documents\workspace-sidacoja\sidacoja-utils\target\
java -cp sidacoja-utils.0.1.0-RELEASE.jar; com.sidacoja.utils.BatchApplication

::go back where you started
cd /D %cmdHome%
set cmdHome =
