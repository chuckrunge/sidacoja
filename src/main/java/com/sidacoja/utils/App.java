package com.sidacoja.utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	
    	Sidacoja.input("C:/Users/Chuck/names.csv");
    	Sidacoja.inputType("CSV");
    	
    	Sidacoja.columns(new String[]{"First Name","Last Name", "UserID"}); //, "Date"});
    	Sidacoja.sequence(new String[] {"UserID", "Last Name", "First Name"});
    	Sidacoja.addFilter(new String[]{"OR", "UserID","EQ","U004454"});
    	Sidacoja.addFilter(new String[]{"OR", "UserID","EQ","U006676"});
    	Sidacoja.addFilter(new String[]{"OR", "First Name","EQ","Chris"});
    	
    	Sidacoja.output("C:/Users/Chuck/sidacoja.csv");
    	Sidacoja.outputType("CSV");
    	
    	Sidacoja.fire();
    	
    	//console(Sidacoja.getSidacoja().toString());
    		
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
