package reamStartup;

import java.io.InputStream;

public class UtilEvents {
	
	
	
	public void CreateWsVarenv(String Varname, String chemin, String nom) {
		execBackgroundShellProcess("setx /S /M "+Varname+" \" "+chemin+" \" -m "); // ajout de variable Systeme WS or LDB
		execBackgroundShellProcess("setx "+Varname+" \" "+chemin+" \" ");    // ajout de variable utilisateur WS or LDB
       
	}
	
	
	
	public Process execBackgroundShellProcess(String cmd) {
		Process pret = null;
		  if(cmd.compareToIgnoreCase("")!=0)  {
		          	  try {
		          		  pret = Runtime.getRuntime().exec(cmd);
						  InputStream      in = pret.getInputStream();
						  StringBuilder build = new StringBuilder();
						  //on demarre la lecture
						  char c = (char) in.read();
						  while (c != (char) -1){
							  build.append(c);
							  c = (char) in.read();
							  }
						  
						  //on recupere le tout
						  @SuppressWarnings("unused")
						String OutPut = build.toString();
						  
						  //attente jusqua la fin du processus: on sait jamais
						  pret.wait();
						 // pret.waitFor();
						  				 
						              } catch(Exception e) {
						            	  /*System.out.append("erreure d'execution :" + cmd +"\n"+e.toString());*/
						         }
						  }
		  else {
			  //System.out.append("\n"+"Il faut taper une commande !");  
			}
		
		  return pret;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
