package com.sidacoja.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit tests 
 */
public class AppTest2 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest2( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest2.class );
    }

    /**
     * execute against XLS
     */

    public void testApp02()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/Crimes2014.xls"); 
    	sdcj.inputType("XLS");

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
        assertTrue( 11363 == cache.countSelected() );
        
        //number of columns selected for output
        assertTrue(11 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns selected for output
        assertTrue( 11 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?
        assert(true);
    }

    /**
     * execute against CSV
     */
    
    public void testApp01()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/Names.csv");
    	sdcj.inputType("CSV");
    	
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
        assertTrue(9 == cache.countSelected() );
        
        //number of columns selected for output
        assertTrue(4 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns selected for output
        assertTrue(4 == cache.getLabels(sdcj.getColumns()).length);
        
    }

    /**
     * execute against XML
     */
    public void testApp03()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/rowset.xml");
    	sdcj.inputType("XML");
    	
    	sdcj.output("./sidacoja.xml");
    	sdcj.outputType("XML");
    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch(Exception e) {
    		console(e.getMessage());
    	}

    	//number of data rows equals number of rows in cache
        assertTrue(3 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        assertTrue(3 == cache.countSelected() );
        
        //number of columns desired
        assertTrue(5 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns in output
        assertTrue(5 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?
    	assertTrue(true);
    }

    /**
     * execute against JSON
     */
    public void testApp04()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/inputFile1.json");
    	sdcj.inputType("JSON");
    	
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
        assertTrue(3 == cache.countSelected() );
    	
        //number of columns desired
        assertTrue(3 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns in output
        assertTrue(3 == cache.getLabels(sdcj.getColumns()).length);
        
    }
	    
    public void console(String sz) {
    	System.out.println(sz);
    }

} //end class
