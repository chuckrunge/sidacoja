package com.sidacoja.utils;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit tests 
 */
public class AppTest5
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest5 (String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest3.class );
    }

    /**
     * test AND / OR selection against CSV
     * and verify sort on first row
     */
    
    public void testApp01()
    {
    	Sidacoja sdcj = new Sidacoja();
    	sdcj.input("./resources/Names.csv");
    	sdcj.inputType("CSV");
    	
    	sdcj.columns(new String[]{
    			"First Name","Last Name","UserID","Date"
    	});

    	sdcj.sequence(new String[] {
    	    	"Last Name","First Name"
    	});
    	       	
    	sdcj.addFilter(new String[]{"IF","UserID","EQ","U004454"});
    	sdcj.addFilter(new String[]{"AND","First Name","LIKE","%Chuck%"});
    	sdcj.addFilter(new String[]{"OR","First Name","LIKE","%Darius"});
    	sdcj.addFilter(new String[]{"OR","First Name","LIKE","Chris%"});
    	sdcj.addFilter(new String[]{"OR","Date","EQ","11/11/2011"});
    	sdcj.addFilter(new String[]{"IF","Date","EQ","11/14/2015"});
    	
    	sdcj.output("./sidacoja.csv");
    	sdcj.outputType("CSV");
    	
    	RowCache cache = new RowCache();
    	try {

    		cache = sdcj.fire();
    		
    	} catch(Exception e) {
    		console(e.getMessage());
    	}

    	//retrieve first selected row to verify sort sequence
    	List<Row> rows = cache.getList();
    	//console("rows: "+rows.size());
    	for(Row row: rows) {
	    	List<Cell> cells = row.getList();
	    	//console("cells: "+cells.size());
	    	if(row.isSelected()) {
	    		System.out.print("row nbr: "+row.getNumber()+" ");
	    		for(Cell cell: cells) {
	    			System.out.print(cell.getLabel()+", "+cell.getValue()+", ");
	    		}
	    	//}	
	    	console("");
	    	//if(row.isSelected()) {
    	    //	assertTrue("Darius".equals(cells.get(0).getValue() ));  
    	    	//break;
	    	}
    	}
    	
    	//number of data rows equals number of rows in cache
        assertTrue(9 == cache.getList().size() );

        //number of data rows written equals number of rows selected
//        assertTrue(4 == cache.countSelected() );
        
        //number of columns selected for output
        assertTrue(4 == cache.countLabels( sdcj.getColumns()));
        
        //number of columns selected for output
        assertTrue(4 == cache.getLabels(sdcj.getColumns()).length);
        
    }

public void console(String sz) {
	System.out.println(sz);
}

} //end class
