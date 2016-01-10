package com.sidacoja.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SourceDataJDBC implements SourceData {

	String table = "";
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public RowCache processInput(String url) {
  		
		RowCache cache = new RowCache();
		List<Row> rows = new ArrayList<Row>();
		List<Cell> cells = new ArrayList<Cell>();
		
		console("source url: "+url);
		console("target table: "+table);
		Connection conn = null;
		PreparedStatement stmt = null;

		try{
			// Open a connection
			conn = DriverManager.getConnection(url); //DB_URL,USER,PASS

			// Execute query
			stmt = conn.prepareStatement("SELECT * FROM " +table);

			ResultSet rs = stmt.executeQuery();  //sql
			ResultSetMetaData meta = rs.getMetaData();
			int i=0, j=0;
			Row row = new Row();
			
			// Extract data from result set
			while(rs.next()){
				row = new Row();
				row.setNumber(j);j++;
				cells = new ArrayList<Cell>();
				Cell cell = new Cell();
				for(i=1;i<meta.getColumnCount()+1;i++) {
					cell = new Cell();
					cell.setNumber(i);
					String sz = rs.getString(meta.getColumnLabel(i));
					
					cell.setLabel(meta.getColumnLabel(i));
					cell.setValue(sz);
					if("VARCHAR".equals( meta.getColumnTypeName(i) ) )
						cell.setDataType(meta.getColumnTypeName(i)+"("+meta.getPrecision(i)+")");
					else
						cell.setDataType(meta.getColumnTypeName(i));

					cells.add(cell);
				}

				row.setList(cells);
				rows.add(row);
				
			} //end rowset loop
			
			cache.setList(rows);
			
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			console( "SourceDataJDBC "+se.getMessage() );
			se.printStackTrace();
		}catch(Exception e){
			console( "TargetDataJDBC "+e.getMessage() );
			e.printStackTrace();
		}	 

		console("cache count: "+cache.countSelected());
		return cache;

	}
	
    public void console(String sz) {
    	System.out.println(sz);
    }
    
} //end class
