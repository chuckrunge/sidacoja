package com.sidacoja.utils;

/**
 * Hello Sidacoja!
 *
 */
public class App 
{
    public static void main( String[] args ) {

    	 final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";
    	 final String USER = "sa";
    	 final String PASS = "";

    	Sidacoja sdcj = new Sidacoja();

    	sdcj.input(DB_URL);
    	sdcj.inputType("jdbc");
    	sdcj.setTable("copyTable"); //Employees
    	
    	sdcj.columns(new String[]{"AGE", "ID", "START", "FIRST"});
    	sdcj.sequence(new String[]{"START"}); //"AGE", "ID", , "FIRST"
    	
    	sdcj.addFilter(new String[]{"OR", "AGE","NE","30"});
    	sdcj.addFilter(new String[]{"OR", "AGE","NE","99"});
    	//Sidacoja.addFilter(new String[]{"OR", "Emp No.","EQ","1.0"});
    	
    	//sdcj.setCacheOnly(true);
    	
    	//sdcj.output(DB_URL);
    	//sdcj.setOutputTable("copyTable");
    	//sdcj.outputType("jdbc");
    	
    	sdcj.output("./sidacoja.xls");
    	sdcj.outputType("xls");
    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}
    	
    	console("output selected: "+cache.countSelected());
    		
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
