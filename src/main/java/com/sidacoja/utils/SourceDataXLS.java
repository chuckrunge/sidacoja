package com.sidacoja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SourceDataXLS implements SourceData {

	public RowCache processInput(String input) {
		
		console(input);
		int i=0, j=0;
		boolean firstRow = true;
	    RowCache cache = new RowCache();
		
		try {
		     
		    FileInputStream file = new FileInputStream(new File(input));
		     
		    HSSFWorkbook workbook = new HSSFWorkbook(file); //create workbook instance
		    HSSFSheet sheet = workbook.getSheetAt(0); //select first worksheet

	    	List<com.sidacoja.utils.Row> rows = new ArrayList<com.sidacoja.utils.Row>();
	    	List<com.sidacoja.utils.Cell> cells = new ArrayList<com.sidacoja.utils.Cell>();
			List<String> labels= new ArrayList<String>();
            com.sidacoja.utils.Row sRow = new com.sidacoja.utils.Row();
			com.sidacoja.utils.Cell sCell = new com.sidacoja.utils.Cell();

		    Iterator<Row> rowIterator = sheet.iterator(); //Iterate through rows
		    while(rowIterator.hasNext()) {
		    	
		        Row row = rowIterator.next();
		        cells = new ArrayList<com.sidacoja.utils.Cell>();
		        i=0;
		        if(j % 1000 == 0) {
		        	console("row: "+j); //" cell "+i
		        }
		        Iterator<Cell> cellIterator = row.cellIterator(); //iterate through columns 
	            
	            while(cellIterator.hasNext()) {
		             
		            Cell cell = cellIterator.next();
		            if(firstRow == false) {
		            	sCell.setNumber(i);
		            	sCell.setLabel(labels.get(i));
		            	i = i + 1;
		            }
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                    //System.out.print(cell.getBooleanCellValue() + "\t\t");	                    
		                    sCell.setValue(Boolean.toString(cell.getBooleanCellValue()));
		                    sCell.setDataType("Boolean");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    //System.out.print(cell.getNumericCellValue() + "\t\t");
		                    sCell.setValue(Double.toString(cell.getNumericCellValue()));
		                    sCell.setDataType("Double");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                    //System.out.print(cell.getStringCellValue() + "\t\t");
		                    if(firstRow) {
		                    	labels.add(cell.getStringCellValue());
		                    } else {
		                    	sCell.setValue(cell.getStringCellValue());
		                    	sCell.setDataType("String");
		                    }
		                    break;
		            } //end switch
		            if(firstRow == false) {
		            	//console("");
		            	cells.add(sCell);
		            	sCell = new com.sidacoja.utils.Cell();
		            	//displayCells(cells);
		            }
		        } //end cell loop
	            //System.out.println("");
	        	//displayCells(cells);
	        	i=0;
	            if(firstRow == false) {
	            	sRow = new com.sidacoja.utils.Row();
	            	sRow.setNumber(j);
	            	sRow.setList(cells);
	            	rows.add(sRow);
	            	cells = new ArrayList<com.sidacoja.utils.Cell>();
	            	j = j + 1;
	            }
	            else {firstRow = false;}
	            
		    } //end row loop
		    file.close();
		    cache.setList(rows);
		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}

		return cache;
		
	}
	
	public void displayCells(List<com.sidacoja.utils.Cell> cells) {
		for(com.sidacoja.utils.Cell cell:cells) {
			console(cell.toString());
		}
		//for(int m=0;m<cells.size();m++) {
		//	console(cells.get(m).toString());
		//}
	}
	
    public void console(String sz) {
    	System.out.println(sz);
    }

} //end class
