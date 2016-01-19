::set advanced parameter list for sidacoja
set cmdHome=%CD%
echo off
set input=jdbc:hsqldb:hsql://localhost:9001/mdb
set inputType=JDBC
set table=copyTable

set output=C:\\Users\\Chuck\\sidacoja.xls
set outputType=XLS

set columns=
set columns=ID,AGE,START,FIRST
set sequencers=
set sequencers=ID,AGE,START,FIRST
set filters=
set filters=OR,ID,NE,100

set cacheOnly=false;

::display values
echo on

set input 
set table

set output 
 
set columns 
set sequencers 
set filters
set cacheOnly

cd \Users\Chuck\Documents\workspace-sidacoja\sidacoja-utils\target\
java -cp sidacoja-utils.0.1.0-SNAPSHOT.jar; com.sidacoja.utils.BatchApplication
cd /D %cmdHome%
set cmdHome =
