package com.sidacoja.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.omg.CORBA.DATA_CONVERSION;
import java.lang.Integer;

public class TargetDataXLS implements TargetData {

	public String processOutput(RowCache cache, String[] columns, String output) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet #1");

		Map<String, Object[]> data = new HashMap<String, Object[]>();
		List<com.sidacoja.utils.Row> rows = cache.getList();
		data = loadMap(rows.get(0), data, columns, true); //labels only
		for(com.sidacoja.utils.Row row: rows) {
			data = loadMap(row, data, columns, false); //data rows
		}

		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
		    Row row = sheet.createRow(rownum++);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr) {
		    	if(obj != null) {
		    		Cell cell = row.createCell(cellnum++);
		    		if(obj instanceof Date)
		    			cell.setCellValue((Date)obj);
		    		else if(obj instanceof Boolean)
		    			cell.setCellValue((Boolean)obj);
		    		else if(obj instanceof String)
		    			cell.setCellValue((String)obj);
		    		else if(obj instanceof Double)
		    			cell.setCellValue((Double)obj);
		    	}
		    } //end for
		} //end for
		 
		try {
		    FileOutputStream out =
		            new FileOutputStream(new File(output));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException fnfe) {
		    fnfe.printStackTrace();
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		}

		return "OK";
		
	}
	
	public Map<String, Object[]> loadMap(com.sidacoja.utils.Row row, Map<String, Object[]> data, String[] columns, boolean firstTime) {
		
		int i = 0;
		Integer inty = new Integer(i);
		List<com.sidacoja.utils.Cell> cells = row.getList();
		Object[] obj = new Object[cells.size()];
		
		if(firstTime) {
			for(i=0;i<cells.size();i++) {
				if(isSelected(cells.get(i).getLabel(), columns)) {
					obj[i] = cells.get(i).getLabel();
				}
			}
			inty = data.size();
			data.put(inty.toString(), obj);
		}
		
		if(row.isSelected() && firstTime==false) {
			for(i=0;i<cells.size();i++) {
				if(isSelected(cells.get(i).getLabel(), columns)) {
					obj[i] = cells.get(i).getValue();
				}	
			}
			inty = data.size();
			data.put(inty.toString(),obj);
		}

		return data;
	}
	
	
	public boolean isSelected(String label, String[] columns) {
	
		for(int m=0;m<columns.length;m++) {
			if(label.equals(columns[m])) {
				return true;
			} //end if
		} //end criteria loop

		return false;
	
	}
}
