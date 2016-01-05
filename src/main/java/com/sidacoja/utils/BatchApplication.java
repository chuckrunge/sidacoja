package com.sidacoja.utils;

/**
 * convert one file type to another
 *
 */
public class BatchApplication 
{
    public static void main( String[] args ) {

    	console("sidacoja utilitites");
    	if(args.length != 4) {
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
     	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}
    	
    	console("output rows selected: "+cache.countSelected());
    		
    }
    
    public static void console(String sz) {
    	System.out.println(sz);
    }
}
