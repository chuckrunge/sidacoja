# sidacoja
A Simple Data Conversion for Java

Copyright (C) 2015  Chuck Runge
Lombard, IL.
CGRunge001@GMail.com

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 


SIDACOJA

Sidacoja provides methods for simple selection, sorting, and translation for tabular data contained in JDBC, XLS, CSV, XML or JSON format.  A spreadsheet is a good example of the type of data supported.  In JSON or XML, a table appears as an array of rows.
  
Tabular data in any of the listed formats can be read or written.  Or a table read in one format can be written in another format.  If no selection criteria is supplied, the entire file will be written.

WHAT IS SIDACOJA GOOD FOR?
A database table can be dumped to a spreadsheet.  Or a spreadsheet can be loaded back to the database.  JSON input can be received as a Java object for further processing.  An XML table can be processed in the same way.  And when you're done, you can write it out as a csv or any other format.

EXAMPLES

Example 1: copy a database table for backup
sidacoja_copy_table_to_table.cmd (batch example)

Example 2: extract rows from table for testing
sidacoja_copy_table_to_table.cmd (batch example)

Example 3: dump a table to csv
sidacoja_dump_table_to_csv.cmd (batch example)

Example 4: copy a database table to a spreadsheet
sidacoja_dump_table_to_xls.cmd (batch example)

Example 5: load a spreadsheet into a database
sidacoja_load_table_from_xls.cmd (batch example)
   
Example 6: translate json to xml for processing in memory
ExampleInMemory.java (online example)

Example 7 load an external properties table to xml in memory
ExampleProperties.java (online example)


REQUIRED LIBRARIES

Please note that several jar files might be needed on the classpath to support the operation in process.
1) commons-csv-1.1.jar for CSV files
2) poi-3.13.jar for XLS files
3) json-simple-1.1.1.jar for JSON files
4) JDBC driver for your database

RELEASE NOTES - 0.1.4
- Added UI for desktop.  See README.doc for details.

RELEASE NOTES - 0.1.3
- Added asterisk to support LIKE in windows batch files.

RELEASE NOTES - 0.1.2
- Added support for NOTLIKE in row filter.

RELEASE NOTES - 0.1.1
- Added support for LIKE in row filter (it saves typing in long strings for selection).


PROCESSING OVERVIEW
===================

Each data type is processed by it's own input class, and each type has it's own output class.  A common format of rows and cells called RowCache is used in between.  A RowCache contains a collection of rows, and each row contains a collection of cells.

For each run, one file is input, and one file is output, along with an explicit file type for each.  These parameters are required.

    	input("./resources/Crimes2014.xls"); 
    	inputType("XLS");


    	output("./sidacoja.xls");
    	outputType("XLS");

Processing begins with the command to “fire”.

	fire();

This is all that's required to create a RowCache and write it to an output file.  JDBC uses a URL string instead of a file path/file name.  The table name is passed as a separate parameter [ setTable("table") ], and can be overidden in JDBC output [ setOutputTable("outputTable") ]. 

Optionally, specific columns can be selected by name by passing them in as a String array.

    	columns(new String[]{
			"ID", "Case Number",	
    		"Date", "Primary Type",	
    		"Description", "Location Description",	
    		"Arrest"
    	});

Or the table can be sorted on specific columns.  Of course, sorted columns must be selected for processing (as above). 

	sequence(new String[] {
    		"Case Number", "Arrest"
    	});

And, if you'd like to pull a subset, columns and values can be specified for row selection.  Any one of these is enough to select a row.

    	addFilter(new String[]{"IF", "Primary Type","EQ","THEFT"});
    	addFilter(new String[]{"OR", "Primary Type","EQ","BATTERY"});
    	addFilter(new String[]{"OR", "Primary Type","EQ","BURGLARY"});

Also, the “fire” method returns a RowCache object which can be received by the invoking program.  A JSON or XML document can then be processed as a POJO, same as a spreadsheet or database table.

    	RowCache cache = sdjc.fire();
		
The row cache contains everything that was read from input, and can be used as a Java object.  A row cache is basically a collection of rows, each of which is a collection of columns.  


An example using all of the above, both required and optional, is shown below.

    	Sidacoja sdjc = new Sidacoja();
    	sdjc.input("./resources/Crimes2014.xls"); 
    	sdjc.inputType("XLS");

    	sdjc.columns(new String[]{
    			"Case Number",	
    			"Date",	
    			"Primary Type",	
    			"Description",	
    			"Location Description",	
    			"Arrest",	
    			"Updated On"
    	});

    	sdjc.sequence(new String[] {
    			"Case Number", "Arrest"
    	});
    	
    	sdjc.addFilter(new String[]{"IF", "Primary Type","EQ","THEFT"});
    	sdjc.addFilter(new String[]{"OR", "Primary Type","EQ","BATTERY"});
    	sdjc.addFilter(new String[]{"OR", "Primary Type","EQ","BURGLARY"});
    	
    	sdjc.output("./sidacoja.XLS");
    	sdjc.outputType("XLS");
    	
    	RowCache cache = sdjc.fire();
		
		The RowCache object is returned for inspection or further processing.  The cacheOnly parameter is available to stop work after loading the RowCache object.  Set cacheOnly to true if the RowCache is all you need. 
		
		
JDBC EXAMPLE
============		

    	final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";
    	final String USER = "sa";
    	final String PASS = "";

    	Sidacoja sdcj = new Sidacoja();
		
		sdcj.input(DB_URL);
    	sdcj.inputType("jdbc");
    	sdcj.setTable("copyTable"); 
    	
    	sdcj.columns(new String[]{"AGE", "ID", "START", "FIRST"});
    	sdcj.sequence(new String[]{"START"});
    	    	
    	sdcj.output(DB_URL);
    	sdcj.setOutputTable("copyTable");
    	sdcj.outputType("jdbc");
    	    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		System.out.println(e.getMessage() );
    		e.printStackTrace();
    	}

		
ADVANCED DATA SELECTION
=======================

Basic AND / OR logic is supported.  IF begins a query.  AND ties a criterion to the previous one.  AND will string multiple criteria together, and they all must be true.  OR begins another criterion.

Example:

    	sdjc.addFilter(new String[]{"IF", "Last Name","EQ","Smith"});
    	sdjc.addFilter(new String[]{"AND", "First Name","EQ","John"});
    	sdjc.addFilter(new String[]{"AND", "City","EQ","Johnsonville"});
    	sdjc.addFilter(new String[]{"OR", "Last Name","EQ","Johnson"});
    	sdjc.addFilter(new String[]{"AND", "First Name","EQ","James"});
    	sdjc.addFilter(new String[]{"AND", "City","EQ","Smithton"});

EQ (equals) is the most specific way to identify rows, but NE (not equal) is also supported.  If excluding rows is simpler, use NE.

Example:

    	sdjc.addFilter(new String[]{"IF", "Last Name","EQ","Smith"});
    	sdjc.addFilter(new String[]{"AND", "First Name","EQ","John"});
    	sdjc.addFilter(new String[]{"AND", "City","NE","Johnsonville"});

LIKE is used for partial matches, or partially entered criteria.  A percent sign (%) is used to match the beginning or the end of a string. A percent sign on both ends means the criteria can appear anywhere.  An asterisk (*) can be used in place of the percent sign. 

Example:

    	sdjc.addFilter(new String[]{"IF", "Last Name","LIKE","Smith%"});
    	sdjc.addFilter(new String[]{"AND", "First Name","LIKE","%John%"});
    	sdjc.addFilter(new String[]{"AND", "City","NOTLIKE","%ville"});

NOTLIKE is the negative of LIKE.  Anything partially matching is omitted.

		
BATCH EXECUTION
===============

A main method is provided for batch execution (BatchApplication class).

The batch interface as provided expects an input file, input type, output file, and output type, in that order.  The program can be used for simple conversion between supported types.  

Additional parameters can be provided via environment variables.  It is suggested that parameters be passed either via command line or via environment variables to avoid confusion.

::set advanced parameter list for sidacoja

set input = "C:\\Users\\Chuck\\sidacoja.xls"
set inputType = XLS

set output = "C:\\Users\\Chuck\\sidacoja.csv"
set outputType = CSV

set columns = "CURRENCY1","CURRENCY2","AMOUNT","SETTLEMENT","ACCOUNT"
set sequencers = "CURRENCY1","CURRENCY2","AMOUNT","SETTLEMENT","ACCOUNT"
set filters = "IF","CURRENCY1","EQ","USD"

set cacheOnly = false;

::execute batch interface
java -cp sidacoja-utils.0.1.0-RELEASE.jar; com.sidacoja.utils.BatchApplication