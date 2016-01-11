::set advanced parameter list for sidacoja
set cmdHome=%CD%
echo off
set input=C:\\Users\\Chuck\\sidacoja.xls
set inputType=XLS

set output=C:\\Users\\Chuck\\sidacoja.csv
set outputType=CSV

set columns="CURRENCY1","CURRENCY2","AMOUNT","SETTLEMENT","ACCOUNT"
set sequencers="CURRENCY1","CURRENCY2","AMOUNT","SETTLEMENT","ACCOUNT"
set filters="OR","CURRENCY1","EQ","USD"

set cacheOnly=false;

::show me what you did
echo on

set input 

set output 
 
set columns 
set sequencers 
set filters
set cacheOnly

cd \Users\Chuck\Documents\workspace-sidacoja\sidacoja-utils\target\
java -cp sidacoja-utils.0.1.0-SNAPSHOT.jar; com.sidacoja.utils.BatchApplication
cd /D %cmdHome%
set cmdHome =