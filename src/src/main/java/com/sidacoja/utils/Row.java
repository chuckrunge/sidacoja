package com.sidacoja.utils;

import java.util.List;

public class Row implements Comparable<Row> {
	
	public Row(){}

	public Row(int number, List<Cell> list) {
		super();
		this.number = number;
		this.list = list;
	}

	private int number;
	private List<Cell> list; //= new ArrayList();
	private boolean selected = false;
	private String sortKey = ""; 
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Cell> getList() {
		return list;
	}

	public void setList(List<Cell> list) {
		this.list = list;
	};
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getSortKey() {
		
		StringBuffer newKey = new StringBuffer();
		if(sortKey.isEmpty()) {
			List<Cell> cellList = this.getList();
			for(Cell cell: cellList) {
				newKey.append(cell.getValue());
			}
			setSortKey(newKey.toString()); 
		}
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public int compareTo(Row row) {
		return this.getSortKey().
				compareTo(
						row.getSortKey());			
	}

	@Override
	public String toString() {
		return "Row [number=" + number + ", list=" + list.toString() + "]";
	}

}
