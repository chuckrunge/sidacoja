package com.sidacoja.utils;

/**
 * In Memory Example - JSON to XML
 * A received JSON string is converted to XML without reading files. 
 * A user application might then load custom object or pass as string.
 */
public class ExampleProperties
{
    public static void main( String[] args ) {

    	final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";

    	Sidacoja sdcj = new Sidacoja();

    	sdcj.input(DB_URL);
    	sdcj.inputType("jdbc");
    	sdcj.setTable("properties");
    	
    	sdcj.output("N/A");
    	sdcj.outputType("xml");
    	
    	RowCache cache = new RowCache();
    	try {

    		cache = sdcj.fire();
    	
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}

    	console("output selected: "+cache.countSelected());

    	//THIS IS THE XML STRING RETURNED
    	console(sdcj.getReturnString());
    		
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}