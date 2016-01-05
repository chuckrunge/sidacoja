package com.sidacoja.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TargetDataJSON implements TargetData {

	   public String processOutput(RowCache cache, String[] columns, String file) {
		   
		   	int i=0;
	        JSONObject obj = new JSONObject();
	        
	        Set<String> keys = cache.simpleAttr.keySet();
	        Iterator<String> iter = keys.iterator();
	        while(iter.hasNext()) {
			   String key = iter.next();
			   //console(key+" = "+cache.simpleAttr.get(key));
			   if(!"List".equals(key)) {
				   obj.put(key, cache.simpleAttr.get(key));	
			   }
	        }
	        List<Row> rows = cache.getList();
	        Row row = new Row();
	        List<Cell> cells = new ArrayList<Cell>();
	        Cell cell = new Cell();
		    JSONObject jsonRow = new JSONObject();
		    JSONArray table = new JSONArray();
		    JSONObject jsonObj = new JSONObject();
		    
	        Iterator<Row> rowIter = rows.iterator();
	        while(rowIter.hasNext()) {
			   row = (Row) rowIter.next();
			   if(row.isSelected()) {
				   cells = row.getList();
				   Iterator cellIter = cells.iterator();
				   table = new JSONArray();
				   while(cellIter.hasNext()) {
					   cell = new Cell();
					   cell = (Cell) cellIter.next();
					   if(isSelected(cell.getLabel(), columns)) {
						   jsonObj = new JSONObject();
						   jsonObj.put(cell.getLabel(), cell.getValue());
						   table.add(jsonObj);
					   }
				   } //end cell loop
				   jsonRow.put("Row "+row.getNumber(), table);
			   }
			}
	        //update primary object
	        if(cache.simpleAttr.containsValue("List")) {
			    obj.put(cache.simpleAttr.get("List"), jsonRow); 
	        } else {
				obj.put("List", jsonRow); //default object name
	        }
		   
	        try {
		        FileWriter jFile = new FileWriter(file);
	            jFile.write(obj.toJSONString());
	            jFile.flush();
	            jFile.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        } finally {
	        	console("JSON written successfully...\n");
	        	return "OK";
	        }
	   }
		
		public boolean isSelected(String label, String[] columns) {

			if(columns == null) {
				return true;
			}
			
			for(int m=0;m<columns.length;m++) {
				if(label.equals(columns[m])) {
					return true;
				} //end if
			} //end criteria loop

			return false;
		
		}
	   
	   public void console(String sz) {
		   
		   System.out.println(sz);
		   
	   }
}
