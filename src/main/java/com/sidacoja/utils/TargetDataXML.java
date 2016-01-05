package com.sidacoja.utils;

import java.io.File;
import java.util.List;
import java.lang.Integer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TargetDataXML {

	   public String processOutput(RowCache cache, String[] columns, String file) {
		   
		   console(file);
		   List<Row> rows = cache.getList();
		   
			  try {
				  
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			 
					// root elements
					Document doc = docBuilder.newDocument();
					String rootName = cache.simpleAttr.get("rootElement");
					Element rootElement;
					if(rootName == null) {
						rootElement = doc.createElement("RootElement");
					} else {
						rootElement = doc.createElement(rootName);
						rootElement.setAttribute("Name", rootName);
					}
					doc.appendChild(rootElement);
					int i=0;

					for(Row row:rows) {
						if(row.isSelected()) {
							List<Cell> cells = row.getList();
							Element rowElement = doc.createElement("Row");
							rootElement.appendChild(rowElement);
						 
							// set attribute on element
							Attr attr = doc.createAttribute("rowId");
							attr.setValue(i+"");
							i++;
							rowElement.setAttributeNode(attr);
						
							for(Cell cell: cells) {
								if(isSelected(cell.getLabel(), columns)) {
									String label = cell.getLabel().replaceAll(" ","_");
									Element cellElement = doc.createElement(cell.getLabel().replaceAll(" ","_"));
									cellElement.appendChild(doc.createTextNode(cell.getValue()));
									rowElement.appendChild(cellElement);
								}
							} //end cell loop
						}
					}
					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(file));
			 
					// Output to console for testing
					// StreamResult result = new StreamResult(System.out);
			 
					transformer.transform(source, result);
			 
					console("XML written successfully...\n");
			 
				  } catch (ParserConfigurationException pce) {
					pce.printStackTrace();
				  } catch (TransformerException tfe) {
					tfe.printStackTrace();
				  }
	   
	   return "OK";
		   
	   }

	public boolean isSelected(String label, String[] columns) {

		if(columns == null) {
			return true;
		}
		
		for(int m=0;m<columns.length;m++) {
			if(label.equals(columns[m])) {
				return true;
			} //end if
		} //end criteria loop

		return false;
		
	}
   
   public static void console(String sz) {
   	System.out.println(sz);
   }

}
