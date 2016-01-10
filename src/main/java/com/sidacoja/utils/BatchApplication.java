package com.sidacoja.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * convert one file type to another
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

	public static void main( String[] args ) {

    	console("sidacoja utilitites");
    	envFound = checkEnvironment();
    	
    	if(!envFound && args.length != 4) {
    		console("4 input parameters are required...");
    		console("input file, input type, output file, output type");
    		//return;
    	}
    	
    	int i = 0;
    	Sidacoja sdcj = new Sidacoja();
    	for(String parm: args) {
    		console(parm);
    		if(i==0) {
    			sdcj.input(parm);
    		}
    		if(i==1) {
    			sdcj.inputType(parm);
    		}
    		if(i==2) {
    			sdcj.output(parm);
    		}
    		if(i==3) {
    			sdcj.outputType(parm);
    		}
    		i++;
    	}

		if(!isNullOrEmpty(input)) {
			sdcj.input(input);
			console("input: "+input);
		}
		if(!isNullOrEmpty(inputType)) {
			sdcj.inputType(inputType);
			console("inputType: "+inputType);
		}
		if(!isNullOrEmpty(output)) {
			console("output: "+output);
			sdcj.output(output);
		}
		if(!isNullOrEmpty(outputType)) {
			console("outputType: "+outputType);
			sdcj.outputType(outputType);
		}
	/*
		if(!isNullOrEmpty(columns.toString())) {
			//console("columns: "+columns.toString());
			sdcj.columns(columns);
		}
		if(!isNullOrEmpty(sequencers.toString())) {
			//console("outputType: "+sequencers.toString());
			sdcj.sequence(sequencers);
		}
		sdcj.setCacheOnly(cacheOnly);
	*/
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}
    	
    	console("output rows selected: "+cache.countSelected());
    		
    }
 
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
    	//dumpVars(env);
    	for(String var:vars) {
    		console(var+": "+env.get(var));
    		//if( env.containsKey(var) ) {
    			//console("env.containsKey: "+var);
        		if("input".equals(var)) {
        			//console("input is equals");
        			input = env.get(var);
        			//console("input: "+input);
        			wasFound = true;
        		}
        		if("inputType".equals(var)) {
        			//console("inputType is equals");
        			inputType = env.get(var);
        			//console("inputType: "+inputType);
        			wasFound = true;
        		}
        		if("columns".equals(var)) {
        			//console("columns is equal");
        			String szVar = env.get(var);
        			//console("from properties: "+szVar);
        			columns = loadStringArray(szVar);
        			wasFound = true;
        		}
        		if("sequencers".equals(var)) {
        			sequencers  = loadStringArray(env.get(var));
        			wasFound = true;
        		}
        		if("filters".equals(var)) {
        			//every filter is an array - filter is a list of arrays
        			String filterString = env.get(var);
        			if(isNullOrEmpty(filterString)) {
        				console("filters not found");
        			} else {
            			String[] oneFilter = loadStringArray(env.get(var));
            			filters.add(oneFilter);
            			wasFound = true;        				
        			}
        		}
        		if("cacheOnly".equals(var)) {
        			if("true".equals(env.get(var))) {
        				cacheOnly = true;
        				wasFound = true;
        			};
        			if("false".equals(env.get(var))) {
        				cacheOnly = false;
        				wasFound = true;
        			};
        		}
        		if("output".equals(var)) {
        			output = env.get(var);
        			wasFound = true;
        		}
        		if("outputType".equals(var)) {
        			outputType = env.get(var);
        			wasFound = true;
        		}
        		if("table".equals(var)) {
        			table = env.get(var);
        			wasFound = true;
        		}
        		if("outputTable".equals(var)) {
        			outputTable = env.get(var);
        			wasFound = true;
        		}
        	//}
    		
    	}
    	
    	return wasFound;
    	
    }

    public static String[] loadStringArray(String sz) {
    	
    	//console("in loadStringArray: "+sz);
    	String[] szArray = null;
    	String delims = ","; // use + to treat consecutive delims as one;
    	                    // omit to treat consecutive delims separately
    	szArray = sz.split(delims);
    	//for(String sx:szArray) {
    	//	console(sx);
    	//}
    	
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
