package com.sidacoja.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TargetDataCSV implements TargetData{

	@Override
	public String processOutput(RowCache cache, String[] columns, String file) {
		String status = null;
   		BufferedWriter bw = null;
   		try {
   			bw = new BufferedWriter(new FileWriter(file));
   		} catch(IOException ioe) {
   			System.out.println("open failed on output: "+file);
   			System.out.println(ioe.getMessage());
   			status = "open failed on output: "+file;
   		}
   		
    	List<Row> listRows = cache.getList();
    	boolean firstIteration = true;
		List<String> labels= new ArrayList<String>();
		List<String> values= new ArrayList<String>();
    	for(Row row: listRows) {
        	List<Cell> listCells = row.getList();
        	//console(listCells.size()+" cells in cache row "+row.getNumber());
        	values = new ArrayList<String>();
        	for(Cell cell: listCells) {
        		
        		if(firstIteration && isSelected(cell.getLabel(), columns)) {
        			labels.add(cell.getLabel());
        		} 
        		if(row.isSelected()&& isSelected(cell.getLabel(), columns)) {
        			values.add(cell.getValue());
        		}
        		
        	} //end cell loop
        	try{
        	if(firstIteration) {
        		for(String label:labels) {
        			if(label.isEmpty() || label  == null) {
        				bw.write("*,");
        			} else {
        				bw.write(label+",");
        			}
        		}
        		System.out.println("");
        		bw.newLine();
        		firstIteration = false;
        	} 
        	if(row.isSelected()) {
            	for(String value:values) {
            		//System.out.print(value+",");
            		bw.write(value+",");
            	}
        		bw.newLine();
        	}
        	} catch(IOException ioe) {
        		System.out.println("write failed on output: "+file);
        		System.out.println(ioe.getMessage());
        		status = "write failed on output: "+file;
        	}
        } //end row loop
    try{	
    	bw.close();
	} catch(IOException ioe) {
		System.out.println("close failed on output: "+file);
		System.out.println(ioe.getMessage());
		status = "close failed on output: "+file;
	}

	console("CSV written successfully...\n");
	return status;
		
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
	
	
    public void console(String sz) {
    	System.out.println(sz);
    }
	
} //end class
