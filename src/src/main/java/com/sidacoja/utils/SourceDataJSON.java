package com.sidacoja.utils;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SourceDataJSON implements SourceData{

	public RowCache processInput(String file) {
		
		RowCache cache = new RowCache();
		List<Row> rows = new ArrayList<Row>();
		List<Cell> cells = new ArrayList<Cell>();
		Row row = new Row();
		Cell cell = new Cell();
        JSONParser parser = new JSONParser();
        int i=0, j=0, k=0, c=0, r=0;
        
        try {
 
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            String label = null, value = null;

            Set<?> keys = jsonObject.entrySet();
            for(Object key: keys){
            	String sz = (String) key.toString();
            	label = sz.substring(0,sz.indexOf('='));
            	Object szObj = jsonObject.get(sz.substring(0,sz.indexOf('=')));

            	String szValue = null;
            	i = szObj.toString().indexOf('['); //find embedded table object
            	if(i == -1) {
            		value = szObj.toString();
            		cache.simpleAttr.put(label, value);
            	} else {
            		cache.simpleAttr.put("List", label);
            		Object listObj = parser.parse(szObj.toString().substring(i));
            		JSONArray jsonObj2 = (JSONArray) listObj;
            		ListIterator<?> iter = jsonObj2.listIterator();
            		while(iter.hasNext()) {
                		c=0; //cell counter
                		row = new Row();
                		row.setNumber(r++); //row counter
                		cells = new ArrayList<Cell>();
            			cell = new Cell();
            			cell.setNumber(c++);
            			szValue = iter.next().toString();

            			k = 1;
            			i = szValue.indexOf(':');
            			while(i != -1) {
                			cell = new Cell();
                			cell.setNumber(c++);
            				j = szValue.indexOf(',',i);
            				if(j==-1) j = szValue.length()-1;
            				label = szValue.substring(k+1,i-1);
            				cell.setLabel(label);
            				
            				value = szValue.substring(i+2, j-1);
            				k = j+1;
            				cell.setValue(value);
            				cells.add(cell);
            				row.setList(cells);
                			i = szValue.indexOf(':',k);
            			}
                		rows.add(row);
            		}
            		cache.setList(rows);
            	}
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    
		return cache;
		
	}
	
    public static void console(String sz) {
    	System.out.println(sz);
    }
    
}
