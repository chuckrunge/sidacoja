package com.sidacoja.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;

public class TargetDataXLS implements TargetData {

	@SuppressWarnings("deprecation")
	public String processOutput(RowCache cache, String[] columns, String output) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		HSSFSheet sheet = workbook.createSheet("Sheet #1");

		Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
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
		    		if(isBoolean((String) obj)) {
		    			cell.setCellValue(Boolean.valueOf((String) obj));
		    		}
		    		else if(isNumber((String) obj)) {
		    			cell.setCellValue(Double.valueOf((String) obj));
		    		}
		    		else if(isDate((String) obj)) {
		    			CellStyle cellStyle = workbook.createCellStyle();
		    			cellStyle.setDataFormat(
		    			createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		    			cell = row.createCell(1);
		    			Calendar cal = setCalendar(obj);
		    			cell.setCellValue(cal.getTime());
		    			cell.setCellStyle(cellStyle);
		    			console("date value: "+cal.toString());
		    		}
		    		else
		    			cell.setCellValue((String)obj);

		    	}
		    } //end for
		} //end for
		 
		try {
		    FileOutputStream out =
		            new FileOutputStream(new File(output));
		    workbook.write(out);
		    out.close();
		    System.out.println("XLS written successfully...\n");
		     
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

    
    public boolean isDate(String sz) {
        if(sz.isEmpty()) {
            return false;
        }
        int slash1 = sz.indexOf('/');
        int slash2 = sz.indexOf(slash1+1,'/');
        
        if(slash2 == -1) {
        	return false;
        }
        return true;
    }
    
    public boolean isBoolean(String sz) {
        if(sz.isEmpty()) {
            return false;
        }
        if("false".equals(sz)) {
        	return true;
        }
        if("true".equals(sz)) {
        	return true;
        }
        return false;
    }

    public boolean isNumber(String sz) {
        
        if(sz.isEmpty()) {
            return false;
        }

        char [] charNumber = sz.toCharArray();
        for(int i =0 ; i<charNumber.length ;i++) {

        	char c = charNumber[i];
        	switch (c) {
        	case '.':
        	case '0':
        	case '1':
        	case '2':
        	case '3':
        	case '4':
        	case '5':
        	case '6':
        	case '7':
        	case '8':
        	case '9':
        		//OK;
        		break;
        	default:
        		return false;
        };
        
        };
        
        return true;
    
    }

    public Calendar setCalendar(Object obj) {
    	
    	String szObj = (String) obj;
    	console("setCalendar: "+obj);
    	int slash1 = szObj.indexOf('/');
    	int slash2 = szObj.indexOf(slash1+1, '/');

    	int year  = Integer.parseInt(szObj.substring( slash2 ));
    	console("year: "+year);
		int month = Integer.parseInt(szObj.substring( 0, slash1 ));
		console("month: "+month);
		int day  = Integer.parseInt(szObj.substring( slash1, slash2 ));
		console("day: "+day);
		
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, day);
		
		return cal;
		
    }
}