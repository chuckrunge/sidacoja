package com.sidacoja.utils;

public class Cell {
	
	Cell(){}
	
	Cell(int number, String label, String value, String dataType){
		this.number = number;
		this.label = label;
		this.value = value;
		this.dataType = dataType;
	}
	
	private int number;
	private String label;
	private String value;
	private String dataType;
	private boolean selected = false;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Cell [number=" + number + ", label=" + label + ", value=" + value + ", dataType=" + dataType + "]";
	}

}
