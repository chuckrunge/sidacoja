package com.sidacoja.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RowCache {
	
	private Common common = new Common();
	
	public RowCache(List<Row> list) {
		super();
		this.list = list;
	}

	public RowCache(){}
	
	Map<String,String> simpleAttr = new LinkedHashMap<String,String>();

	List<Row> list;

	public List<Row> getList() {
		return list;
	}

	public void setList(List<Row> list) {
		this.list = list;
	}
	
	public String[] getLabels(String[] columns) {
		boolean isColumnsNull = false;
		String[] selected = new String[0];
		if(columns == null) {
			isColumnsNull = true;
		} 
		int i = 0;
		List<Row> listRows = getList();
		if(listRows == null)
			return selected;
		Row row = listRows.get(0);
		List<Cell> listCells = row.getList();
		if(isColumnsNull) {
			selected = new String[listCells.size()];
		} else {
			selected = new String[columns.length];
		}	
		
        for(Cell cell: listCells) {
        	if(isColumnsNull) {
        		selected[i++] = cell.getLabel();
        	} else {
        		if(common.isSelected(cell.getLabel(), columns)) {
        			selected[i++] = cell.getLabel();
        		}
        	}
        }
        
		return selected;
	
	}

	public int countSelected() {
		int i = 0;
		List<Row> listRows = getList();
		if(listRows == null)
			return 0;
        for(Row row: listRows) {
        	if(row.isSelected()) {
        		i++;
        	}
        }
		return i;
	}

	public void display() {
    	List<Row> listRows = getList();
    	console(listRows.size()+" rows in cache");
        for(Row row: listRows) {
        	List<Cell> listCells = row.getList();
        	console(listCells.size()+" cells in cache row "+row.getNumber());
        	for(Cell cell: listCells) {
        		console(row.getNumber()+":"+cell.getNumber()+" "+cell.getLabel()+" "+cell.getValue()+" "+cell.getDataType());
        	}
        }
	}

	public int countLabels(String[] columns) {
		int i = 0;
		List<Row> listRows = getList();
		if(listRows == null)
			return 0;
		List<Cell> listCells = listRows.get(0).getList();
        for(Cell cell: listCells) {
        	if(common.isSelected(cell.getLabel(), columns)) {
        		i++;
        	}
        }
		return i;
	}

	public static void console(String sz) {
		System.out.println(sz);
	}
	
	@Override
	public String toString() {
		return "RowCache [list=" + list.toString() + "]";
	}
	

}
