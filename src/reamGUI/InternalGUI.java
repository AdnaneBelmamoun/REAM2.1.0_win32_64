package reamGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;//BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import redfinBots.RFSEngine;

/*
 * InternalFrameDemo.java requires:
 *   MyInternalFrame.java
 */
public class InternalGUI extends JFrame
                               implements ActionListener, ItemListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DynamicTree treePanel;
    private JButton RSButton = null;
    private JTextField RSInputfield = null;
    private JRadioButton jrb_RSbycity = null;
    private JRadioButton jrb_RSbyzip = null;
    private JRadioButton jrb_RSbyadd = null;
    private JRadioButton jcb_RS_Fast_data_import = null;
    private JRadioButton jcb_RS_import_all = null;

    
    private JButton LSButton = null;
    private JTextField LSInputfield = null;
    private JCheckBox jcbfilteropt_SBy_Price =null;
    private JCheckBox jcbfilteropt_SBy_lotSize =null;
    private JCheckBox jcbfilteropt_SBy_repAgent =null;
    private JTextArea DBScreen= null;
    private JTextArea operationstextarea =null;
    private JScrollPane JSP =null;
    private JPanel MainPane =null;
    private JScrollPane FrameJSP =null;
    //private Vector<NameValuePair> RSchkbox= new Vector<NameValuePair>();  
    

    public InternalGUI() {
        super("REAM2.1 Main GUI <--- Author: Adnane Belmamoun --->");
        
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 150;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
        setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        setBackground(Color.pink);
        
   //    setResizable(false);
     this.getContentPane().setLayout(new BorderLayout(1,0));

        //Set up the GUI.
        
/*
 * Remote search Panel
 */
        JPanel RS_jp = new  JPanel(new GridLayout(2, 0));
        RS_jp.setPreferredSize(new Dimension(550,100));
        RS_jp.setVisible(true);
        //RS_jp.setBackground(new Color(150));
        RS_jp.setBorder(BorderFactory.createTitledBorder("Remote Search"));//)createEmptyBorder(5,5,5,5));
        RSButton = new JButton("Remote Search >>>");
        RSButton.setPreferredSize(new Dimension(180, 30));
        RSButton.setActionCommand("New_RS");
        RSButton.addActionListener(this);
        RSInputfield = new JTextField("los angeles");//Ciy / Zip / Address
        RSInputfield.setPreferredSize(new Dimension(270, 30));
        RSButton.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        RSInputfield.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
       
        //Group of RS Radio buttons: 
        JPanel jrbRSgrpane =new JPanel(new GridLayout(3,0));
       // jrbRSgrpane.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
       // jrbRSgrpane.setBackground(Color.orange);
        jrbRSgrpane.setBorder(BorderFactory.createTitledBorder("Search By:"));
        jrb_RSbycity = new JRadioButton("City");
        jrb_RSbycity.setSelected(true);
        jrb_RSbyzip = new JRadioButton("Zip");
        jrb_RSbyadd = new JRadioButton("Address");
        jrb_RSbycity.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
        jrb_RSbyzip.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
        jrb_RSbyadd.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
        
        //group the radio butttons together
        ButtonGroup RS_RBgroup = new ButtonGroup();
        RS_RBgroup.add(jrb_RSbycity);
        RS_RBgroup.add(jrb_RSbyzip);
        RS_RBgroup.add(jrb_RSbyadd);
      
        //Register an Action listener for the radio buttons.
        jrb_RSbycity.addActionListener(this);
        jrb_RSbyzip.addActionListener(this);
        jrb_RSbyadd.addActionListener(this);
      //Register an Action Command for the radio buttons.
        jrb_RSbycity.setActionCommand("ByCity");
        jrb_RSbyzip.setActionCommand("ByZip");
        jrb_RSbyadd.setActionCommand("ByAdd");
        //set the size 
        jrb_RSbycity.setPreferredSize(new Dimension(100, 20));
        jrb_RSbyzip.setPreferredSize(new Dimension(100, 20));
        jrb_RSbyadd.setPreferredSize(new Dimension(100, 20));
        // add the radio buttons to the RS Panel
        jrbRSgrpane.add(jrb_RSbycity);//,BorderLayout.WEST);scrollPane
        jrbRSgrpane.add(jrb_RSbyzip);//,BorderLayout.EAST);
        jrbRSgrpane.add(jrb_RSbyadd);//,BorderLayout.EAST);
      
      
        //Group of RS radio button: 
        JPanel jcbRS_import_pane =new JPanel(new GridLayout(2,0));
       // jcbRS_import_pane.setBackground(Color.YELLOW);
        jcbRS_import_pane.setBorder(BorderFactory.createTitledBorder("Data Import Options"));
        //jcbRS_import_pane.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        jcb_RS_Fast_data_import = new JRadioButton("Import R.E. Headers Only (Fast Import)");
        jcb_RS_Fast_data_import.setSelected(true);
        jcb_RS_import_all = new JRadioButton("Import All R.E.Data (Deep Import)");
        jcb_RS_Fast_data_import.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        jcb_RS_import_all.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        
        //group the radio butttons together
        ButtonGroup RSImport_groupRB = new ButtonGroup();
        RSImport_groupRB.add(jcb_RS_import_all);
        RSImport_groupRB.add(jcb_RS_Fast_data_import);
        
        
        //Register an Action listener for the radio buttons.
        jcb_RS_import_all.addActionListener(this);
        jcb_RS_Fast_data_import.addActionListener(this);
       //Register an Action listener for the radio buttons.
        jcb_RS_import_all.setActionCommand("ImportAll");
        jcb_RS_Fast_data_import.setActionCommand("ImportHeadersOnly");
        //set the size 
        jcb_RS_import_all.setPreferredSize(new Dimension(400, 20));
        jcb_RS_Fast_data_import.setPreferredSize(new Dimension(400, 20));
        
        // add the radio buttons to the RS Panel
        jcbRS_import_pane.add(jcb_RS_Fast_data_import);//,BorderLayout.EAST);
        jcbRS_import_pane.add(jcb_RS_import_all);//,BorderLayout.WEST);
      
        // add the components to the RS Panel
        // after putting them in up and down sub-panels
        JPanel RS_Up_jp = new JPanel();//new GridLayout(0,3));
        RS_Up_jp.add(new JLabel("Remote Search"));
        RS_Up_jp.add(RSInputfield);
        RS_Up_jp.add(RSButton);
    //    RS_Up_jp.setBackground(Color.RED);
        //   RS_jp.add(RSInputfield);//,FlowLayout.LEFT);
      //  RS_jp.add(RSButton);//,FlowLayout.RIGHT);
        JPanel RS_Down_jp = new JPanel(new BorderLayout());//new GridLayout(0,2));
        RS_Down_jp.add(jrbRSgrpane,BorderLayout.AFTER_LINE_ENDS);
        RS_Down_jp.add(jcbRS_import_pane,BorderLayout.CENTER);
      //  RS_Down_jp.setBackground(Color.GREEN);
        //RS_jp.add(jrbRSgrpane,BorderLayout.AFTER_LAST_LINE);
        //RS_jp.add(jcbRSpane,BorderLayout.AFTER_LAST_LINE);
        
        RS_jp.add(RS_Up_jp);
        RS_jp.add(RS_Down_jp);
 
/*
 * Local Search Panel
 */
        JPanel LS_jp = new  JPanel();
        LS_jp.setPreferredSize(new Dimension(550,100));
        LS_jp.setVisible(true);
        //LS_jp.setBackground(new Color(80));
        LS_jp.setBorder(BorderFactory.createTitledBorder("Search For Existing R.E's in the Local Database"));//)createEmptyBorder(5,5,5,5));
        LSButton = new JButton("Local Search >>>");
        LSButton.setPreferredSize(new Dimension(180, 30));
        LSButton.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        LSButton.setActionCommand("New_LS");
        LSButton.addActionListener(this);
        LSInputfield = new JTextField("Ciy / Zip / Address");
        LSInputfield.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
        LSInputfield.setPreferredSize(new Dimension(270, 30));
        
        JPanel FilteringOptions_pane = new JPanel(new GridLayout(3,0));
        FilteringOptions_pane.setBorder(BorderFactory.createTitledBorder("Filtering By : "));
         jcbfilteropt_SBy_Price = new JCheckBox("Real Estate Price");
         jcbfilteropt_SBy_lotSize = new JCheckBox("Real Estat Lot. Size");
         jcbfilteropt_SBy_repAgent = new JCheckBox("Real Estat Rep. Agent");

         FilteringOptions_pane.add(jcbfilteropt_SBy_Price);
         FilteringOptions_pane.add(jcbfilteropt_SBy_lotSize);
         FilteringOptions_pane.add(jcbfilteropt_SBy_repAgent);
         
         
        //Register a listener for the check boxes.
        jcbfilteropt_SBy_Price.addItemListener(this);
        jcbfilteropt_SBy_lotSize.addItemListener(this);
        jcbfilteropt_SBy_repAgent.addItemListener(this);
      
        
        JPanel LS_Up_jp = new JPanel();//new GridLayout(0,3));
        LS_Up_jp.add(new JLabel("Local Database Search"));
        LS_Up_jp.add(LSInputfield);
        LS_Up_jp.add(LSButton);
       // LS_Up_jp.setBackground(Color.BLUE);
        //   RS_jp.add(RSInputfield);//,FlowLayout.LEFT);
      //  RS_jp.add(RSButton);//,FlowLayout.RIGHT);
        JPanel LS_Down_jp = new JPanel(new BorderLayout());//new GridLayout(0,2));
        LS_Down_jp.add(FilteringOptions_pane,BorderLayout.CENTER);
     //   LS_Down_jp.setBackground(Color.GRAY);
        
        LS_jp.add(LS_Up_jp);
        LS_jp.add(LS_Down_jp);
 
        
        
        //LS_jp.add(LSInputfield);//,BorderLayout.WEST);
       // LS_jp.add(LSButton);//,BorderLayout.EAST);
       
     
        //JPanel operationspane = new JPanel(new GridLayout());
       // operationspane.setPreferredSize(new Dimension(550,200));
       // operationspane.setVisible(true);
      //  operationspane.setBackground(Color.cyan);
       // operationspane.setBorder(BorderFactory.createTitledBorder("Operations"));
        operationstextarea = new JTextArea();
       // operationstextarea.setRows(50);
      //  operationstextarea.setTabSize(30);
      //  operationstextarea.setWrapStyleWord(true);
      //  operationstextarea.setLineWrap(true);
        operationstextarea.setBorder(BorderFactory.createTitledBorder("Operations Output"));
        //operationstextarea.setAutoscrolls(true);
        operationstextarea.setPreferredSize(new Dimension(250,200));
       // operationspane.add(operationstextarea);//,BorderLayout.CENTER);
        
        //operationspane.add(new JButton("----------------___"),BorderLayout.NORTH);
        //operationspane.add(new JButton("----------------___"),BorderLayout.WEST);
        //operationspane.add(new JButton("----------------___"),BorderLayout.EAST);
       
        JPanel mainPane_Up = new JPanel(new GridLayout(2,0));
        mainPane_Up.add(RS_jp);//,BorderLayout.NORTH);
        mainPane_Up.add(LS_jp);//,BorderLayout.NORTH);
        //MainPane.add(mainPane_Up);
        
        JScrollPane mainPane_Down = new JScrollPane(operationstextarea);//operationspane);//,
                //JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                //JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPane_Down.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       // MainPane.add(mainPane_Down);
        
        JSplitPane jspliter_mainpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        		mainPane_Up, mainPane_Down);
        jspliter_mainpane.setOneTouchExpandable(true);
        jspliter_mainpane.setDividerLocation(mainPane_Up.getPreferredSize().width);
        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize_mainpane = new Dimension(mainPane_Up.getPreferredSize());
        mainPane_Up.setMinimumSize(minimumSize_mainpane);
        mainPane_Down.setMinimumSize(minimumSize_mainpane);
        
        MainPane = new JPanel(new GridLayout());
        MainPane.add(jspliter_mainpane);
    /*    // Wrap Panel For Main Search Panels
        MainPane = new JPanel(new GridLayout(3,0));//new GridLayout(0,2));//new GridLayout(0,1));
        MainPane.add(RS_jp);//,BorderLayout.NORTH);
        MainPane.add(LS_jp);//,BorderLayout.NORTH);
        MainPane.add(operationspane);//,BorderLayout.NORTH);
      */ 
       
        FrameJSP =  new JScrollPane(MainPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        
        treePanel = new DynamicTree();
        treePanel.setPreferredSize(new Dimension(300, 450));
        treePanel.setVisible(true);
        
        JPanel DBViewer_jp = new  JPanel(new BorderLayout());//new GridLayout(3,0)
        
        JPanel DB_butt_grp_pane = new JPanel();
       
        JButton DB_Node_Update_butt = new JButton("Update Node");
        JButton DB_Node_Delete_butt = new JButton("Delete Node");
        DB_butt_grp_pane.setPreferredSize(new Dimension(treePanel.getPreferredSize().width,DB_Node_Delete_butt.getPreferredSize().height+5));
        DB_butt_grp_pane.add(DB_Node_Update_butt);
        DB_butt_grp_pane.add(DB_Node_Delete_butt);
        
        DBViewer_jp.add(treePanel,BorderLayout.WEST);
        DBViewer_jp.add(DB_butt_grp_pane,BorderLayout.SOUTH);
        DBViewer_jp.setAutoscrolls(true);
        
        
        
        DBScreen  = new JTextArea("DB Infos Output");
        DBScreen.setBorder(BorderFactory.createTitledBorder("DB Elements Infos:"));
        DBScreen.setPreferredSize(new Dimension(
        		treePanel.getPreferredSize().width,
        		MainPane.getPreferredSize().height-treePanel.getPreferredSize().height+30));
                     //treePanel.getPreferredSize());
        
        JPanel DBViewer_screen_jp = new  JPanel(new BorderLayout());
        DBViewer_screen_jp.add(DBViewer_jp,BorderLayout.NORTH);
        DBViewer_screen_jp.add(DBScreen,BorderLayout.AFTER_LINE_ENDS);

        JSP = new JScrollPane(DBViewer_screen_jp,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
       
        //Create a split pane with the two scroll panes in it.
        JSplitPane jspliter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		JSP, FrameJSP);
        jspliter.setOneTouchExpandable(true);
        jspliter.setDividerLocation(treePanel.getPreferredSize().width);
        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(300, 90);
        JSP.setMinimumSize(minimumSize);
        FrameJSP.setMinimumSize(minimumSize);

        // Finally Add the Jsplitter Containing the Left and the Right Panes        
        add(jspliter);

        // Create and Set the Menu Bar
        setJMenuBar(createMenuBar());
        
    }
    
    public void itemStateChanged(ItemEvent e) {
    	 Object source = e.getItemSelectable();

    	    if (source == jcbfilteropt_SBy_Price) {
    	    	DBScreen.setText("Search by Price");
    	    } else if (source == jcbfilteropt_SBy_lotSize) {
    	    	DBScreen.setText("Search by lot size");
    	    } else if (source == jcbfilteropt_SBy_repAgent) {
    	    	DBScreen.setText("Search by Agent");
    	    } 
    	    
    	    //if (e.getStateChange() == ItemEvent.DESELECTED)
    	     
		
	}

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu = new JMenu("REAM Menu");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //Set up the new Remote search menu item.
        JMenuItem menuItem = new JMenuItem("New Remote Search");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("newRS");
        menuItem.addActionListener(this);
        menu.add(menuItem);

      //Set up the new local search menu item.
        menuItem = new JMenuItem("New Local Search");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("newLS");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Set up the Quit software menu item.
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("New_RS".equals(e.getActionCommand()) || "newRS".equals(e.getActionCommand())) { //new
            //createFrame();
            // create Frame for new Remote search Results:
            // new RSresultsFrame(String RSkey,String Options)
            ShowRSresultsFrame(RSInputfield.getText(),"");
            
            
        }
        if ("New_LS".equals(e.getActionCommand())|| "newLS".equals(e.getActionCommand())) { //new
            createFrame();
        }
        if ("quit".equals(e.getActionCommand())) { //new
        	quit(); // it's Better to Call a customized Quit Method
        }
        
	    if ("ByCity".equals(e.getActionCommand())) {
	    	DBScreen.setText("Remote Search by City");
	    }
    	if ("ByZip".equals(e.getActionCommand())) {
    	DBScreen.setText("Search by Zip");
    	}
    	if ("ByAdd".equals(e.getActionCommand())) {
    	DBScreen.setText("Search by Address");
    	}
    	if ("ImportAll".equals(e.getActionCommand())) {
    	DBScreen.setText("Import All Datas from the server");
    	}
    	if ("ImportHeadersOnly".equals(e.getActionCommand())) {
    	DBScreen.setText("Fast Data Headers Import Only");
        } 
          
    }

    //Create a new internal frame.
    protected void createFrame() {
    	Internalguichild frame = new Internalguichild();
	        frame.setVisible(true); //necessary as of 1.3
	        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        frame.setEnabled(true);//Selected(true);
    }
    
    protected void ShowRSresultsFrame(String RSkey,String Options){
    	// recover the input from search gui
    	// here must first check the existence of the Workspace and creat it and screen it in the first search frame
    	// it's better to do it in the frame constructor (need methods ISworkspaceExist() and CreateWorkspace()--> Jchoosebox)
    	//Create an Instance of RFSEngine-->provide (WClient) 
		RFSEngine rfseng =  new RFSEngine();
		/*
		 *  prepare the search matches and extract the data from the exact match into a String matrix
		 */
		Vector<Vector<String>> SR_MatrixStr = rfseng.preparesearchMatches(RSkey);
		
		int indr=0;
		// Data screening
		 Vector<Vector<String>> datatoscreen = new Vector<Vector<String>>();
		 operationstextarea.setRows(SR_MatrixStr.size());
		 
		 for (Vector<String> rr: SR_MatrixStr){
			 //int indc=0;
			 operationstextarea.append("row Data :"+indr+"---->"+rr.toString()+"\n");
			 Vector<String> rowtoscreen =new Vector<String>();
			 for(String cc: rr){
				 //if(indc>0){
					 rowtoscreen.add(cc);
					// }
					 
					// indc++;
			 }
			 datatoscreen.add(rowtoscreen);
			 indr++;
		 }
		 
		 
		 
		
		
    	// Create the new RSresultFrame() 
		 new RSresultFrame(datatoscreen,SR_MatrixStr, rfseng,RSkey); 
		 
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        InternalGUI frame = new InternalGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(new JScroll);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	
    /*	Before Starting the Main REAM GUI The REAM Engine (REAM_MainEngine) should
     *  first check for existence of the REAM workspace (Database, EnvPath)
     *  If they don't exist, then invite the user to create a REAM workspace by choosing a Path
     *  and create the env_path_variables %REAM_app_loc and %REAM_workspace_loc.
     *    Then Run the REAM_MainGUI()
     */
    	
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                reamGUI.InternalGUI.createAndShowGUI();
            }
        });
    }

	
}
