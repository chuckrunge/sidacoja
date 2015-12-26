package com.sidacoja.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceDataXML implements SourceData {

	public RowCache processInput(String input) {
		
		//process selected rows
		RowCache cache = new RowCache();
		List<Row> rows = new ArrayList<Row>();
		List<Cell> cells = new ArrayList<Cell>();
		Cell cell = new Cell();
		Row row = new Row();
		
		  try {
			  
				File fXmlFile = new File(input);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				
				Element element = doc.getDocumentElement();
				console("Root element :" + element.getNodeName());
				cache.simpleAttr.put("rootElement", element.getNodeName());
				NodeList nList = element.getChildNodes();
		 
				for (int i = 0; i < nList.getLength(); i++) {

				   Node rNode = nList.item(i);
				   row = new Row();
				   if(rNode.getNodeType() ==1) {
					   console("NodeName:"+rNode.getNodeName()+" type: "+rNode.getNodeType());
					   cells = new ArrayList<Cell>();
					   NodeList cList = rNode.getChildNodes();
					   
					   for(int j=0;j<cList.getLength();j++) {
						   Node col = cList.item(j);
						   cell = new Cell();
						   if(col.getNodeType()==1) {
							   console(col.getNodeName()+" "+col.getTextContent().trim());
							   cell.setLabel(col.getNodeName());
							   cell.setValue(col.getTextContent().trim());
							   cell.setNumber(j);
							   cells.add(cell);
						   }
					   } //end cell loop
					   row.setList(cells);
					   row.setNumber(i);
					   rows.add(row);
				   } //end row loop
				   
				   cache.setList(rows);
				   
				}
				
			  } catch (Exception e) {
				e.printStackTrace();
			  }
			
			return cache;
			
		} //end processInput
		 
		public static void console(String sz) {
			System.out.println(sz);
		}
		   
} //end class
