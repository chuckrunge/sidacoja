package com.sidacoja.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sidacoja {

	Sidacoja(){}
	private static String input;
	private static String inputType;
	private static String[] columns;
	private static String[] sequencers = null;
	private static List<String[]> filters = new ArrayList<String[]>();
	private static String output;
	private static String outputType;
	private static Sidacoja sdcj;
	
	public static Sidacoja getSidacoja() {
		if(sdcj == null) {
			sdcj = new Sidacoja();
		}
		return sdcj;
	}
	
   	public static Sidacoja process(){
   		return getSidacoja();
   	}
   	public static Sidacoja input(String input){
   		Sidacoja.input = input;
   		return getSidacoja();
   	}
   	public static Sidacoja inputType(String inputType){
   		Sidacoja.inputType = inputType;
   		return getSidacoja();
   	}
   	public static Sidacoja columns(String[] columns){
   		Sidacoja.columns = columns;
   		return getSidacoja();
   	}
   	public static Sidacoja sequence(String[] sequencers){
   		Sidacoja.sequencers = sequencers;
   		return getSidacoja();
   	}
   	public static Sidacoja addFilter(String[] criterion){
   		Sidacoja.filters.add(criterion);
   		return getSidacoja();
   	}
   	public static Sidacoja output(String output){
   		Sidacoja.output = output;
   		return getSidacoja();
   	}
   	public static Sidacoja outputType(String outputType){
   		Sidacoja.outputType = outputType;
   		return getSidacoja();
   	}
   	public static Sidacoja fire(){
   		RowCache cache = new RowCache();
   		String status = null;

   		switch(inputType) {
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
   		default:
   			break;
   		}
   		
   		//select and filter - mark row and cells for use
   		if(!filters.isEmpty()) {
   			selectAndFilter(cache, filters);
   		}
   		
   		//sort on selected columns
   		if(sequencers != null) {
   			RowCache sortedCache = sequence(cache, sequencers);
   			cache = sortedCache;
   		}

  		switch(outputType) {
   		case "CSV":
   			TargetDataCSV tdc = new TargetDataCSV(); 
   			status = tdc.processOutput(cache, columns, output);
   			break;
   		case "XLS":
   			TargetDataXLS tdx = new TargetDataXLS(); 
   			status = tdx.processOutput(cache, columns, output);
   			break;
   		case "XML":
   			TargetDataXML tdl = new TargetDataXML(); 
   			status = tdl.processOutput(cache, columns, output);
   			break;
   		case "JSON":
   			TargetDataJSON tdj = new TargetDataJSON(); 
   			status = tdj.processOutput(cache, columns, output);
   			break;
   		default:
   			break;
   		}
   		return getSidacoja();
   	}
   	
    public static RowCache selectAndFilter(RowCache cache, List<String[]> filters) {
    	List<Row> rows = cache.getList();
    	List<Cell> cells = null;
    	for(Row row: rows) {
    		cells = null;
    		cells = row.getList();
    		for(Cell cell: cells) {
		
    			//0= AND/OR //1=column //2=EQ/NOT  //3=VALUE
    			for(int p=0;p<filters.size();p++) {
    				String[] filter = filters.get(p);
    				if(filter[0].equals("OR")){
    					if(cell.getLabel().equals(filter[1])) {
    						//cell label matches filter!
    						if(cell.getValue().equals(filter[3])) {
    							console(cell.getValue()+" equals "+filter[3]);
    							//cell value matches filter
    							cell.setSelected(true);
    							if(filter[2].equals("EQ")){
    								console("filter equals "+filter[2]);
    								//select row
    								row.setSelected(true);
    							} else {
    								console("filter not equals "+filter[2]);
    								//do not select row
    							}
    						} //end if
    					} //end if	
    				} //end if for AND
    			} //end criteria loop
    			int x = cells.lastIndexOf(cell);
    			cells.set(x, cell);
    		} //end cell loop
    		int z = rows.lastIndexOf(row);
    		rows.set(z,  row);
    	} //end row loop
    	cache.setList(rows);
    	return cache;
    }
    
    public static RowCache sequence(RowCache cache, String[] sz) {    	
    	List<Row> rows = cache.getList();
    	StringBuffer sb = new StringBuffer();
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
    		console("sortKey: "+sb.toString());
    		sb.delete(0,sb.length());
    	} //end row loop
    	
    	Collections.sort(rows);
    	cache.setList(rows);
    	
    	return cache;
    }

    public static void displayRowList(List<Row> szList) {

    	for(Row row: szList) {
    		console(row.toString());
    	}
    } 
   	    
    public static void console(String sz) {
    	System.out.println(sz);
    }

	@Override
	public String toString() {
		return "Sidacoja [" +
				"input="+input+","+
				"inputType="+inputType+","+
				"columns="+columns+","+
				"output="+output+","+
				"outputType=" + outputType +
				"]";
	}
		
}
