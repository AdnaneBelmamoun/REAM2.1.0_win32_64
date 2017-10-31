package reamStartup;


import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.jetty.util.URIUtil;

import com.gargoylesoftware.htmlunit.util.NameValuePair;

import reamGUI.InternalGUI;




public class REAM_MainEngine extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Properties Sys_properties;
	private Map<String,String> Sys_environement;
	private String OSname;
	private String Current_dirpath;
	private String ws_path;
	private String ldb_path;
	private boolean REAMWS_ready =false;
	private boolean REAMLDB_ready=false;
	private boolean REAM_Full_ready=false;
	private UtilEvents eventsutilies = new UtilEvents();
	
	final String reamWS_name = "reamWS";
	final String reamLDB_name = "reamLDB";
	final String ldb_RootFile_name = "RootLDBdef.xml" ;
	final String REAMSF_name = "REAM_SF.ream";
	
	
	private REAM_MainEngine(){
		
		/*
		 *  step 0:  check existence of the JDK on the current system:
		 *  if(JDK exist)  then retrieve the system properties of the current
		 *   					JDK installation and the Java_Path.
		 *  if(JDK not exist ) then send Jdialogue message to install jdk7 and higher.
		 *  					and exist safely REAM.     
		 */
	
		if(!System.getProperty("java.library.path").isEmpty()){
			this.Sys_properties = System.getProperties();
			this.Sys_environement = System.getenv();
		}
		
		/*	Before Starting the Main REAM GUI The REAM Engine (REAM_MainEngine) should
	     *  first check for existence of the REAM workspace (Database, EnvPath)
	     *  If they don't exist, then invite the user to create a REAM workspace by choosing a Path
	     *  and create the env_path_variables %REAM_app_loc and %REAM_workspace_loc.
	     *    Then Run the REAM_MainGUI()
	     */
		File ff = new File(Sys_properties.getProperty("java.class.path"));
		
		if(ff.getParentFile().getPath().contains(";")){
			String [] Current_directory_path_tab = ff.getParentFile().getPath().split(";");
			this.setCurrent_dirpath(Paths.get((String)Current_directory_path_tab[0]).toFile().getParent());
		}else{
			this.setCurrent_dirpath(ff.getParentFile().getPath());
		}
	    // new REAM start Procedure based on local Setting file.
		System.out.println("REAM current execution directory path  --> " + this.getCurrent_dirpath());
		//System.out.println("REAM current execution directory path  --> " + ff.getAbsolutePath());
		
				
	/*  Deprecated Method: 
	 * 	
		// check existence of REAM_SF:
		File REAMSF = new File(this.getCurrent_dirpath()+"/"+REAMSF_name) ;
		if(!REAMSF.exists()){
			// Initiate User Ask for new REAMSF creation
			// user must choose a location for the REAM_Workspace:
			// launch JFileChooser if user want to create database
			// and abort application if not.
			
			
			
		}
		
		*/
		
		
		/*
		 *  Getting the environment
		 */
		
		//	Map<String, String> env = System.getenv();
		//this.Sys_environement = System.getenv();
		
		
		/* check Existence of JDK on the system*/
		
		/*
		 * Second step is to detect the Operating System we are on.
		 */
	    String osNAME = System.getProperty("os.name");
	
		if(osNAME.contains("Windows")){
			if(this.Sys_environement.containsKey(this.reamWS_name)){
		    	this.setWs_path(this.Sys_environement.get(this.reamWS_name));

		    	System.out.println("Ws exising var env "+ this.reamWS_name +" :  "+this.Sys_environement.get(this.reamWS_name));

		    	File fwstemp = new File(this.getWs_path().trim());
		    	Path wspath =  fwstemp.toPath();
		    	
		    	boolean wsdirexists = Files.isDirectory(wspath,LinkOption.NOFOLLOW_LINKS);
		    	
		    	if(wsdirexists){
		    		System.out.println("the diectrory Ws exist  :  "+this.Sys_environement.get(this.reamWS_name));
					// ****************************************
		    		this.setREAMWS_ready(true);
		    		//*****************************************

		    		if(this.Sys_environement.containsKey(this.reamLDB_name)){
			    		/* reamLDB Env var exist , get the actual defined REAMLDB path*/
		    			this.setLdb_path(this.Sys_environement.get(this.reamLDB_name));
			    		
			    		
				        /*  then we can check the consistency of the REAM LDB:
				    	 check if reamLDB_Path really exist 
			    		+ exception handling */
		    			File fldbtemp = new File(this.getLdb_path().trim());
				       	Path ldbpath =  fldbtemp.toPath();
		    			//Path ldbpath = Paths.get(this.getLdb_path().split(":")[0],
  			            //      this.getWs_path().split(":")[1]);
			    		boolean LDBdirexists = Files.isDirectory(ldbpath,LinkOption.NOFOLLOW_LINKS);
				    	if(LDBdirexists){
				    		
				    		
				    		
			    			//*****************************************
			    			this.setREAMLDB_ready(true);
			    			//*****************************************
			    			
			    			
			    			/*
			    			 * 
			    			 *  At this point the REAM APP is Assumed fully ready to use
			    			 * 
			    			 */
			    			
			    			//*****************************************
			    			this.setREAM_Full_ready(true);
			    			//*****************************************
				    		
				    		
				        }
		    		}
				
	    		 }
				
				
			}
	    	
	    	
	    }
	    
	    
	    /**
	     *  Call For REAMEngineStarter Method
	     *  To fix REAM until  REAM_Full_ready = true
	     **/
	    while(!this.isREAM_Full_ready()){
	    	
	    	System.out.println("REAM not yet ready  -->  probably the LDB need to be configured");
	    	REAM_EngineStarter(osNAME);
	    }
	    if(this.isREAM_Full_ready()){
	    	System.out.println("REAM is totally ready");
	    	
	    	JOptionPane.showMessageDialog(this, "Thank you For using Real Estate Assitant Manager REAM V2.2");
			
			/** *********************************************** **/
			/** ****** We can here call The internal GUI  ***** **/
			/** *********************************************** **/
			
			//InternalGUI IGUI = new InternalGUI();
	    	
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater( new Runnable() {
	            public void run() {
	            	reamGUI.InternalGUI.createAndShowGUI();
	            }
	        });
	    	
	    	
	    }
	    
	    
	    
	
	
		
	}
	
	

	private int LinuxUbuntux86_install(String currpath) {
		int install_Result =0;
		Runnable task_install_linux = new REAMInstallGUI(currpath,"linux_X86");
	    Thread worker = new Thread(task_install_linux);
	    // We can set the name of the thread
	    worker.setName("Install_Linux_X86");//+nb_taskImpAll);
	    // Start the thread, never call method run() direct
	    worker.start();
		
		
		return install_Result;
		
	}
	
/*	private boolean isDirectoryPathexist(String p){
		return Files.isDirectory(Paths.get(p),LinkOption.NOFOLLOW_LINKS);
	}
*/

	private void REAM_EngineStarter(String osNAME){
		
		/* ***********************************************************************
		 * 
		 *                     The Windows OS Case
		 * 
		 ************************************************************************* */
		

		/* *************************************************************
		 *  First things first ====> Get the Host System Environment
		 * ************************************************************* */

        /* check Existence of JDK on the system*/
        /* if JDK Exist Then check the Type of OS:*/
        if(!System.getProperty("java.library.path").isEmpty()){
        	//JDK exist then proceed to next step
			
        	/* if os.name = windows 7 /8 /10 */
			if(osNAME.contains("Windows")){
				//String sys_protocol = "";
				//String askuser_iconname = "info.png";
			    String sys_fileseparator = System.getProperty("file.separator");
			    
			    /* here check if REAM VarEnvs (REAMWS= workspace path ; REAMLDB= local database path) */
			    //String reamWS_name = "reamWS";
			    //String reamWS_Path = "";
			    //String reamLDB_name = "reamLDB";
			    //String reamLDB_Path = "";
			    if(this.Sys_environement.containsKey(this.reamWS_name)){
			    	/*	 reamWS Env var exist , get the actual defined REAM WS path*/
			    	//reamWS_Path = MainEgin.Sys_environement.get(reamWS_name);
			    	this.setWs_path(this.Sys_environement.get(this.reamWS_name));
			    	
			    	//MainEgin.Sys_environement
			    	
			    	/*	 then we can check the consistency of the REAM WS:
			    	 check if path really exist 
			    	 + exception handling*/
			    	//boolean wsdirexists = Files.isDirectory(Paths.get(reamWS_Path),LinkOption.NOFOLLOW_LINKS);
			    	
			    	File ftemp = new File(this.getWs_path().trim());
			    	Path wspath =  ftemp.toPath();
			    	boolean wsdirexists = Files.isDirectory(wspath,LinkOption.NOFOLLOW_LINKS);
			    	if(wsdirexists){
			    		/*	Run consistency check if really reamWS_Path contains files
				    	 * + exception handling
				    	 * */
				    	
				    	/*	 (check if any missing file in the WS (Optional))
				    	 * + exception handling
				    	 * */
			    		/*
			    		 * We Zap the consistency check for this project ... next upgrade
			    		 * 
			    		 * ----------> At this level we assume the REAMWS is ready:
			    		*/
			    		
			    		// ****************************************
			    		this.setREAMWS_ready(true);
			    		//*****************************************
			    		
			    		System.out.println("Proceeding to check LDB");
			    		
			    		if(this.Sys_environement.containsKey(this.reamLDB_name)){
				    		/* reamLDB Env var exist , get the actual defined REAMLDB path*/
			    			this.setLdb_path(this.Sys_environement.get(this.reamLDB_name));
				    		
			    			System.out.println("Now checking the LDB");
					        /*  then we can check the consistency of the REAM LDB:
					    	 check if reamLDB_Path really exist 
				    		+ exception handling */
					    	File fldbtemp = new File(this.getLdb_path().trim());
					       	Path ldbpath =  fldbtemp.toPath();
				    		boolean LDBdirexists = Files.isDirectory(ldbpath,LinkOption.NOFOLLOW_LINKS);
					    	if(LDBdirexists){
					    		System.out.println(" LDB exist now");
					    		
					    		
					    		
/** ************************************************************************************************************************************************* **/					    		
					    		
					    		
				    		/* then check if really reamLDB_Path contains the file RootLDBdef.xml
				    		+ exception handling*/
					    		String ldbrootpath = this.getLdb_path()+sys_fileseparator+this.ldb_RootFile_name;
					    		@SuppressWarnings("unused")
								boolean ldbRootFileexists = Files.exists(Paths.get(ldbrootpath),LinkOption.NOFOLLOW_LINKS);
					    		
					    	//	if(ldbRootFileexists){
					    			/* The LDBRootDef.xml file exist
					    			 * we can proceed to check if the DB is out dated
					    			 * Check Last time LDB was Updated and ASK user to check 
					    			 * and/or Update LDB definitions
					    			 * 
					    			 * -----> + exception handling
					    			 *  
					    			 * ======> We can add a method public boolean CheckDBdef(String ldbRootpath)
					    			 *  that returns true if the DB is out dated and need update.
					    			 *  This feature will appear in the next version upgrade.
					    			 *  For now we suppose The REAMWS and LDBWS existence are enough
					    			 *  To start an instance of REAM InternalGUI 
					    			 *   
					    			 *  A the End if everything is successful then  :
						    		 *   if exist then We ASSUME REAM is already installed so we can proceed to call the REAMInternalGUI.
						             *   start REAMInternalGUI
						             *   
						             *   =======> To Start The REAM GUI : We need to have the values :
						             *                  REAMWS_ready <-- True.
						             *                  REAMLDB_ready <-- True. 
						    		 */
					    			
/** ************************************************************************************************************************************************* **/					    						    			
					    			//*****************************************
					    			this.setREAMLDB_ready(true);
					    			//*****************************************
					    			
					    			
					    			/*
					    			 * 
					    			 *  At this point the REAM APP is Assumed fully ready to use
					    			 * 
					    			 */
					    			
					    			//*****************************************
					    			this.setREAM_Full_ready(true);
					    			//*****************************************
					    			
					    			JOptionPane.showMessageDialog(this, "Thank you For using Real Estate Assitant Manager REAM V2.2");
					    			
					    			/** *********************************************** **/
					    			/** ****** We can here call The internal GUI  ***** **/
					    			/** *********************************************** **/
					    			
					    			//InternalGUI IGUI = new InternalGUI();
					    	    	
					    	        //Schedule a job for the event-dispatching thread:
					    	        //creating and showing this application's GUI.
					    	        javax.swing.SwingUtilities.invokeLater( new Runnable() {
					    	            public void run() {
					    	            	reamGUI.InternalGUI.createAndShowGUI();
					    	            }
					    	        });
					    			/*
					    			 * 
					    			 *   After This Point We only handle the exceptions:
					    			 *   Either: 
					    			 *   ---> install WS / LDB 
					    			 *   or 
					    			 *   ---> correct WS / LDB installation
					    			 *   or
					    			 *   ---> Abort and Exit REAM
					    			 * 
					    			 */
					    			 
					    			
					    			
					    //		}else{
					    			/* 
					    			 *  Fire exception to correct The LDB Install by generating a new Definition File 
					    			 *   RootLDBdef.xml in the ReamLDB Path.
					    			 *   such procedure will have to read the hole database definitions if not lost, or just 
					    			 *   Read everything or install LDB again
					    			 */
					    			
					    //		}
				    		
					    	
				    		
					    	
				    		
				    		
				    		
					    	
					    	}else{
					    		
					    		/* fire Exception handling for non existing path defined in the LDB Var Env
					    		 * Probably need to fix the REAMLDB install by updating the REAMLDB EnvVar to 
					    		 * an existing REAMLDB Path.
					    		 */
					    		// boolean fixREAMLDB(String newLDBpath){return LDBfixStatus;}
											    		/*
											    		 *  A the End if everything is successfull then  :
											    		 *   if exist then REAM is already installed so we can proceed to call the REAMInternalGUI.
											             *   start REAMInternalGUI
											             *   
											             *   =======> To Start The REAM GUI : We change the values :
											             *                  REAMWS_ready <-- True.
											             *                  REAMLDB_ready <-- True.  
											             *   
											             *   
											    		 */
					    		// or
					    		// AbortREAM()
					    		
					    		
					    		
					    		// here we should handle the exception of missing LDB directory 
					    		// LDB directory need to be selected by user again and created
					    		// don't forget to reupdate the reamWS var env
					    		
					    	}
					    		
				    		
				    	}else{
				    		/* fire exception 2 to create REAMLDB var env */
				    		
				    		   
						    // if var envs don't exist on host OS, then proceed to create them by installing REAM again or fixing installation.
						    
						        // we can ask the user to select path   
						    
						        // then we use the selected path to create the env vars:
						    
				    		    // boolean CreateREAMLDB(String userSelectdLDBpath){return LDBcreationStatus;}
				    		
						        // once everything is successful: then we can start REAMInternalGUI
													    		/*
													    		 *  A the End if everything is successfull then  :
													    		 *   if exist then REAM is already installed so we can proceed to call the REAMInternalGUI.
													             *   start REAMInternalGUI
													             *   
													             *   =======> To Start The REAM GUI : We change the values :
													             *                  REAMWS_ready <-- True.
													             *                  REAMLDB_ready <-- True.  
													             *   
													             *   
													    		 */
				    		
				    		// Or AbortREAM()
				    		
				    		
						    
					    	/** Start a JoptionPane: 
					    	 * if user is OK ---> Start install by calling  Jfilechooser 
					    	 * if user select selectedwspath ---> private boolean CreateReamWS(String selectedwspath)
					    	 * if ReamWS created successfully ----> recall Engine Starter to check the LDB part
					    	 * else ReamWS not created ---> Send message to USER about non handled exception then Abort. 
					    	 * 
					    	**/
				    		
				    		// int getOptionspane(Object[] options,String msg, String title,int msgtype)
					    	
					    	Object[] LDBoptions = {"Cancel", "Confirm"};
							final int installLDBoptions = JOptionPane.showOptionDialog(this,  "REAM Local Data Storage does not exist on your system\n"
					    		    + "Please Confirm for proceeding to install the Local Data Storage \n",
					    		      "Proceeding to install the REAM Local Data Storage",
					    	 		    JOptionPane.YES_NO_OPTION,
					    	 		    JOptionPane.QUESTION_MESSAGE,
					    	 		    null, // Default Icon 
					    	 		   LDBoptions,
					    	 		  LDBoptions[1]);
							System.out.println("user LDB option selection:  "+installLDBoptions);
							
					    	  if(installLDBoptions != 1 ){
					    		  System.exit(ABORT);
					    	  }
					    	  
					    	  if(installLDBoptions == 1){
					    		  
					    		  System.out.println("User Confirmed the LDB install");
					    		  /*
					    		   * user confirmed the installation so we can proceed:
					    		   *  -> Step1: Fire a JfileChooser Event
					    		   */
					    		  
					    		  // public firefilechooser(String currentpath)
					    	   	  //Create a file chooser
					    		  final JFileChooser fc = new JFileChooser();
					    		  File userLDBSelection = null;
					    		  fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					    		  fc.setSelectedFile(new File(this.getCurrent_dirpath()));
					    		  //In response to a button click:
					    		  int returnVal = fc.showOpenDialog(this);
					    		  if (returnVal == JFileChooser.APPROVE_OPTION) {
					    			  userLDBSelection = fc.getSelectedFile();	    	 
						    	      System.out.println("Opening LDB Directory path: " + userLDBSelection.getName() + "---->    "+ userLDBSelection.getPath());
						    	      System.out.println("Building LDB var env : " + this.reamLDB_name + "---->    "+ userLDBSelection.getAbsolutePath());
						    	      // here install the app in background 
						    	      // create the persistent  varenv REAMWS.
						    	      (this.eventsutilies).CreateWsVarenv(this.reamLDB_name, userLDBSelection.getAbsolutePath(), userLDBSelection.getName());
						    	      
						    			//*****************************************
						    			this.setREAMLDB_ready(true);
						    			//*****************************************
						    			
						    			
						    			/*
						    			 * 
						    			 *  At this point the REAM APP is Assumed fully ready to use
						    			 * 
						    			 */
						    			
						    			//*****************************************
						    			this.setREAM_Full_ready(true);
						    			//*****************************************
						    			
						    	      
						    	
						    	 }
						    	else{
						    		System.out.println("User Aborted Choosing LDB location ");
						    		
						    	}
						    	       
					    		  
					    		  
					    		  
					    	  }
				    		
				    		
				    		
				    		
						    
				    		
				    	}
			    		
			    		
			    		
			    	}else{
			    		/* fire Exception handling for non existing path defined in the WS Var Env
			    		 * Probably need to fix the REAMWS install by updating the REAMWS EnvVar to 
			    		 * an existing REAMWS Path.
			    		 */
			    		// boolean FixREAMWS(){return WSFixStatus;}
			    		/*
			    		 *  A the End if everything is successfull then  :
			    		 *   if exist then REAM is already installed so we can proceed to call the REAMInternalGUI.
			             *   start REAMInternalGUI
			             *   
			             *   =======> To Start The REAM GUI : We change the values :
			             *                  REAMWS_ready <-- True.
			             *                  REAMLDB_ready <-- True.  
			             *   
			             *   
			    		 */
						// or
						// AbortREAM()
					    		
			    		
			    	}
			    	
			    	
			    	
			    	
			    	
			    }else{
			    	/* fire exception 1 to create REAMWS var env */
			    	
					   
				    // if var envs don't exist on host OS, then proceed to create them by installing REAM again or fixing installation.
				    
				        // we can ask the user to select path   
				    
				        // then we use the selected path to create the env vars:
				    
				        // once everything is successful: then we can start REAMInternalGUI
				    
			    	// boolean InstallREAM(){return REAMInstallStatus;}
		    		/*
		    		 *  A the End if everything is successfull then  :
		    		 *   if exist then REAM is already installed so we can proceed to call the REAMInternalGUI.
		             *   start REAMInternalGUI
		             *   
		             *   =======> To Start The REAM GUI : We change the values :
		             *                  REAMWS_ready <-- True.
		             *                  REAMLDB_ready <-- True.  
		             *   
		             *   
		    		 */
					// or
					// AbortREAM()
				    
			    	/** Start a JoptionPane: 
			    	 * if user is OK ---> Start install by calling  Jfilechooser 
			    	 * if user select selectedwspath ---> private boolean CreateReamWS(String selectedwspath)
			    	 * if ReamWS created successfully ----> recall Engine Starter to check the LDB part
			    	 * else ReamWS not created ---> Send message to USER about non handled exception then Abort. 
			    	 * 
			    	**/
			    	
			    	Object[] options = {"Cancel", "Confirm"};
					final int installWSoptions = JOptionPane.showOptionDialog(this,  "REAM is not installed on your system\n"
			    		    + "Please Confirm or Cancel the installation\n",
			    		      "Proceeding To install REAM",
			    	 		    JOptionPane.YES_NO_OPTION,
			    	 		    JOptionPane.QUESTION_MESSAGE,
			    	 		    null, // Default Icon 
			    	 		    options,
			    	 		    options[1]);
					System.out.println("user option selection:  "+installWSoptions);
					
			    	  if(installWSoptions == 0){
			    		  System.exit(ABORT);
			    	  }
			    	  
			    	  if(installWSoptions == 1){
			    		  
			    		  System.out.println("User Confirm the install");
			    		  /*
			    		   * user confirmed the installation so we can proceed:
			    		   *  -> Step1: Fire a JfileChooser Event
			    		   */
			    	   	  //Create a file chooser
			    		  final JFileChooser fc = new JFileChooser();
			    		  File userSelection = null;
			    		  fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    		  fc.setSelectedFile(new File(this.getCurrent_dirpath()));
			    		  //In response to a button click:
			    		  int returnVal = fc.showOpenDialog(this);
			    		  if (returnVal == JFileChooser.APPROVE_OPTION) {
			    			  userSelection = fc.getSelectedFile();	    	 
				    	      System.out.println("Opening WS Directory path: " + userSelection.getName() + "---->    "+ userSelection.getAbsolutePath());
				    	      System.out.println("Building WS var env : " + this.reamWS_name + "---->    "+ userSelection.getAbsolutePath());
				    	      // here install the app in background 
				    	      // create the persistent  varenv REAMWS.
				    	      (this.eventsutilies).CreateWsVarenv(this.reamWS_name, userSelection.getAbsolutePath(), userSelection.getName());
				    	      
					    		// ****************************************
					    		this.setREAMWS_ready(true);
					    		//*****************************************
				    	      
				    	
				    	 }
				    	else{
				    		System.out.println("User Abort Choosing File ");
				    		
				    	}
				    	       
			    		  
			    		  
			    		  
			    	  }
			    	
			    	
			    	
			    }
			    

			
		    }
			
     }/* This parenthesis End has to  be relocated at the end of all processing 
			  * and add an exception handling for non existing JDK
	          * either ask user to install JDK with a specific version (for this project = 1.8) 
	          * or make procedure to download and install it
	          * with the user permission of course:
	          */
        
        
        
        
        
        
	
		
		
		/* ***********************************************************************
		 * 
		 *                     The Linux OS Case
		 * 
		 ************************************************************************* */	

		/*
		 * First step is to detect the Operating System we are on.
		 */		
       // String osNAME = System.getProperty("os.name");
	
        /* if os.name = linux*/
        if(osNAME.contains("linux")){
		
		
		System.out.println(System.getenv().toString());
				//getnv("TEST").toString());
		 String sys_arch = System.getenv("_system_arch").toString();
		 String sys_type = System.getenv("_system_type").toString();
		 String sys_name = System.getenv("_system_name").toString();
		 String Shell_path = System.getenv("SHELL").toString();
		 String sys_wd = System.getenv("PWD").toString();
		 //String Ream_ws_sysenvvar = System.getenv("REAM_WS_Path").toString();
		     System.out.println("Archi --------->: "+sys_arch);
			 System.out.println(sys_type);
			 System.out.println(sys_name);
			 System.out.println(Shell_path);
			 System.out.println(sys_wd);
		
		 if(sys_type.equalsIgnoreCase("Linux")){
			 if(sys_name.equalsIgnoreCase("Ubuntu")){
				 String sys_protocol = "file://";
				 String askuser_iconname = "info.png";
				 String sys_separator = "/";
				 // Do ubuntu env var writing procedure
				 //System.out.println("--------->: "+sys_arch);
				// System.out.println(sys_type);
				 //System.out.println(sys_name);
				 //System.out.println(Shell_path);
				 //System.out.println(sys_wd);
				 
				 Map<String,String> env = GetEnv.getEnvironment();
			        
			        String wsvarname ="REAMlws";
			        if(env.containsKey(wsvarname)){
			        	System.out.println("***"+wsvarname + "=" + env.get(wsvarname));
			        }else{
			        	System.out.println( "The key " + wsvarname + "  don't exist in this OS"
			        			+ " need to create or define the REAM workspace.");
			        	
			        	// ASKuser to install
			        	
			        	String icon_path = sys_protocol+getCurrent_dirpath()+sys_separator+askuser_iconname;
			        	
			        setTitle(getCurrent_dirpath());
			        	
			        	setDefaultCloseOperation(REAM_MainEngine.EXIT_ON_CLOSE);
			        	setVisible(false);
			        	try {
			        		Image im = ImageIO.read(new URL(icon_path));//"src/mainREAMstart/FD.jpg" //file:///root/workspace/CRMLSConnect
			        		setIconImage(im);
			        	    } catch (IOException e) {e.printStackTrace();}			
			        				setVisible(false);
			        				
			        				String msg = "The REAM Workspace does not Exist "
			        						+ "in your system.\n"
			        					    + "In order to use REAM you need to define \n "
			        					    + "a location for the REAM Workspace \n \n"
			        					    + "      Do you want to create it?         ";
			        				
			        				
			        				AskUser ask2installdialog = new AskUser(this, "REAM Starter Engine for OS "+sys_type, msg,icon_path);//,icon_path
			        				
			        				if(ask2installdialog.isUserAnswer_YesInstall()){
			        					//initiate install process on linux ubuntu 
			        					LinuxUbuntux86_install(getCurrent_dirpath());
			        					
			        				}else{
			        					System.exit(0);
			        				}
			        				
			        	
			        }
				 
			 }else{
				 
				    //send Message Box "To install Ream on +sys_name+ OS Call the
				    //  REAM software programmer.
				  
				 
			 }
		 }
		 
		 if(sys_type.equalsIgnoreCase("OSX")){
				 // Do Mac OSX  Posix env var writing procedure
                 
			 
			 }else{
				 
				  // send Message Box "To install Ream on this OS Call the
				  //    REAM software programmer.
				  
				 
			 }
		
        }
        
        
        

		
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		

		 new REAM_MainEngine(); 
		
		
		
		
		
		

	}



	public String getCurrent_dirpath() {return Current_dirpath;	}
	public void setCurrent_dirpath(String current_dirpath) {Current_dirpath = current_dirpath;	}

	public String getWs_path() {return ws_path;	}
	public void setWs_path(String ws_path) {this.ws_path = ws_path;}

	public String getLdb_path() {return ldb_path;	}
	public void setLdb_path(String ldb_path) {this.ldb_path = ldb_path;	}



	public String getOSname() {
		return OSname;
	}



	public void setOSname(String oSname) {
		OSname = oSname;
	}



	public boolean isREAMWS_ready() {
		return REAMWS_ready;
	}



	public void setREAMWS_ready(boolean rEAMWS_ready) {
		REAMWS_ready = rEAMWS_ready;
	}



	public boolean isREAMLDB_ready() {
		return REAMLDB_ready;
	}



	public void setREAMLDB_ready(boolean rEAMLDB_ready) {
		REAMLDB_ready = rEAMLDB_ready;
	}



	public boolean isREAM_Full_ready() {
		return REAM_Full_ready;
	}



	public void setREAM_Full_ready(boolean rEAM_Full_ready) {
		REAM_Full_ready = rEAM_Full_ready;
	}



	public UtilEvents getEventsutilies() {
		return eventsutilies;
	}



	public void setEventsutilies(UtilEvents eventsutilies) {
		this.eventsutilies = eventsutilies;
	}

}
	