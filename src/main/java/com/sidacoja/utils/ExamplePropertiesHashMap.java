package com.sidacoja.utils;

// import for standalone use - com.sidacoja.utils.*;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
	read one row of properties from xml into a hashmap
*/
public class ExamplePropertiesHashMap {
	
    public static void main( String[] args ) {

    	Sidacoja sdcj = new Sidacoja();
    	
    	sdcj.input("./resources/propertiesRowset.xml");
    	sdcj.inputType("xml");

		sdcj.setCacheOnly(true);
    	sdcj.output("N/A");
    	sdcj.outputType("xml");
    	   	
    	RowCache cache = new RowCache();
    	try {
    		cache = sdcj.fire();
    	} catch( Exception e) {
    		console(e.getMessage() );
    		e.printStackTrace();
    	}

    	console("output selected: "+cache.countSelected());
		
		//load hashmap
		Map<String,String> hmap = new HashMap<String,String>();
    	
		boolean titlePrint = true;
		List<Row> rowList = cache.getList();
		for(Row row: rowList) {
			console("\n");
			List<Cell> cells = row.getList();
			if(titlePrint) {
				titlePrint = false;
				for(Cell cell: cells) {
					hmap.put(cell.getLabel(),cell.getValue());
				}
			}

			//demonstrate hashmap is loaded from xml
			Iterator iterator = hmap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				System.out.println("property " + key + " = " + hmap.get(key));
			}
			
		}
    }
    
    public static void console(String sz) {
    	System.out.print(sz);
    }
}

