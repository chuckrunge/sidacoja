package com.sidacoja.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * convert one input type to another
 *
 */
public class BatchApplication {

	private static String input;
	private static String inputType;
	private static String[] columns = null;
	private static String[] sequencers = null;
	private static List<String[]> filters = new ArrayList<String[]>();
	private static boolean cacheOnly = false;
	private static String output;
	private static String outputType;
	private static String table;
	private static String outputTable;
	
	private static boolean envFound = false;

/*
 * provide interface to sidacoja from batch environment	
 */
	public static void main( String[] args ) {

    	console("sidacoja utilitites");
    	
    	//retrieve environment variaables
    	envFound = checkEnvironment();

    	//need environment variables or parms from arguments 
    	if(!envFound && args.length != 4) {
    		console("4 input parameters are required...");
    		console("input file, input type, output file, output type");
    		return;
    	}
    	
    	int i = 0;
    	Sidacoja sdcj = new Sidacoja();
    	
    	//use parameters if provided
    	for(String parm: args) {
    		console(parm);
    		if(i==0) { input = parm; }
    		if(i==1) { inputType = parm; }
    		if(i==2) { output = parm; }
    		if(i==3) { outputType = parm;; }
    		i++;
    	}

    	//update sidacoja with input variables
		if(!isNullOrEmpty(input)) {
			sdcj.input(input);
			console("input: "+input);
		}
		if(!isNullOrEmpty(inputType)) {
			sdcj.inputType(inputType);
			console("inputType: "+inputType);
		}
		if(!isNullOrEmpty(output)) {
			sdcj.output(output);
			console("output: "+output);
		}
		if(!isNullOrEmpty(outputType)) {
			sdcj.outputType(outputType);
			console("outputType: "+outputType);
		}
		if(columns!=null) {
			sdcj.columns(columns);
			console(columns.length+" columns selected");
		}
		if(sequencers!=null) {
			sdcj.sequence(sequencers);
			console(sequencers.length+" sequencers selected");
		}
		if(!filters.isEmpty()) {
			sdcj.addFilter(filters.get(0));
			console(filters.size()+" filters provided");
		}
		
		if(cacheOnly) { 
			sdcj.setCacheOnly(cacheOnly); 
			console("cacheOnly: "+cacheOnly);
		}
		if(!isNullOrEmpty(table)) { 
			sdcj.setTable(table); 
			console("table: "+table);
		}
		if(!isNullOrEmpty(outputTable)) { 
			sdcj.setTable(outputTable); 
			console("outputTable: "+outputTable);			
		}

    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}
    	
    	console("output rows selected: "+cache.countSelected());
    		
    }
 /*
  * check for variables from system environment
  */
    public static boolean checkEnvironment() {
    	boolean wasFound = false;
    	String[] vars = {"input",
    			"inputType",
    			"columns",
    			"sequencers",
    			"filters",
    			"cacheOnly",
    			"output",
    			"outputType",
    			"table",
    			"outputTable"};
    	
    	Map<String,String> env = System.getenv();
    	//Properties env = System.getProperties();    	
    	//dumpVars(env);

    	List<String> keys = new ArrayList<String>(env.keySet());
    	Collections.sort(keys);
    	for (String key : keys) {
        		if("input".equals(key)) {
        			input = (String) env.get(key);
        			wasFound = true;
        		}
        		if("inputType".equals(key)) {
        			inputType = (String) env.get(key);
        			wasFound = true;
        		}
        		if("columns".equals(key)) {
        			String szVar = (String) env.get(key);
        			columns = loadStringArray(szVar);
        			wasFound = true;
        		}
        		if("sequencers".equals(key)) {
        			sequencers  = loadStringArray((String) env.get(key));
        			wasFound = true;
        		}
        		if("filters".equals(key)) {
        			//every filter is an array - filter is a list of arrays
        			String filterString = (String) env.get(key);
        			if(isNullOrEmpty(filterString)) {
        				console("filters not found");
        			} else {
            			String[] oneFilter = loadStringArray((String) env.get(key));
            			filters.add(oneFilter);
            			wasFound = true;        				
        			}
        		}
        		if("cacheOnly".equals(key)) {
        			if("true".equals(env.get(key))) {
        				cacheOnly = true;
        				wasFound = true;
        			};
        			if("false".equals(env.get(key))) {
        				cacheOnly = false;
        				wasFound = true;
        			};
        		}
        		if("output".equals(key)) {
        			output = (String) env.get(key);
        			wasFound = true;
        		}
        		if("outputType".equals(key)) {
        			outputType = (String) env.get(key);
        			wasFound = true;
        		}
        		if("table".equals(key)) {
        			table = (String) env.get(key);
        			wasFound = true;
        		}
        		if("outputTable".equals(key)) {
        			outputTable = (String) env.get(key);
        			wasFound = true;
        		}
    		
    	}
    	
    	return wasFound;
    	
    }
/*
 * load string received by parsing it into an array
 */
    public static String[] loadStringArray(String sz) {
    	
    	String[] szArray = null;
    	String delims = ","; // use + to treat consecutive delims as one;
    	                    // omit to treat consecutive delims separately
    	szArray = sz.split(delims);
    	
    	return szArray;
    	
    }
 
	public static boolean isNullOrEmpty(String sz) {
		boolean result = false;
		
		if(sz == null) { 
			result = true;
		} else {
			if(sz.isEmpty()) { 
				result = true;
			}
		}
		
		return result;
		
	}
/*
 * test method to review system environment
 */
	private static void dumpVars(Map<String, ?> m) {
		  List<String> keys = new ArrayList<String>(m.keySet());
		  Collections.sort(keys);
		  for (String k : keys) {
		    System.out.println(k + " : " + m.get(k));
		  }
	}
	
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
