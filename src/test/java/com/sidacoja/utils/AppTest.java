package com.sidacoja.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit tests 
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * execute against XLS
     */

    public void testApp02()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/Crimes2014.xls"); 
    	sdcj.inputType("XLS");

    	sdcj.columns(new String[]{
				//"ID",
    			"Case Number",	
    			"Date",	
    			//"Block",
    			//"IUCR",
    			"Primary Type",	
    			"Description",	
    			"Location Description",	
    			"Arrest",	
    			"Updated On"
    	});

    	sdcj.sequence(new String[] {
    			"Case Number", "Arrest"
    	});
    	
    	sdcj.addFilter(new String[]{"IF", "Primary Type","EQ","THEFT%"});
    	sdcj.addFilter(new String[]{"IF", "Primary Type","EQ","BATTERY"});
    	sdcj.addFilter(new String[]{"IF", "Primary Type","EQ","BURGLARY"});
    	
    	sdcj.output("./sidacoja.XLS");
    	sdcj.outputType("XLS");
 
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch(Exception e) {
    		console(e.getMessage());
    	}

    	//number of data rows equals number of rows in cache
        assertTrue( 11363 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        assertTrue( 5319 == cache.countSelected() ); 
        
        //number of columns selected for output 7
        assertTrue(7 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns selected for output 7
        assertTrue(7 == cache.getLabels(sdcj.getColumns()).length);
        
    }

    /**
     * execute against CSV
     */
    
    public void testApp01()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/Names.csv");
    	sdcj.inputType("CSV");
    	
    	sdcj.columns(new String[]{
    	"First Name","Last Name","UserID","Date"
    	});

    	sdcj.sequence(new String[] {
    			"First Name","Last Name","UserID","Date"
    	});

    	sdcj.addFilter(new String[]{"IF", "Last Name","LIKE","%Rugher%"});
    	
    	sdcj.output("./sidacoja.csv");
    	sdcj.outputType("CSV");
    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch(Exception e) {
    		console(e.getMessage());
    	}
    	
    	//number of data rows equals number of rows in cache
        assertTrue(9 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        assertTrue(3 == cache.countSelected() );
        
        //number of columns selected for output
        assertTrue(4 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns selected for output
        assertTrue(4 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?

    }

    /**
     * execute against XML
     */
    public void testApp03()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/rowset.xml");
    	//Sidacoja.input("C:/users/chuck/inputFile1.json");
    	sdcj.inputType("XML");
    	
    	sdcj.columns(new String[]{
    			"CURRENCY1","AMOUNT","ACCOUNT"
		});
    	sdcj.sequence(new String[]{
    			"AMOUNT","ACCOUNT"
		});
    	sdcj.addFilter(new String[] {"IF","CURRENCY1","EQ", "USD"});
    	
    	sdcj.output("./sidacoja.xml");
    	sdcj.outputType("XML");
    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch(Exception e) {
    		console(e.getMessage());
    	}
    	
    	//console(Sidacoja.getSidacoja().toString());
    	//number of data rows equals number of rows in cache
        assertTrue(3 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        assertTrue(2 == cache.countSelected() );
        
        //number of columns desired
        assertTrue(3 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns in output
        assertTrue(3 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?

    }

    /**
     * execute against JSON
     */
    public void testApp04()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/inputFile1.json");
    	sdcj.inputType("JSON");
    	
    	sdcj.columns(new String[]{"Sponsor", "Member", "Contact"});
    	sdcj.sequence(new String[] {"Sponsor", "Member", "Contact"});
    	sdcj.addFilter(new String[]{"OR", "Contact","EQ","eBay2"});
    	sdcj.addFilter(new String[]{"OR", "Contact","EQ","eBay3"});
    	//Sidacoja.addFilter(new String[]{"OR", "Emp No.","EQ","1.0"});
    	
    	sdcj.output("./sidacoja.json");
    	sdcj.outputType("JSON");
    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch(Exception e) {
    		console(e.getMessage());
    	}
    	
    	//number of data rows equals number of rows in cache
        assertTrue(3 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        assertTrue(2 == cache.countSelected() );
        
        //number of columns desired
        assertTrue(3 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns in output
        assertTrue(3 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?

    }
	    
public void console(String sz) {
	System.out.println(sz);
}

} //end class
