//sidacoja - A Simple Data Conversion for Java
//Copyright (C) 2015  Chuck Runge
//Lombard, IL.
//CGRunge001@GMail.com

//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either version 2
//of the License, or (at your option) any later version.

//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

package com.sidacoja.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sidacoja {

	Sidacoja(){}
	private String input;
	private String inputType;
	private String[] columns = null;
	private String[] sequencers = null;
	private List<String[]> filters = new ArrayList<String[]>();
	private boolean cacheOnly = false;
	private String output;
	private String outputType;
	private String table;
	private String outputTable;
	private Sidacoja sdcj;
	
	public Sidacoja getSidacoja() {
		if(sdcj == null) {
			sdcj = new Sidacoja();
		}
		return sdcj;
	}
	
   	public Sidacoja process(){
   		return getSidacoja();
   	}
   	public Sidacoja input(String input){
   		this.input = input;
   		return getSidacoja();
   	}
   	public Sidacoja inputType(String inputType){
   		this.inputType = inputType;
   		return getSidacoja();
   	}
   	public Sidacoja columns(String[] columns){
   		this.columns = columns;
   		return getSidacoja();
   	}
   	public Sidacoja sequence(String[] sequencers){
   		this.sequencers = sequencers;
   		return getSidacoja();
   	}
   	public Sidacoja addFilter(String[] criterion){
   		this.filters.add(criterion);
   		return getSidacoja();
   	}
   	public Sidacoja output(String output){
   		this.output = output;
   		return getSidacoja();
   	}
   	public Sidacoja outputType(String outputType){
   		this.outputType = outputType;
   		return getSidacoja();
   	}
   	public String[] getColumns(){
   		return this.columns;
   	}
   	public boolean isCacheOnly() {
		return cacheOnly;
	}

	public void setCacheOnly(boolean cacheOnly) {
		this.cacheOnly = cacheOnly;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getOutputTable() {
		return outputTable;
	}

	public void setOutputTable(String outputTable) {
		this.outputTable = outputTable;
	}

	public RowCache fire() throws Exception{
 
		//perform simple validation with error messages
   		if(isNullOrEmpty(input)) throw new Exception("Input file is required.");
   		if(isNullOrEmpty(inputType)) throw new Exception("Input Type is required.");
   		if(!isCacheOnly()) {
   			if(isNullOrEmpty(output)) throw new Exception("Output file is required.");
   			if(isNullOrEmpty(outputType)) throw new Exception("Output Type is required.");
   		}
   		if("JDBC".equals(inputType)) {
   			if(isNullOrEmpty(table)) {
   				throw new Exception("JDBC requires table name");
   			}
   		}
   		if( !(columns == null || sequencers == null) ) {   			
   			if(columns.length>0 & sequencers.length>0) {
   				for(String sequencer:sequencers) {
   					if(!isSelected(sequencer, columns)) {
   						throw new Exception("sequencer "+sequencer+" is not a selected column");   					
   					}
   				}
   			}
   		}
   		if( !(filters.size()>0 || columns == null) ) {   			
   			if(columns.length>0) {
   				for(String[] filter: filters) {
   					if(!isSelected(filter[1], columns)) {
   						throw new Exception("filter for "+filter[1]+" is not a selected column");   					
   					}
   				}
   			}
   		}
   		
   		if(filters.size()>0) {
   			for(String[] filter: filters) {
   				if(!("AND".equals(filter[0]) || "OR".equals(filter[0])) ) {
   					throw new Exception(filter[0]+" is not valid in filter. First value must be AND or OR");
   				}
   				if(!("EQ".equals(filter[2]) || "NE".equals(filter[2])) ) {
   					throw new Exception(filter[2]+" is not valid in filter. Third value must be EQ or NE");
   				}
   			}
   			
   		}
   		RowCache cache = new RowCache();
   		String status = null;

   		switch( inputType.toUpperCase().trim() ) {
   		case "CSV":
   			SourceDataCSV sdc = new SourceDataCSV();
   			cache = sdc.processInput(input);
   			break;
   		case "XLS":
   			SourceDataXLS sdx = new SourceDataXLS();
   			cache = sdx.processInput(input);
   			//cache.display();
   			break;
   		case "XML":
   			SourceDataXML sdl = new SourceDataXML();
   			cache = sdl.processInput(input);
   			//cache.display();
   			break;
   		case "JSON":
   			SourceDataJSON sdj = new SourceDataJSON();
   			cache = sdj.processInput(input);
   			//cache.display();
   			break;
   		case "JDBC":
   			SourceDataJDBC sjdb = new SourceDataJDBC();
   			sjdb.setTable(table);
   			cache = sjdb.processInput(input);
   			//cache.display();
   			break;
   		default:
   			throw new Exception("Input Type "+inputType+" is not supported.");
   		}
   		
   		//select and filter - mark row and cells for use
   		if(!filters.isEmpty()) {
   			cache = selectAndFilter(cache, filters);
   		}
   		
   		//sort on selected columns
   		if(sequencers != null) {
   			cache = sequence(cache, sequencers);
   		}

   		if(isCacheOnly()) {
   			return cache;
   		}

  		switch(outputType.toUpperCase()) {
   		case "CSV":
   			TargetDataCSV tdc = new TargetDataCSV(); 
   			status = tdc.processOutput(cache, columns, output);
   			break;
   		case "XLS":
   			TargetDataXLS tdx = new TargetDataXLS(); 
   			status = tdx.processOutput(cache, columns, output);
   			console("TargetDataXLS Status: "+ status);
   			break;
   		case "XML":
   			TargetDataXML tdl = new TargetDataXML(); 
   			status = tdl.processOutput(cache, columns, output);
   			break;
   		case "JSON":
   			TargetDataJSON tdj = new TargetDataJSON(); 
   			status = tdj.processOutput(cache, columns, output);
   			break;
   		case "JDBC":
   			TargetDataJDBC sdb = new TargetDataJDBC();
   			if(outputTable == null | outputTable.isEmpty()) {
   				outputTable = table;
   			}
   			sdb.setTable(outputTable);
   			status = sdb.processOutput(cache, columns, output);
   			//cache.display();
   			break;
   		default:
   			throw new Exception("Output Type "+outputType+" is not supported.");
   		}
  		
  		if(cache.getList() == null)
  			console("cachelist is null after targetdata?!");
   		console("output rows: "+cache.getList().size()); 		
   		return cache;
   		
   	}
   	
    public RowCache selectAndFilter(RowCache cache, List<String[]> filters) {
    	List<Row> rows = cache.getList();
    	List<Cell> cells = null;
    	boolean rowWasSelected = false;
    	
    	for(Row row: rows) {
    		row.setSelected(false);
        	rowWasSelected = false;
    		cells = null;
    		cells = row.getList();
    		
			//0= AND/OR //1=column //2=EQ/NOT  //3=VALUE
			for(int p=0;p<filters.size();p++) {
				String[] filter = filters.get(p);

				for(Cell cell: cells) {
       			
    				if(cell.getLabel().equals(filter[1])) {
    					//cell label matches filter!
    					if(cell.getValue().equals(filter[3])) {
    						//cell value matches filter
    						cell.setSelected(true);
    						if(filter[2].equals("EQ")){
     							//select row
    							if(filter[0].equals("OR") ){
    								row.setSelected(true);
    								rowWasSelected = row.isSelected();
    							}
    							if(filter[0].equals("AND") ){
    								if(rowWasSelected) {
    									row.setSelected(true);
    									rowWasSelected = row.isSelected();
    								}
    							}
    							//console("dark hole #1");
    						}
							//cell value matches;
    						if(filter[2].equals("NE")){
    							if( (filter[0].equals("OR")) || (filter[0].equals("AND") && rowWasSelected) ){
    								row.setSelected(false);
    							}
    							rowWasSelected = row.isSelected();
    						}
    					} else {
    						//cell value does not match
    						if(filter[2].equals("NE")){
    							if( (filter[0].equals("OR")) || (filter[0].equals("AND") && rowWasSelected) ){
    								row.setSelected(true);
    							}
    							rowWasSelected = row.isSelected();
    						}
    						rowWasSelected = row.isSelected();
    					}
    				} //end if
    				
				int x = cells.lastIndexOf(cell);
    			cells.set(x, cell);
    		} //end cell loop
		} //end filter loop (?)    			
    					
    		int z = rows.lastIndexOf(row);
    		rows.set(z,  row);
    		//console("row "+row.getNumber()+" was selected "+row.isSelected());
    	} //end row loop
    	
    	cache.setList(rows);
    	return cache;
    }
    
    public RowCache sequence(RowCache cache, String[] sz) {    	
    	List<Row> rows = cache.getList();
    	StringBuffer sb = new StringBuffer();
    	int index = 0;
    	for(Row row: rows) {
    		List<Cell> cells = row.getList();
   			for(String key: sequencers) {
   	    		for(Cell cell: cells) {
    				if(cell.getLabel().equals(key)) {
    					sb.append(cell.getValue());
    				}
    			} //end cell loop
    		} //end sequencer loop
    		row.setSortKey(sb.toString());
    		index = rows.indexOf(row);
    		rows.set(index,row);
    		sb.delete(0,sb.length());
    	} //end row loop
    	
    	Collections.sort(rows);
    	cache.setList(rows);
    	
    	return cache;
    }

    public void displayRowList(List<Row> szList) {

    	for(Row row: szList) {
    		console(row.toString());
    	}
    } 
   	    
    public void console(String sz) {
    	System.out.println(sz);
    }

	@Override
	public String toString() {
		return "Sidacoja [" +
				"input="+input+","+
				"inputType="+inputType+","+
				"columns="+columns+","+
				"filters="+filters+","+
				"output="+output+","+
				"outputType=" + outputType +
				"]";
	}

	public int countLabels(RowCache cache) {
		int i = 0;
		List<Row> listRows = cache.getList();
		if(listRows == null)
			return 0;
		List<Cell> listCells = listRows.get(0).getList();
        for(Cell cell: listCells) {
        	if(isSelected(cell.getLabel(), columns)) {
        		i++;
        	}
        }
		return i;
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
		
}
