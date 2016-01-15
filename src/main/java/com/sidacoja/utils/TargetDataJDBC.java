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
		String sz = "";
    	rowZero = listRows.get(0);

    	console("target url: "+url);
    	console("target table: "+table);
		Connection conn = null;
		PreparedStatement pStmt = null;
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
	        	if(isSelected(cell.getLabel(), columns)) {
	        		if(inProcess) {
	        			createTable = createTable.concat(",\""+cell.getLabel()+"\" "); }
	        		else {
	        			inProcess = true;
	        			createTable = createTable.concat("\""+cell.getLabel()+"\" "); }
	        		if("Double".equals(cell.getDataType())) {
	        			createTable = createTable.concat("Float");} //Double Precision
	        		else {
		        		if("String".equals(cell.getDataType())) {
		        			createTable = createTable.concat("Varchar(254)");}
		        		else {
			        		createTable = createTable.concat(cell.getDataType());}
	        		}
//	        		createTable = createTable.concat(cell.getDataType());
	        	}
	        }
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
        		if(row.isSelected()&& isSelected(cell.getLabel(), columns)) {
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
        			if("VARCHAR".equals(cell.getDataType()) ) {
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
            	//console(sz);
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
