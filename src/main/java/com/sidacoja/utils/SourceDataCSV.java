package com.sidacoja.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class SourceDataCSV implements SourceData {

	@Override
	public RowCache processInput(String file) {
  		
		console(file);
		Reader in = null;
		Iterable<CSVRecord> records = null;
		
		try {
			in = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			records = CSVFormat.EXCEL.parse(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i=0, j=0;
    	List<Row> rows = new ArrayList<Row>();
    	List<Cell> cells = new ArrayList<Cell>();
		List<String> labels= new ArrayList<String>();
		for (CSVRecord record : records) {
			if(labels.size()== 0) {
				for(i=0;i<record.size();i++) {
					labels.add(record.get(i));
				}
			} else {
				cells = new ArrayList<Cell>();
				for(i=0;i<record.size();i++) {
			    	Cell cell = new Cell(i,labels.get(i),record.get(i),"Thing");
			     	cells.add(cell);
				}
			}
			if(cells.size() > 0) {
				Row row = new Row(j++,cells); //I think there are no cells in row zero!
				rows.add(row);
			}
		} //end records
    	    	
    	RowCache cache = new RowCache(rows);
    	console(rows.size()+" rows added");
    	
   		return cache;
	}
	
    public void console(String sz) {
    	System.out.println(sz);
    }
    
} //end class
