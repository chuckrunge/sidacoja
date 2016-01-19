package com.sidacoja.utils;

/**
 * In Memory Example - JSON to XML
 * A received JSON string is converted to XML without reading files. 
 * A user application might then load custom object or pass as string.
 */
public class ExampleInMemory 
{
    public static void main( String[] args ) {

    	Sidacoja sdcj = new Sidacoja();
    	
    	sdcj.input("{\"rootElement\":\"RootElement\",\"List\":{\"Row 2\":[{\"Sponsor\":\"Paypal3\"},{\"Member\":\"Google2\"},{\"Contact\":\"eBay1\"}],\"Row 1\":[{\"Sponsor\":\"Paypal1\"},{\"Member\":\"Google3\"},{\"Contact\":\"eBay2\"}],\"Row 0\":[{\"Sponsor\":\"Paypal2\"},{\"Member\":\"Google1\"},{\"Contact\":\"eBay3\"}]}}");
    	sdcj.inputType("json");
    	
    	sdcj.output("N/A"); //this satisfies rudimentary edit
    	sdcj.outputType("xml");
    	
    	RowCache cache = new RowCache();
    	try {
    		
    		cache = sdcj.fire();
    		
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}

    	console("output selected: "+cache.countSelected()+"\n");
    	
    	//THIS IS THE XML STRING
    	console(sdcj.getReturnString());
    	
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
