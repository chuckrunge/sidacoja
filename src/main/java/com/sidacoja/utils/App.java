package com.sidacoja.utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
 
    	Sidacoja sdcj = new Sidacoja();

    	sdcj.input("./resources/inputFile1.json");
    	sdcj.inputType("json");
    	//sdjc.columns(new String[]{"Sponsor","Member", "Contact"});
    	//Sidacoja.sequence(new String[] {"Sponsor","Member", "Contact"});
    	//sdcj.addFilter(new String[]{"OR", "Method Of Contact","NE","online application"});
    	//Sidacoja.addFilter(new String[]{"OR", "Contact","EQ","eBay3"});
    	//Sidacoja.addFilter(new String[]{"OR", "Emp No.","EQ","1.0"});
    	
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
