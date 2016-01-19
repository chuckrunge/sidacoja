package com.sidacoja.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TargetDataJDBC  implements TargetData {
	
	private String table = "";
	private Common common = new Common();

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public String processOutput(RowCache cache, String[] columns, String url) {
		
    	List<Row> listRows = cache.getList();
    	Row rowZero = new Row();
		List<String> values= new ArrayList<String>();
		String sz = "";
    	rowZero = listRows.get(0);

    	console("target url: "+url);
    	console("target table: "+table);
		Connection conn = null;
		Statement stmt = null;

		try{
			// Open a connection
			conn = DriverManager.getConnection(url); //DB_URL,USER,PASS

			try{
			// drop table / create table
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE " +table); //+ " IF EXISTS");
			} catch(Exception dte) {
				//don't care!
			}
			
	        List<Cell> listCells = rowZero.getList();
	        boolean inProcess = false;
	        String createTable = "CREATE TABLE "+table+" (";
	        console("createTable for "+listCells.size()+" columns");
	        for(Cell cell: listCells) {
	        	if(common.isSelected(cell.getLabel(), columns)) {
	        		if(inProcess) {
	        			createTable = createTable.concat(",\""+cell.getLabel()+"\" "); }
	        		else {
	        			inProcess = true;
	        			createTable = createTable.concat("\""+cell.getLabel()+"\" "); }
	        		if("Double".equals(cell.getDataType())) {
	        			createTable = createTable.concat("Decimal(17,2)");} //Float? Double Precision?
	        		else {
		        		if("String".equals(cell.getDataType())) {
		        			createTable = createTable.concat("Varchar(254)");}
		        		else {
			        		createTable = createTable.concat(cell.getDataType());}
	        		}
//	        		createTable = createTable.concat(cell.getDataType());
	        	} //end if
	        } //end cell loop
	        createTable = createTable.concat(")");
	        console(createTable);
			stmt.executeUpdate(createTable);
			
		//==============================================================================
		int j=0;
    	for(Row row: listRows) {
	        
	        if(j % 1000 == 0) {
	        	console("row: "+j); //" cell "+i
	        }
	        j++;
        	values = new ArrayList<String>();
        	listCells = row.getList();
        	for(Cell cell: listCells) {
        		if(row.isSelected()&& common.isSelected(cell.getLabel(), columns)) {
        			//console("TargetDataJDBC "+cell.getDataType()+" = "+cell.getValue());
        			if("INTEGER".equals( cell.getDataType() ) ) {
        				values.add(cell.getValue());
        			}
        			if("Double".equals( cell.getDataType() ) ) {
        				values.add(cell.getValue());
        			}
        			if("DATE".equals( cell.getDataType() ) ) {
        				values.add("'"+cell.getValue()+"'");
        			}
        			if("Date".equals( cell.getDataType() ) ) {
        				values.add("'"+cell.getValue()+"'");
        			}
        			if(cell.getDataType().contains("VARCHAR")) {
        				values.add("'"+cell.getValue()+"'");
        			}
        			if("String".equals(cell.getDataType()) ) {
        				if(cell.getValue().length()==0) {
        					//console("TargetDataJDBC found empty cell");
        					values.add("'.'");
        				} else {
        					values.add("'"+cell.getValue()+"'");
        				}
        			}
        		}
        		
        	} //end cell loop

        	if(row.isSelected()) {
        		sz = "";
        		sz = sz.concat("INSERT INTO "+table+" VALUES(");
        		int i=1;
            	for(String value:values) {
            		if(i==values.size())
            			sz = sz.concat(value);
            		else
            			sz = sz.concat(value+",");
            		i++;
            	}
            	sz = sz.concat(");");
    			stmt.executeUpdate(sz);
        		
        	}

    	} //end row loop
    	
    	stmt.close();
    	conn.close();
    	
		}catch(SQLException sql){
			console(sz);
			console( "TargetDataJDBC "+sql.getMessage() );
			sql.printStackTrace();
		}
    	
		return "OK";
	   
	}
     	       	
    public void console(String sz) {
    	System.out.println(sz);
    }

}
