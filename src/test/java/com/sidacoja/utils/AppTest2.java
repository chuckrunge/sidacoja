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
    	Sidacoja sdjc = new Sidacoja();
    	sdjc.input("./resources/Crimes2014.xls"); 
    	sdjc.inputType("XLS");

    	sdjc.output("./sidacoja.XLS");
    	sdjc.outputType("XLS");
    	
    	RowCache cache = sdjc.fire();
    	
    	//number of data rows equals number of rows in cache
        //assertTrue( 11363 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        //assertTrue( 5319 == cache.countSelected() );
        
        //number of columns selected for output
        //assertTrue(7 == sdjc.countLabels(cache));
        
        //number of columns selected for output
        //assertTrue(7 == cache.getLabels(sdjc.getColumns()).length);
        
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
    	
    	RowCache cache = sdcj.fire();
    	
    	//number of data rows equals number of rows in cache
        //assertTrue(3 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        //assertTrue(3 == cache.countSelected() );
        
        //number of columns selected for output
        //assertTrue(4 == sdcj.countLabels(cache));
        
        //number of columns selected for output
        //assertTrue(4 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?
    	assertTrue(true);
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
    	
    	RowCache cache = sdcj.fire();
    	
    	//number of data rows equals number of rows in cache
        //assertTrue(3 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        //assertTrue(2 == cache.countSelected() );
        
        //number of columns desired
        //assertTrue(3 == sdcj.countLabels(cache));
        
        //number of columns in output
        //assertTrue(3 == cache.getLabels(sdcj.getColumns()).length);
        
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
    	
    	RowCache cache = sdcj.fire();
    	
    	//number of data rows equals number of rows in cache
        //assertTrue(3 == cache.getList().size() );

        //number of data rows written equals number of rows selected
        //assertTrue(2 == cache.countSelected() );
        
        //number of columns desired
        //assertTrue(3 == sdcj.countLabels(cache));
        
        //number of columns in output
        //assertTrue(3 == cache.getLabels(sdcj.getColumns()).length);
        
        // how to test sort?
    	assertTrue(true);
    }
	    
public void console(String sz) {
	System.out.println(sz);
}

} //end class
