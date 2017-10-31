package reamLDB;

import redfinBots.RE_Object;
import redfinBots.RFSEngine;

public class ImportRE_runnable implements Runnable {
	  private final  String REurl;
	  private final  RFSEngine rfsengine;

	  public ImportRE_runnable(String REhref,RFSEngine rfseng) {
	    this.REurl = REhref;
	    this.rfsengine =  rfseng;
	  }

	  public void run() {
		  // here start updating the existing database
		  // note that the DB existence and consistency
		  // has to be checked at the startup.

		  
	    //	String href = (String) REhref.elementAt(0);
			
			RE_Object REobj_temp = new RE_Object(rfsengine.getRF_base_URL(),  this.REurl, rfsengine.getClient());
			
			//  here instead of writing in the output we should write on 
			// the local database
			System.out.println("Importing the RE Object  : \n"+REobj_temp.tostring());
			
			
	    //}
	    
	  }
	} 