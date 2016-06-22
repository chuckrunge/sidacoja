package com.sidacoja.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
		   
		   String xmlReq = null;
		   List<Row> rows = cache.getList();
		   Common common = new Common();
		   
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
								if(common.isSelected(cell.getLabel(), columns)) {
									//String label = cell.getLabel().replaceAll(" ","_");
									Element cellElement = doc.createElement(cell.getLabel().replaceAll(" ","_"));
									cellElement.appendChild(doc.createTextNode(cell.getValue()));
									rowElement.appendChild(cellElement);
								}
							} //end cell loop
						}
					}
					// write the content into xml file
					File outFile = new File(file);
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					if(outFile.exists()) { 
						console("output: "+file);
						StreamResult result = new StreamResult(outFile); //new File(file)
						transformer.transform(source, result);
					} else {
						boolean fileOK = outFile.createNewFile();
						if(!fileOK) console("No XML file "+file+" was created");
						else {
							console("output: "+file);
							StreamResult result = new StreamResult(outFile); //new File(file)
							transformer.transform(source, result);							
						}
					}
			 
					// Output to string
				    OutputStream out = new ByteArrayOutputStream();
				    StreamResult streamResult = new StreamResult();
				    streamResult.setOutputStream(out);
				    transformer.transform(source, streamResult);
				    xmlReq = streamResult.getOutputStream().toString();					
					console("XML written successfully...\n");
			 
				  } catch (ParserConfigurationException pce) {
					  pce.printStackTrace();
				  } catch (TransformerException tfe) {
					  tfe.printStackTrace();
				  } catch (IOException ioe) {
					  ioe.printStackTrace();
				  }
	   
	   return xmlReq;
		   
	   }

   public static void console(String sz) {
   	System.out.println(sz);
   }

}
