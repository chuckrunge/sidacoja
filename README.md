# sidacoja
A Simple Data Conversion for Java

Copyright (C) 2015  Chuck Runge
Lombard, IL.
CGRunge001@GMail.com

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 


Sidacoja provides methods for simple selection, sorting, and translation for tabular data contained in XLS, CSV, XML or JSON format.  A spreadsheet is a good example of the type of data supported.  In JSON or XML, a table appears as an array of cells.
  
Any of the listed formats can be read or written.  Or a table read in one format can be written in another.  If no selection criteria is supplied, the entire file will be written. 

Each type is processed by it's own input class, and each type has it's own output class.  A common format of rows and cells called RowCache is used in between.  A RowCache contains a collection of rows, and each row contains a collection of cells.

For each run, one file is input, and one file is output, along with an explicit file type for each.  These parameters are required.

    	input("./resources/Crimes2014.xls"); 
    	inputType("XLS");


    	output("./sidacoja.xls");
    	outputType("XLS");

Processing begins with the command to “fire”.

	Fire();

This is all that's required to create a RowCache and write it to an output file.

Optionally, specific columns can be selected by name by passing them in as a String array.

    	columns(new String[]{
			"ID", "Case Number",	
    		"Date", "Primary Type",	
    		"Description", "Location Description",	
    		"Arrest"
    	});

Or the table can be sorted on specific columns.   

	sequence(new String[] {
    		"Case Number", "Arrest"
    	});

And, if you'd like to pull a subset, columns and values can be specified for row selection.  Any one of these is enough to select a row.

    	addFilter(new String[]{"OR", "Primary Type","EQ","THEFT"});
    	addFilter(new String[]{"OR", "Primary Type","EQ","BATTERY"});
    	addFilter(new String[]{"OR", "Primary Type","EQ","BURGLARY"});

Also, the “fire” method returns a RowCache object which can be received by the invoking program.

    	RowCache cache = sdjc.fire();


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
    	
    	sdjc.addFilter(new String[]{"OR", "Primary Type","EQ","THEFT"});
    	sdjc.addFilter(new String[]{"OR", "Primary Type","EQ","BATTERY"});
    	sdjc.addFilter(new String[]{"OR", "Primary Type","EQ","BURGLARY"});
    	
    	sdjc.output("./sidacoja.XLS");
    	sdjc.outputType("XLS");
    	
    	RowCache cache = sdjc.fire();

		
ADVANCED DATA SELECTION

Basic AND / OR logic is supported.  OR begins a new criterion.  AND ties a criterion to the previous one.  
So AND will string multiple criteria together, and they all must be true.

Example:
    	sdjc.addFilter(new String[]{"OR", "Last Name","EQ","Smith"});
    	sdjc.addFilter(new String[]{"AND", "First Name","EQ","John"});
    	sdjc.addFilter(new String[]{"AND", "City","EQ","Johnsonville"});

EQ (equals) is the most specific way to identify rows, but NE (not equal) is also supported.  If excluding rows is simpler, NE is also available.

Example:
    	sdjc.addFilter(new String[]{"OR", "Last Name","EQ","Smith"});
    	sdjc.addFilter(new String[]{"AND", "First Name","EQ","John"});
    	sdjc.addFilter(new String[]{"AND", "City","NE","Johnsonville"});

		
BATCH EXECUTION

A main method is provided for batch execution.  Please note that several jar files might be needed on the classpath to support the operation in process.
-commons-csv-1.1.jar
-poi-3.13.jar
-json-simple-1.1.1.jar

The batch interface as provided expects an input file, input type, output file, and output type, in that order.  The program can be used for conversion between supported types.  Additional parameters can be added, if needed.
