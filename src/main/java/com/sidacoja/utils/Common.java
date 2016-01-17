package com.sidacoja.utils;

/*
 * common utility methods
 */
public class Common {
	
	long demoL= 0;
	char demoC = 'c';
	String demoS = "sz";
	
	public String whatIs(Object obj) {
		
		return obj.getClass().getSimpleName();
				
	}

    public boolean isSelected(String label, String[] columns) {
    	
      	if(columns==null) {
        	return true;
      	}
        
      	for(int m=0;m<columns.length;m++) {
       		if(label.equals(columns[m])) {
      			return true;
      		} //end if
        } //end criteria loop

        return false;
        	
    }
    
   	public boolean isNullOrEmpty(String sz) {
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
   	
} //end class
