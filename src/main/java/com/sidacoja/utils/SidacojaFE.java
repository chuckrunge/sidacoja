	package com.sidacoja.utils;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;  //showMessageDialog()
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;

public class SidacojaFE implements ActionListener {

		private JFrame frame = new JFrame();
		private JTextField txtInputFileOr; //input
		JComboBox<String> comboBox = new JComboBox<String>();
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		JComboBox<String> comboBox_6 = new JComboBox<String>();
		JComboBox<String> comboBox_7 = new JComboBox<String>();
		JComboBox<String> comboBox_8 = new JComboBox<String>();
		JComboBox<String> comboBox_9 = new JComboBox<String>();
		JComboBox<String> comboBox_10 = new JComboBox<String>();
		JComboBox<String> comboBox_11 = new JComboBox<String>();
		JComboBox<String> comboBox_12 = new JComboBox<String>();
		JComboBox<String> comboBox_13 = new JComboBox<String>();
		JComboBox<String> comboBox_14 = new JComboBox<String>();

		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField_4;
		private JTextField textField_5;
		private JTextField textField_6;
		private JTextField textField_7;
		private JTextField textField_8;
		private JTextField textField_9;
		private JTextArea textArea = new JTextArea();
		private JButton btnGo = new JButton("GO!");
		private JButton btnCancel = new JButton("Cancel");
		private JButton btnAdd = new JButton("Add");
		private JButton btnAdd_1 = new JButton("Add");
		private JButton btnClear = new JButton("Clear");
		private JButton btnClear_1 = new JButton("Clear");
		private JButton btnReadColumns = new JButton("Read Columns");
		private final JButton btnFileLocater = new JButton("Select File");
		private final JButton btnFileLocater_1 = new JButton("Select File");
		private ArrayList<String> columnArray = new ArrayList<String>();
		private Common common = new Common();

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SidacojaFE window = new SidacojaFE();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public SidacojaFE() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			//frame = new JFrame();
			frame.setBounds(100, 100, 640, 428);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setTitle("SIDACOJA File Conversion and Extract");
			
			JLabel lblNewLabel = new JLabel("Input: ");
			lblNewLabel.setBounds(28, 40, 86, 23);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Type:");
			lblNewLabel_1.setBounds(28, 74, 46, 14);
			frame.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("TableName: ");
			lblNewLabel_2.setBounds(185, 74, 72, 14);
			frame.getContentPane().add(lblNewLabel_2);
			
			txtInputFileOr = new JTextField(); //file or url
			txtInputFileOr.setText("jdbc:hsqldb:hsql://localhost/mdb"); //"input file or database url");
			txtInputFileOr.addActionListener(this);
			txtInputFileOr.setBounds(68, 41, 430, 20);
			frame.getContentPane().add(txtInputFileOr);
			txtInputFileOr.setColumns(10);
			
			//JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItem("XLS");
			comboBox.addItem("CSV");
			comboBox.addItem("JSON");
			comboBox.addItem("XML");
			comboBox.addItem("JDBC");
			comboBox.setBounds(68, 71, 86, 23);
			frame.getContentPane().add(comboBox);
			
			textField_1 = new JTextField();
			textField_1.setText("for jdbc only");
			textField_1.addActionListener(this);
			textField_1.setBounds(265, 71, 114, 20);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Output: ");
			lblNewLabel_3.setBounds(28, 111, 46, 14);
			frame.getContentPane().add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Type: ");
			lblNewLabel_4.setBounds(28, 136, 46, 14);
			frame.getContentPane().add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("TableName: ");
			lblNewLabel_5.setBounds(185, 136, 72, 14);
			frame.getContentPane().add(lblNewLabel_5);
			
			textField_2 = new JTextField("C:\\users\\chuck\\java\\sidacoja\\sidacoja.xml");
			textField_2.addActionListener(this);
			textField_2.setBounds(84, 108, 417, 20);
			frame.getContentPane().add(textField_2);
			textField_2.setColumns(10);
			
			//JComboBox<String> comboBox_1 = new JComboBox<String>();
			comboBox_1.addItem("XLS");
			comboBox_1.addItem("CSV");
			comboBox_1.addItem("JSON");
			comboBox_1.addItem("XML");
			comboBox_1.addItem("JDBC");
			comboBox_1.setBounds(68, 133, 86, 20);
			frame.getContentPane().add(comboBox_1);
			
			textField_3 = new JTextField();
			textField_3.setText("for jdbc only");
			textField_3.addActionListener(this);
			textField_3.setBounds(265, 133, 114, 20);
			frame.getContentPane().add(textField_3);
			textField_3.setColumns(10);
			
			//JButton btnGo = new JButton("GO!");
			btnGo.addActionListener(this);
			btnGo.setBounds(525, 283, 89, 23);
			frame.getContentPane().add(btnGo);
			
			//JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(this);
			btnCancel.setBounds(525, 317, 89, 23);
			frame.getContentPane().add(btnCancel);
			
			JLabel lblColumns = new JLabel("Columns:");
			lblColumns.setBounds(28, 180, 72, 14);
			frame.getContentPane().add(lblColumns);
			
			JLabel lblSequencers = new JLabel("Sequencers:");
			lblSequencers.setBounds(28, 212, 72, 14);
			frame.getContentPane().add(lblSequencers);
			
			JLabel lblFilters = new JLabel("Filters:");
			lblFilters.setBounds(28, 244, 46, 14);
			frame.getContentPane().add(lblFilters);
			
			//filter line #1
//			JComboBox<String> comboBox_2 = new JComboBox<String>();
			comboBox_2.addItem("IF");
			comboBox_2.addItem("OR");
			comboBox_2.addItem("AND");
			comboBox_2.setBounds(68, 241, 86, 20);
			frame.getContentPane().add(comboBox_2);
			
			//JComboBox<String> comboBox_3 = new JComboBox<String>(); //select column
			//comboBox_3.addItem("columns");
			comboBox_3.setBounds(167, 241, 86, 20);
			frame.getContentPane().add(comboBox_3);
			
//			JComboBox<String> comboBox_4 = new JComboBox<String>();
			comboBox_4.addItem("EQ");
			comboBox_4.addItem("NE");
			comboBox_4.addItem("LIKE");
			comboBox_4.addItem("NOTLIKE");
			comboBox_4.setBounds(263, 241, 72, 20);
			frame.getContentPane().add(comboBox_4);
			
			textField_4 = new JTextField();
			textField_4.addActionListener(this);
			textField_4.setBounds(356, 241, 145, 20);
			frame.getContentPane().add(textField_4);
			textField_4.setColumns(10);
			
			//JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(this);
			btnAdd.setBounds(110, 171, 65, 23);
			frame.getContentPane().add(btnAdd);
			
			textField_5 = new JTextField();
			textField_5.addActionListener(this);
			textField_5.setBounds(185, 174, 317, 20);
			frame.getContentPane().add(textField_5);
			textField_5.setColumns(10);
			
			textField_6 = new JTextField();
			textField_6.setBounds(185, 209, 317, 20);
			frame.getContentPane().add(textField_6);
			textField_6.setColumns(10);
			
			//filter #2
//			JComboBox<String> comboBox_6 = new JComboBox<String>();
			//comboBox_6.addItem("IF");
			comboBox_6.addItem("OR");
			comboBox_6.addItem("AND");
			comboBox_6.setBounds(68, 269, 86, 20);
			frame.getContentPane().add(comboBox_6);
			
			//JComboBox<String> comboBox_7 = new JComboBox<String>();
			//comboBox_7.addItem("columns");
			comboBox_7.setBounds(167, 269, 86, 20);
			frame.getContentPane().add(comboBox_7);
			
//			JComboBox<String> comboBox_8 = new JComboBox<String>();
			comboBox_8.addItem("EQ");
			comboBox_8.addItem("NE");
			comboBox_8.addItem("LIKE");
			comboBox_8.addItem("NOTLIKE");
			comboBox_8.setBounds(263, 269, 72, 20);
			frame.getContentPane().add(comboBox_8);
			
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			textField_7.setBounds(356, 269, 145, 20);
			frame.getContentPane().add(textField_7);
			
			//filter line #3
//			JComboBox<String> comboBox_9 = new JComboBox<String>();
			//comboBox_9.addItem("IF");
			comboBox_9.addItem("OR");
			comboBox_9.addItem("AND");
			comboBox_9.setBounds(68, 300, 86, 20);
			frame.getContentPane().add(comboBox_9);
			
			//JComboBox<String> comboBox_10 = new JComboBox<String>();
			//comboBox_10.addItem("columns");
			comboBox_10.setBounds(167, 300, 86, 20);
			frame.getContentPane().add(comboBox_10);
			
//			JComboBox<String> comboBox_11 = new JComboBox<String>();
			comboBox_11.addItem("EQ");
			comboBox_11.addItem("NE");
			comboBox_11.addItem("LIKE");
			comboBox_11.addItem("NOTLIKE");
			comboBox_11.setBounds(263, 300, 72, 20);
			frame.getContentPane().add(comboBox_11);
			
			textField_8 = new JTextField();
			textField_8.setColumns(10);
			textField_8.setBounds(356, 300, 145, 20);
			frame.getContentPane().add(textField_8);
			
			//filter line #4
//			JComboBox<String> comboBox_12 = new JComboBox<String>();
			//comboBox_12.addItem("IF");
			comboBox_12.addItem("OR");
			comboBox_12.addItem("AND");
			comboBox_12.setBounds(68, 331, 86, 20);
			frame.getContentPane().add(comboBox_12);
			
			//JComboBox<String> comboBox_13 = new JComboBox<String>();
			//comboBox_13.addItem("columns");
			comboBox_13.setBounds(167, 331, 86, 20);
			frame.getContentPane().add(comboBox_13);
			
//			JComboBox<String> comboBox_14 = new JComboBox<String>();
			comboBox_14.addItem("EQ");
			comboBox_14.addItem("NE");
			comboBox_14.addItem("LIKE");
			comboBox_14.addItem("NOTLIKE");
			comboBox_14.setBounds(263, 331, 72, 20);
			frame.getContentPane().add(comboBox_14);
			
			textField_9 = new JTextField();
			textField_9.setColumns(10);
			textField_9.setBounds(356, 331, 145, 20);
			frame.getContentPane().add(textField_9);
			
			JLabel lblSidacoja = new JLabel("Sidacoja Utilities");
			lblSidacoja.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblSidacoja.setBounds(68, 15, 134, 23);
			frame.getContentPane().add(lblSidacoja);
			
			//JButton btnAdd_1 = new JButton("Add"); //sequencers
			btnAdd_1.addActionListener(this);
			btnAdd_1.setBounds(110, 208, 65, 22);
			frame.getContentPane().add(btnAdd_1);
			
			//JButton btnClear = new JButton("Clear");
			btnClear.addActionListener(this);
			btnClear.setBounds(525, 205, 89, 23);
			frame.getContentPane().add(btnClear);
			
			btnFileLocater.addActionListener(this);
			btnFileLocater.setBounds(508, 40, 106, 23);
			frame.getContentPane().add(btnFileLocater);
			
			btnFileLocater_1.addActionListener(this);
			btnFileLocater_1.setBounds(508, 107, 106, 23);
			frame.getContentPane().add(btnFileLocater_1);
			
			//btnClear_1
			btnClear_1.addActionListener(this);
			btnClear_1.setBounds(525, 171, 89, 23);
			frame.getContentPane().add(btnClear_1);
			
			//JButton btnReadColumns = new JButton("Read Columns");
			btnReadColumns.addActionListener(this);
			btnReadColumns.setBounds(469, 70, 145, 23);
			frame.getContentPane().add(btnReadColumns);
			
			//JTextArea textArea = new JTextArea();
			textArea.setBounds(68, 362, 546, 22);
			frame.getContentPane().add(textArea);
			
		}
		public void actionPerformed(ActionEvent evt) {
			
			if (evt.getSource() == btnFileLocater) {
	            JFileChooser openFile = new JFileChooser();
	            openFile.setAcceptAllFileFilterUsed(false);
	            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("spreadsheets (xls)", "xls");
	            openFile.addChoosableFileFilter(filter1);
	            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("comma-delimited files (csv)", "csv");
	            openFile.addChoosableFileFilter(filter2);
	            FileNameExtensionFilter filter3 = new FileNameExtensionFilter("xml documents (xml)", "xml");
	            openFile.addChoosableFileFilter(filter3);
	            FileNameExtensionFilter filter4 = new FileNameExtensionFilter("json documents (json)", "json");
	            openFile.addChoosableFileFilter(filter4);
	            openFile.showOpenDialog(frame);
	            File inFile = openFile.getSelectedFile();
	            if(inFile!=null) {
	            	//System.out.println(inFile.getAbsolutePath());
	            	txtInputFileOr.setText(inFile.getAbsolutePath());
	            	String type = txtInputFileOr.getText().substring(txtInputFileOr.getText().length()-4, txtInputFileOr.getText().length());
	            	int typeLen = 0;
	            	if(".".equals(type.substring(0, 1)))
	            		typeLen = 3;
	            	else
	            		typeLen = 4;
	            	comboBox.setSelectedItem(txtInputFileOr.getText().substring(txtInputFileOr.getText().length()-typeLen, txtInputFileOr.getText().length()).toUpperCase());
	            }
			} 
			if (evt.getSource() == btnFileLocater_1) {
	            JFileChooser openFile = new JFileChooser();
	            openFile.setAcceptAllFileFilterUsed(false);
	            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("spreadsheets (xls)", "xls");
	            openFile.addChoosableFileFilter(filter1);
	            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("comma-delimited files (csv)", "csv");
	            openFile.addChoosableFileFilter(filter2);
	            FileNameExtensionFilter filter3 = new FileNameExtensionFilter("xml documents (xml)", "xml");
	            openFile.addChoosableFileFilter(filter3);
	            FileNameExtensionFilter filter4 = new FileNameExtensionFilter("json documents (json)", "json");
	            openFile.addChoosableFileFilter(filter4);
	            openFile.showOpenDialog(frame);
	            File inFile = openFile.getSelectedFile();
	            if(inFile!=null) {
	            	//System.out.println(inFile.getAbsolutePath());
	            	//if(textField_2.getText().length()==0) {
	            	textField_2.setText(inFile.getAbsolutePath());
	            	String type = textField_2.getText().substring(textField_2.getText().length()-4, textField_2.getText().length());
	            	int typeLen = 0;
	            	if(".".equals(type.substring(0, 1)))
	            		typeLen = 3;
	            	else
	            		typeLen = 4;
	            	comboBox_1.setSelectedItem(textField_2.getText().substring(textField_2.getText().length()-typeLen, textField_2.getText().length()).toUpperCase());
	            } 
			} 
			if (evt.getSource() == btnAdd) {
				for( ; ; ) {
					String columnName = JOptionPane.showInputDialog(frame, "Enter Column Name");
					if(common.isNullOrEmpty(columnName)) break;
					columnArray.add(columnName);
					addToDropDowns(columnName);
					if(textField_5.getText().isEmpty()) {
						textField_5.setText(textField_5.getText()+columnName);
						textField_6.setText(textField_6.getText()+columnName);
					} else {
						textField_5.setText(textField_5.getText()+","+columnName);
						textField_6.setText(textField_6.getText()+","+columnName);
					}
				} //end for
			}
			if (evt.getSource() == btnAdd_1) {
				for( ; ; ) {
					String columnName = JOptionPane.showInputDialog(frame, "Enter Column Name");
					if(common.isNullOrEmpty(columnName)) break;
					if(textField_6.getText().isEmpty()) {
						textField_6.setText(textField_6.getText()+columnName);
					} else {
						textField_6.setText(textField_6.getText()+","+columnName);
					}
				} //end for
			}
			if (evt.getSource() == btnGo) {
				boolean valid = validateInput();
				textArea.removeAll();
				textArea.setText("");
				if(valid)
					prepareAndFire();
				else
					textArea.setText("validation error - run aborted");
				//frame.dispose();
			}
			if (evt.getSource() == btnClear_1) {
				textField_5.setText("");
				textField_6.setText("");
				addToDropDowns("RemoveAll");
			}
			if (evt.getSource() == btnClear) {
				textField_6.setText("");
			}
			if (evt.getSource() == btnReadColumns) {
				textArea.removeAll();
				readInputForColumns();
			}
			if (evt.getSource() == btnCancel) {
				frame.dispose();
			}
		} //end actionPerformed
		
		public boolean validateInput() {
			boolean result = true;
			if(validFileOrUrl(txtInputFileOr.getText())) {
				System.out.println(" input "+txtInputFileOr.getText()+" is valid");
				//JOptionPane.showMessageDialog(null, "file or url was valid");
			} else {
				System.out.println("file or url was NOT valid");
				JOptionPane.showMessageDialog(null, "file or url was NOT valid");
				result = false;
			}
			if(!"for jdbc only".equals(textField_1.getText()) && 
					!"JDBC".equals(comboBox.getSelectedItem()) )  {
						JOptionPane.showMessageDialog(null, "Select JDBC for input table");
						result = false;
			}
			if(!"for jdbc only".equals(textField_3.getText()) && 
					!"JDBC".equals(comboBox_1.getSelectedItem()) )  {
						JOptionPane.showMessageDialog(null, "Select JDBC for ouput table");
						result = false;
			}
			
			return result;
		}
		public boolean validFileOrUrl(String input) {
			boolean valid = false;
			if(input.startsWith("jdbc:")) {
				valid = true;
			} else {

					// create new file
					File f = new File(input);
					valid = f.exists();
					//System.out.println("input file was "+valid);
			} 
	
/*
				if(valid == false) { //URL.isvalid
		        	URL url = new URL(input); 
					InputStream i = null;
					i = url.openStream();
					if(i==null)
						valid = false; 
					else
						valid =true;
		        } */
		         
			return valid;
		
		} //end validFileOrUrl

		public void addToDropDowns(String szColumn) {
			if("RemoveAll".equals(szColumn)) {
				comboBox_3.removeAllItems();
				comboBox_7.removeAllItems();
				comboBox_10.removeAllItems();
				comboBox_13.removeAllItems();
				
			} else {
				comboBox_3.addItem(szColumn);
				comboBox_7.addItem(szColumn);
				comboBox_10.addItem(szColumn);
				comboBox_13.addItem(szColumn);
			}
		}
		
		public void readInputForColumns() {

			Sidacoja sdcj = new Sidacoja();
	    	sdcj.input(txtInputFileOr.getText());
	    	sdcj.inputType((String) comboBox.getSelectedItem());

//	    	console("input "+txtInputFileOr.getText() +" "+(String) comboBox.getSelectedItem() +" "+textField_1.getText() );
	    	
	    	if(!common.isNullOrEmpty(textField_1.getText())) {
	    		sdcj.setTable(textField_1.getText());
	    	}
	    	
	    	sdcj.setCacheOnly(true);
	    	console(sdcj.toString());
	    	RowCache cache = new RowCache();
	    	try {
	    		cache = sdcj.fire();
	    	} catch( Exception e) {
	    		console(e.getMessage() );
	    		e.printStackTrace();
	    	}
	    	//console("returned: "+sdcj.getReturnString());	    	
	    	console("output selected: "+cache.countSelected());
	    	textArea.removeAll();
//	    	textArea.append("returned: "+sdcj.getReturnString());
	    	textArea.setText("  rows selected for output: "+cache.countSelected());
	    	
	    	List<Row> rows = cache.getList();
	    	//rows.isEmpty()
			List<Cell> cells = rows.get(0).getList();
   			StringBuffer szColumn = new StringBuffer();
   			int nbrCols = 0;
   			for(Cell cell: cells) {
   				szColumn.replace(0, cell.getLabel().length(), cell.getLabel());
   				nbrCols++;
   				console("found column "+szColumn.toString());
				columnArray.add(szColumn.toString());
				addToDropDowns(szColumn.toString());
				if(textField_5.getText().isEmpty()) {
					textField_5.setText(textField_5.getText()+szColumn.toString());
					textField_6.setText(textField_6.getText()+szColumn.toString());
				} else {
					textField_5.setText(textField_5.getText()+","+szColumn.toString());
					textField_6.setText(textField_6.getText()+","+szColumn.toString());
				}
   			}

   			textArea.append("  columns selected: "+nbrCols);

		}
		
		public void prepareAndFire() {
	    	//final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";
	    	//final String USER = "sa";
	    	//final String PSWD = "";

	    	Sidacoja sdcj = new Sidacoja();
	    	sdcj.input(txtInputFileOr.getText());
	    	sdcj.inputType((String) comboBox.getSelectedItem());
	    	//sdcj.setTable(textField_1.getText()); //Employees
	    	//console("input "+txtInputFileOr.getText() +" "+(String) comboBox.getSelectedItem() +" "+textField_1.getText());
	    	if(!common.isNullOrEmpty(textField_1.getText())) {
	    		sdcj.setTable(textField_1.getText());
	    	}
	    	String szArray[] = new String[columnArray.size()];
	    	szArray = columnArray.toArray(szArray);
	    	//String szArray[] = columnArray.toArray(String[]); 
	    	for(int k=0;k<columnArray.size();k++) {
	    		//szArray[k] = (String) columnArray.get(k);
	    		console(szArray[k]);
	    	}
	    	if(!common.isNullOrEmpty((String) textField_5.getText())) {
		    	sdcj.columns(szArray);
	    	}
	    	//sdcj.sequence(new String[]{ (String) comboBox_5.getSelectedItem() });
	    	if(!common.isNullOrEmpty((String) textField_6.getText())) {
	    		sdcj.sequence(textField_6.getText().split("\\s*,\\s*")); //squeeze whitespace
	    	}
	    	if(!common.isNullOrEmpty((String) textField_4.getText())) {
	    		sdcj.addFilter(new String[]{(String) comboBox_2.getSelectedItem(),
	    				(String) comboBox_3.getSelectedItem(),
	    				(String) comboBox_4.getSelectedItem(),
	    				textField_4.getText() } );
	    	}
	    	if(!common.isNullOrEmpty((String) textField_7.getText())) {
	    		sdcj.addFilter(new String[]{(String) comboBox_6.getSelectedItem(),
	    				(String) comboBox_7.getSelectedItem(),
	    				(String) comboBox_8.getSelectedItem(),
	    				textField_7.getText() } );
	    	}
	    	if(!common.isNullOrEmpty((String) textField_8.getText())) {
	    		sdcj.addFilter(new String[]{(String) comboBox_9.getSelectedItem(),
	    				(String) comboBox_10.getSelectedItem(),
	    				(String) comboBox_11.getSelectedItem(),
	    				textField_8.getText() } );
	    	}
	    	if(!common.isNullOrEmpty((String) textField_9.getText())) {
	    		sdcj.addFilter(new String[]{(String) comboBox_12.getSelectedItem(),
	    				(String) comboBox_13.getSelectedItem(),
	    				(String) comboBox_14.getSelectedItem(),
	    				textField_9.getText() } );
	    	}
	    	//sdcj.output("jdbc:sqlserver://CHUCK-PC\\SQLSERVERADVSP2;database=chuckDB;integratedSecurity=true;user=Chuck-PC\\Chuck;");
	    	//sdcj.setOutputTable(textField_3.getText());
	    	//sdcj.outputType((String) comboBox_1.getSelectedItem());
	    	
	    	sdcj.output(textField_2.getText()); //"no file required");	//("./sidacoja.xml");
	    	sdcj.outputType((String) comboBox_1.getSelectedItem());
	    	//console("output "+textField_2.getText() +" "+(String) comboBox_1.getSelectedItem() +" "+textField_3.getText());
	    	if(!common.isNullOrEmpty(textField_3.getText()))
	    		sdcj.setOutputTable(textField_3.getText());
	    	sdcj.setCacheOnly(false);
	    	console(sdcj.toString());
	    	RowCache cache = new RowCache();
	    	try {
	    		cache = sdcj.fire();
	    	} catch( Exception e) {
	    		console(e.getMessage() );
	    		e.printStackTrace();
	    	}
	    	//console("returned: "+sdcj.getReturnString());	    	
	    	console("output selected: "+cache.countSelected());
	    	textArea.removeAll();
	    	//textArea.append("returned: "+sdcj.getReturnString());
	    	textArea.append("output selected: "+cache.countSelected());
		}
		   public static void console(String sz) {
		    	System.out.println(sz);
		    }
	} //end class
