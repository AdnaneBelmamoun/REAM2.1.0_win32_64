package reamLDB;

import java.util.Vector;

import redfinBots.RE_Object;
import redfinBots.RFSEngine;
//import gui

public class ImportAll_runnable implements Runnable {
		  private final  Vector<Vector<String>> dataheaders;
		  private final  RFSEngine rfsengine;

		  public ImportAll_runnable( Vector<Vector<String>> D,RFSEngine rfseng) {
		    this.dataheaders = D;
		    this.rfsengine =  rfseng;
		  }

		  public void run() {
			  // here start updating the existing database
			  // note that the DB existence and consistency
			  // has to be checked at the startup.
			  
		   
		    for (int i = 1; i < dataheaders.size(); i++) {
		    	String href = ((Vector<String>)dataheaders.elementAt(i)).elementAt(0);
				
				RE_Object REobj_temp = new RE_Object(rfsengine.getRF_base_URL(), href, rfsengine.getClient());
				//  here instead of writing in the output we should write on 
				// the local database
				System.out.println(REobj_temp.tostring());
				
				
		    }
		    
		  }
		} 