package com.sidacoja.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TargetDataJDBC  implements TargetData {
	
	private String table = "";

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
    	boolean firstIteration = true;
		List<String> labels= new ArrayList<String>();
		List<String> values= new ArrayList<String>();
    	rowZero = listRows.get(0);

    	console("target url: "+url);
    	console("target table: "+table);
		Connection conn = null;
		PreparedStatement pStmt = null;
		Statement stmt = null;

		try{
			// Open a connection
			conn = DriverManager.getConnection(url); //DB_URL,USER,PASS

			// drop table / create table
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE " +table + " IF EXISTS");
			
	        List<Cell> listCells = rowZero.getList();
	        String createTable = "CREATE TABLE "+table+" (";
	        console("createTable for "+listCells.size()+" columns");
	        for(Cell cell: listCells) {
	        	if(isSelected(cell.getLabel(), columns)) {
	        		createTable = createTable.concat(cell.getLabel()+" ");
	        		createTable = createTable.concat(cell.getDataType()+", ");
	        	}
	        }
	        createTable = createTable.concat(")");
	        console(createTable);
			stmt.executeUpdate(createTable);
			
		//==============================================================================
		
    	for(Row row: listRows) {
        	values = new ArrayList<String>();
        	listCells = row.getList();
        	for(Cell cell: listCells) {
        		if(row.isSelected()&& isSelected(cell.getLabel(), columns)) {
        			if("INTEGER".equals( cell.getDataType() ) ) {
        				values.add(cell.getValue());
        			}
        			if("DATE".equals( cell.getDataType() ) ) {
        				values.add("'"+cell.getValue()+"'");
        			}
        			if(cell.getDataType().contains("VARCHAR") ) {
        				values.add("'"+cell.getValue()+"'");
        			}
        		}
        		
        	} //end cell loop

        	if(row.isSelected()) {
        		String sz = "";
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
            	console(sz);
    			stmt.executeUpdate(sz);
        		
        	}

    	} //end row loop
		}catch(SQLException sql){
			console( "TargetDataJDBC "+sql.getMessage() );
			sql.printStackTrace();
		}
    	
		return "OK";
	   
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

}
