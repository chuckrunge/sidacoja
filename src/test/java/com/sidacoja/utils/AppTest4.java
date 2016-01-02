package com.sidacoja.utils;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit tests 
 */
public class AppTest4 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest4( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest4.class );
    }

    /**
     * test XLS date logic 
     */
    
    public void testApp01()
    {
    	//* 12/14/14 02:15 PM	41987.59375

    	XLSNumberToDateTime nbrToDate = new XLSNumberToDateTime();
    	String returnSz = nbrToDate.xlsToDate("41987.59375");
    	console("1.==>"+returnSz);
    	
    	//check gregorian date
        console(">"+returnSz.substring(0,10)+"<");
        assertTrue("12/14/2014".equals(returnSz.substring(0, 10)));
        
    	//check calculated time
        console(">"+returnSz.substring(11,16)+"<");
        assertTrue("14:15".equals(returnSz.substring(11, 16)));
 
        //* 12/21/14 12:44 PM	41994.5307060185

    	XLSNumberToDateTime nbrToDate2 = new XLSNumberToDateTime();
    	String returnSz2 = nbrToDate2.xlsToDate("41994.5307060185");
    	console("2.==>"+returnSz2);
    	
    	//check gregorian date
        assertTrue("12/21/2014".equals(returnSz2.substring(0, 10)));
        
    	//check calculated time
        assertTrue("12:44".equals(returnSz2.substring(11, 16)));
        
    }
    /**
     * test XLS date logic 
     */
    
    public void testApp02()
    {
    	//42342.6628703704

    	XLSNumberToDateTime nbrToDate = new XLSNumberToDateTime();
    	String returnSz = nbrToDate.xlsToDate("42342.6628703704");
    	console("1.==>"+returnSz);
    	
    	//check gregorian date
    	console(">"+returnSz.substring(0, 9)+"<");
        assertTrue("12/4/2015".equals(returnSz.substring(0, 9)));
        
    	//check calculated time
        console(">"+returnSz.substring(10,15)+"<");
        assertTrue("15:54".equals(returnSz.substring(10, 15)));
         
        //    	42004.9986111111  12/31/2014  11:58:00 PM
    	XLSNumberToDateTime nbrToDate2 = new XLSNumberToDateTime();
    	String returnSz2 = nbrToDate2.xlsToDate("42004.9986111111");
    	console("1.==>"+returnSz2);
    	
    	//check gregorian date
    	console(">"+returnSz2.substring(0, 9)+"<");
        assertTrue("12/31/2014".equals(returnSz2.substring(0, 10)));
        
    	//check calculated time
        console(">"+returnSz2.substring(11,16)+"<");
        assertTrue("23:58".equals(returnSz2.substring(11, 16)));
         
    }

public void console(String sz) {
	System.out.println(sz);
}

} //end class
