package com.sidacoja.utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	
    	//Sidacoja.input("C:/Users/Chuck/names.csv");
    	//Sidacoja.input("C:/users/chuck/rowset.xml");
    	Sidacoja.input("C:/users/chuck/inputFile1.json");
    	Sidacoja.inputType("JSON");
    	
    	Sidacoja.columns(new String[]{"Sponsor","Member", "Contact"});
    	Sidacoja.sequence(new String[] {"Sponsor","Member", "Contact"});
    	Sidacoja.addFilter(new String[]{"OR", "Contact","EQ","eBay2"});
    	Sidacoja.addFilter(new String[]{"OR", "Contact","EQ","eBay3"});
    	//Sidacoja.addFilter(new String[]{"OR", "Emp No.","EQ","1.0"});
    	
    	Sidacoja.output("C:/Users/Chuck/sidacoja.json");
    	Sidacoja.outputType("JSON");
    	
    	Sidacoja.fire();
    	
    	//console(Sidacoja.getSidacoja().toString());
    		
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
