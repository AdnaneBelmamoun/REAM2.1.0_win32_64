package reamGUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import reamLDB.ImportAll_runnable;
import reamLDB.ImportRE_runnable;
import redfinBots.RE_Object;
import redfinBots.RFSEngine;;

public class RSresultTabPane extends JPanel 
							implements ActionListener { 
	   
private static final long serialVersionUID = 4740719025832571023L;
private Vector<Vector<String>> RSresdataheaders;
private RFSEngine rfsengine =null;
private JTable table;
public static int[] SelectedRE_ind;
//private JCheckBox rowCheck;
//private JCheckBox columnCheck;SrchResGUITab
//private JCheckBox cellCheck;
//private ButtonGroup buttonGroup;
private JTextArea output;

public RSresultTabPane(Vector<Vector<String>> D,Vector<Vector<String>> FullData,RFSEngine rfseng) {
super();
/*RSresdataheaders =  new Vector<Vector<String>>();
int iii =0;
Vector<String> tt = new Vector<String>();
for (Vector<String> gg: FullData){
	if(iii>0){
		tt.addAll(gg);
	}
	iii++;
	RSresdataheaders.add(tt);
}*/
RSresdataheaders = D;//FullData;
rfsengine = rfseng; 
setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

table = new JTable(new RSTableModel(D));

table.setPreferredScrollableViewportSize(new Dimension(500, 170));
table.setFillsViewportHeight(true);
table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
table.setAutoCreateRowSorter(true);
table.setRowSelectionAllowed(true); // the rows are always selectable  

table.getSelectionModel().addListSelectionListener(new RSresultTab_RowListener());

add(new JScrollPane(table));

// Buttons for import Control:
JPanel RSresTabPan_buttpane = new JPanel();
JButton Butt_Import_all = new JButton("Import All R.E.'s To DataBase");
Butt_Import_all.addActionListener(this);
Butt_Import_all.setActionCommand("ImportAll");
JButton Butt_Import_selected = new JButton("Import Selected R.E.'s");
Butt_Import_selected.addActionListener(this);
Butt_Import_selected.setActionCommand("ImportSelected");
JButton Butt_Show_Details = new JButton("Show R.E. Details ");
Butt_Show_Details.addActionListener(this);
Butt_Show_Details.setActionCommand("ShowREdetails");
RSresTabPan_buttpane.add(Butt_Import_all);
RSresTabPan_buttpane.add(Butt_Import_selected);
RSresTabPan_buttpane.add(Butt_Show_Details);
RSresTabPan_buttpane.setBorder(BorderFactory.createTitledBorder("Import Operations"));
add(RSresTabPan_buttpane);

output = new JTextArea(5, 90);
output.setEditable(false);
add(new JScrollPane(output));
}



public void actionPerformed(ActionEvent event) {
String command = event.getActionCommand();
SelectedRE_ind = table.getSelectedRows();

//int nb_taskImpAll = 0;
if(command.equals("ImportAll")){
	//nb_taskImpAll++;
	// Import ALL  operation may consume too much memory, it's better to thread it:
	Runnable task_importAll = new ImportAll_runnable(RSresdataheaders,rfsengine);
    Thread worker_importAll = new Thread(task_importAll);
    // We can set the name of the thread
    worker_importAll.setName("TaskImportAll ");//+nb_taskImpAll);
    // Start the thread, never call method run() direct
    worker_importAll.start();
   
}

if(command.equals("ImportSelected")){
	if(SelectedRE_ind.length!=1){
		// SHow message box dialog saying that REAM can show only one selected RE 
		
	}else{
					
	// Call New ShowREDetailsFrame(RE_Object REobj)
		int selecindx = SelectedRE_ind[0];
		int realidx = table.convertRowIndexToModel(selecindx);//table.getSelectedRows()[0]);
		//if(realidx==0){realidx=1;}
		Vector<String> tttempvect = RSresdataheaders.elementAt(realidx+1);
		String href = (String)tttempvect.elementAt(8).replaceAll("http", "https");//tttempvect.indexOf("URL"));
		output.append("selected row vector "+RSresdataheaders.elementAt(realidx+1).toString()+" ---> \n");
		output.append("the link index "+(realidx+1)+" ---> ");
		output.append(href+"\n");
		
		// build my runnable 
				Runnable task_importselectedRE = new ImportRE_runnable(href,rfsengine);
			    Thread worker_importselectedRE = new Thread(task_importselectedRE);
			    // We can set the name of the thread
			    worker_importselectedRE.setName("TaskImportSelectedRE ");//+nb_taskImpAll);
			    // Start the thread, never call method run() direct
			    worker_importselectedRE.start();
				
		
		
		
	//	RE_Object REobj_temp = new RE_Object(rfsengine.getRF_base_URL(), href, rfsengine.getClient());
	//	new ShowREDetailsFrame(REobj_temp);
	}
	
	
	
	
/*	if(SelectedRE_ind.length>1){
		// SHow message box dialog saying that REAM can show only one selected RE 
	}else{
	// Call New ShowREDetailsFrame(RE_Object REobj)
		int selecindx = SelectedRE_ind[0];
		int realidx = table.convertRowIndexToModel(selecindx);//table.getSelectedRows()[0]);
		//if(realidx==0){realidx=1;}
		
		String href = ((Vector<String>)RSresdataheaders.elementAt(realidx+1)).elementAt(0);
		output.append("selected row vector "+RSresdataheaders.elementAt(realidx+1).toString()+" ---> \n");
		output.append("the link index "+(realidx+1)+" ---> ");
		output.append(href+"\n");
		
		RE_Object REobj_temp = new RE_Object(rfsengine.getRF_base_URL(), href, rfsengine.getClient());
		new ShowREDetailsFrame(REobj_temp);
	}
	*/
}

if(command.equals("ShowREdetails")){
	if(SelectedRE_ind.length>1){
		// SHow message box dialog saying that REAM can show only one selected RE 
	}else{
	// Call New ShowREDetailsFrame(RE_Object REobj)
		int selecindx = SelectedRE_ind[0];
		int realidx = table.convertRowIndexToModel(selecindx);//table.getSelectedRows()[0]);
		//if(realidx==0){realidx=1;}
		Vector<String> tttempvect = RSresdataheaders.elementAt(realidx+1);
		String href = (String)tttempvect.elementAt(8).replaceAll("http", "https");//tttempvect.indexOf("URL"));
		output.append("selected row vector "+RSresdataheaders.elementAt(realidx+1).toString()+" ---> \n");
		output.append("the link index "+(realidx+1)+" ---> ");
		output.append(href+"\n");
		
		RE_Object REobj_temp = new RE_Object(rfsengine.getRF_base_URL(), href, rfsengine.getClient());
		new ShowREDetailsFrame(REobj_temp);
	}
	
}


}


private void outputSelection() {
/*output.append(String.format("Lead: %d, %d. ",
table.getSelectionModel().getLeadSelectionIndex(),
table.getColumnModel().getSelectionModel().getLeadSelectionIndex()));*/
output.append("Selected Rows ---> :");
for (int c : table.getSelectedRows()) {
output.append(String.format(" %d", table.convertRowIndexToModel(c)));
}
/*output.append(". Columns:");
for (int c : table.getSelectedColumns()) {
output.append(String.format(" %d", c));
}*/
output.append(".\n");
}

private class RSresultTab_RowListener implements ListSelectionListener {
public void valueChanged(ListSelectionEvent event) {
if (event.getValueIsAdjusting()) {
return;
}

output.append("ROW SELECTION EVENT. ----> ");
outputSelection();
}
}


class RSTableModel extends AbstractTableModel {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Vector<String> columnNames = new Vector<String>();
private Vector<Vector<Object>> data = new Vector<Vector<Object>>();

RSTableModel(Vector<Vector<String>> Dataini){

columnNames = Dataini.get(0);
//Vector<Vector<Object>> data_obj = new Vector<Vector<Object>>();//Data;
//Vector<Vector<String>> Data = new Vector<Vector<String>>();
//Data  = .add(Dataini.remove(0));
//data = data_net;

//Vector<String> tit = Data.get(0);//.toArray();
int indrow= 0;
Vector<Object> row_obj  = null;
//System.out.println("les colonnes sont : ====> "+columnNames);
//System.out.println("first column  : ====> "+columnNames.get(0));
for(Vector<String> row: Dataini){
		if(indrow>0){
					row_obj = new Vector<Object>();
					//int addressCI = tit.indexOf("Adress");
					//System.out.println("Index Adress : "+tit.indexOf("Adress"));
					int indcol = 0;
					String cell="";
					for(String cellini: row){
									//	if(indcol>0){	
									//System.out.println("column name  : ====> "+columnNames.get(indcol));
									cell = cellini.replace("—", "0").replace("$", "").replace(",", "");//.replace(".", "");
									//if(!cell.isEmpty()){
									if((columnNames.get(indcol)).equalsIgnoreCase("Address")){ 
									row_obj.add(cellini);
									// }else{row_obj.add("");}
									}
									if((columnNames.get(indcol)).equalsIgnoreCase("Location")){
									//System.out.println(columnNames.get(indcol)+ "Value ===>"+ cellini);
									row_obj.add(cellini);
									// }else{row_obj.add("");}
					}
					
					if((columnNames.get(indcol)).equalsIgnoreCase("Beds")){
									//System.out.println(columnNames.get(indcol)+ "Value cellini ===>"+ cellini);
									//System.out.println("cell content ====>"+cell+"\n"
									//       +"Integer.getInteger(cell) results ====>" + Integer.parseUnsignedInt(cell));
									row_obj.add(String.valueOf(cell));//Double.parseDouble(cell));//Integer.parseUnsignedInt(cell));//.replace("$", "").replace(",", ""))));//cell.replace("$", ""));//
					} 
					if( (columnNames.get(indcol)).equalsIgnoreCase("Baths")){
									//System.out.println(columnNames.get(indcol)+ "Value cellini ===>"+ cellini);
									//System.out.println("cell content ====>"+cell+"\n"
									//	+"Integer.getInteger(cell) results ====>" + String.valueOf(cell));
									row_obj.add(String.valueOf(cell));//.replace("$", "").replace(",", ""))));//cell.replace("$", ""));//
									
					} 
					if((columnNames.get(indcol)).contentEquals("$/SQUARE FEET")){
									//System.out.println(columnNames.get(indcol)+ "Value cellini ===>"+ cellini);
									//System.out.println("cell content ====>"+cell+"\n"
									//	+"Integer.getInteger(cell) results ====>" + Integer.parseUnsignedInt(cell));
									row_obj.add(String.valueOf(cell));//Integer.parseUnsignedInt(cell));//.replace("$", "").replace(",", ""))));//cell.replace("$", ""));//
					} 
					if((columnNames.get(indcol)).contains("DAYS")){
									//System.out.println(columnNames.get(indcol)+ " Value cellini ===>"+ cellini);
									//System.out.println("cell content ====>"+cell+"\n"
									//+"Integer.getInteger(cell) results ====>" + Integer.parseUnsignedInt(cell));
									//row_obj.add(Integer.parseUnsignedInt(cell));//.replace("$", "").replace(",", ""))));//cell.replace("$", ""));//
									row_obj.add(String.valueOf(cell));
					
					}
					
					if((columnNames.get(indcol)).equalsIgnoreCase("Price")){
									//	if(!cell.isEmpty()){
									//System.out.println(columnNames.get(indcol)+ "Value cellini ===>"+ cellini);
									//	System.out.println("cell content ====>"+cell+"\n"
									//	+"Integer.getInteger(cell) results ====>" + Integer.parseUnsignedInt(cell));
									//row_obj.add(Integer.parseUnsignedInt(cell));//.replace("$", "").replace(",", ""))));//cell.replace("$", ""));//
									row_obj.add(String.valueOf(cell));
									//}else{row_obj.add(0);}
					}
					if((columnNames.get(indcol)).equalsIgnoreCase("SQUARE FEET")){
									//if(!cell.isEmpty()){
										//System.out.println(String.valueOf(cell));	
									    row_obj.add(String.valueOf(cell));//Integer.parseUnsignedInt(cell));//.replace(",", "").replace("$", ""))));//cell);//
									//}else{row_obj.add(0);}
					}
					if((columnNames.get(indcol)).startsWith("URL")){//equalsIgnoreCase("href")){
										//if(!cell.isEmpty()){
										row_obj.add(String.valueOf(cell));//.replace(",", "").replace("$", ""))));//cell);//
										//}else{row_obj.add(0);}
										}
					//}
					//
					indcol++;   
					}
			data.add(row_obj);
			}
		indrow++;
		}
//	data = data_net;
}
			public int getColumnCount() {
			return columnNames.size();
			}
			
			public int getRowCount() {
			return data.size();
			}
			
			public String getColumnName(int col) {
			return columnNames.get(col);
			}
			
			public Object getValueAt(int row, int col) {
			
			//System.out.println("print row "+row+"----> "+((Vector<Object>)data.get(row)));//.get(col).getClass()
			return (Object)((Vector<Object>)data.get(row)).get(col);
			}
			
			/*
			* JTable uses this method to determine the default renderer/
			* editor for each cell.  If we didn't implement this method,
			* then the last column would contain text ("true"/"false"),
			* rather than a check box.
			*/
			public Class<? extends Object> getColumnClass(int c) {
				//System.out.println("row 0 details  ----> "+((Vector<Object>)data.get(0)).toString());
				//System.out.println("column Class ----> "+getValueAt(0, c).getClass());
			return getValueAt(0, c).getClass();
			}
			
			/*
			* Don't need to implement this method unless your table's
			* editable.
			*/
			public boolean isCellEditable(int row, int col) {
			//Note that the data/cell address is constant,
			//no matter where the cell appears onscreen.
			if (col < 2) {
			return false;
			} else {
			return true;
			}
			}
			
			/*
			* Don't need to implement this method unless your table's
			* data can change.
			*/
			public void setValueAt(Object value, int row, int col) {
			//       data.set[row][col] = value;
			//       fireTableCellUpdated(row, col);
			}

}

/**
* Create the GUI and show it.  For thread safety,
* this method should be invoked from the
* event-dispatching thread.
*/
private static void createAndShowGUI() {
//Disable boldface controls.
UIManager.put("swing.boldMetal", Boolean.FALSE); 

//Create and set up the window.
JFrame searchresltframe = new JFrame("Search Results Table  -------> Author: Belmamoun Adnane ");
searchresltframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

String searchkey = "Los angeles";
RFSEngine rfseng =  new RFSEngine();
Vector<Vector<String>> Data = rfseng.preparesearchMatches(searchkey);
Vector<Vector<String>> datatoscreen = new Vector<Vector<String>>();
for (Vector<String> rr: Data){
int indc=0;
Vector<String> rowtoscreen =new Vector<String>();
for(String cc: rr){
if(indc>0){
rowtoscreen.add(cc);
}

indc++;
}
datatoscreen.add(rowtoscreen);
}

//Create and set up the conteDatant pane.
RSresultTabPane newContentPane = new RSresultTabPane(datatoscreen,Data,rfseng);
newContentPane.setOpaque(true); //content panes must be opaque
searchresltframe.setContentPane(newContentPane);

//Display the window.
searchresltframe.pack();
searchresltframe.setVisible(true);
}

public static int strToInt( String str ){
int i = 0;
int num = 0;
boolean isNeg = false;

//Check for negative sign; if it's there, set the isNeg flag
if (str.charAt(0) == '-') {
isNeg = true;
i = 1;
}

//Process each character of the string;
while( i < str.length()) {
num *= 10;
num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
}

if (isNeg)
num = -num;
return num;
}


public static void main(String[] args) {
//Schedule a job for the event-dispatching thread:
//creating and showing this application's GUI.
javax.swing.SwingUtilities.invokeLater(new Runnable() {
public void run() {
createAndShowGUI();
}
});
}
}
