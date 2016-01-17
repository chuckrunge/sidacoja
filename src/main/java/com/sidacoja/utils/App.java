package com.sidacoja.utils;

/**
 * Hello Sidacoja!
 *
 */
public class App 
{
    public static void main( String[] args ) {

    	//final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";
    	//final String USER = "sa";
    	//final String PSWD = "";

    	Sidacoja sdcj = new Sidacoja();
    	
    	//("{\"rootElement\":\"RootElement\",\"List\":{\"Row 2\":[{\"Sponsor\":\"Paypal3\"},{\"Member\":\"Google2\"},{\"Contact\":\"eBay1\"}],\"Row 1\":[{\"Sponsor\":\"Paypal1\"},{\"Member\":\"Google3\"},{\"Contact\":\"eBay2\"}],\"Row 0\":[{\"Sponsor\":\"Paypal2\"},{\"Member\":\"Google1\"},{\"Contact\":\"eBay3\"}]}}");
    	sdcj.input("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RootElement><Row rowId=\"0\"><Sponsor>Paypal2</Sponsor><Member>Google1</Member><Contact>eBay3</Contact></Row><Row rowId=\"1\"><Sponsor>Paypal1</Sponsor><Member>Google3</Member><Contact>eBay2</Contact></Row><Row rowId=\"2\"><Sponsor>Paypal3</Sponsor><Member>Google2</Member><Contact>eBay1</Contact></Row></RootElement>");
    	//("./resources/inputFile1.json");
    	sdcj.inputType("xml");
    	//sdcj.setTable("copyTable"); //Employees
    	
    	//sdcj.columns(new String[]{"Sponsor", "Member", "Contact"});
    	//sdcj.sequence(new String[]{"Sponsor"}); 
    	
    	//sdcj.addFilter(new String[]{"OR", "Sponsor","EQ","Paypal1"});
    	//sdcj.addFilter(new String[]{"OR", "Sponsor","EQ","Paypal2"});
    	
    	//sdcj.setCacheOnly(true);
    	
    	//sdcj.output(DB_URL);
    	//sdcj.setOutputTable("copyTable");
    	//sdcj.outputType("jdbc");
    	
    	sdcj.output("no file required");	//("./sidacoja.xml");
    	sdcj.outputType("json");
    	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}
    	console(sdcj.getReturnString());
    	console("output selected: "+cache.countSelected());
    	
    	Common utils = new Common();
    	boolean b = true;
    	int i = 0;
    	short j = 0;
    	console(utils.whatIs(i));
    	console(utils.whatIs(j));
    	console(utils.whatIs(b));
    		
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
