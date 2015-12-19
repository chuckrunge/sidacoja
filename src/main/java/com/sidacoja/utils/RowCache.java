package com.sidacoja.utils;

import java.util.List;

public class RowCache {
	
	public RowCache(List<Row> list) {
		super();
		this.list = list;
	}

	RowCache(){}
	
	List<Row> list;

	public List<Row> getList() {
		return list;
	}

	public void setList(List<Row> list) {
		this.list = list;
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
	
	public static void console(String sz) {
		System.out.println(sz);
	}
	
	@Override
	public String toString() {
		return "RowCache [list=" + list.toString() + "]";
	}
	

}
