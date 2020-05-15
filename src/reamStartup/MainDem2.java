package reamStartup;

import net.jimmc.jshortcut.JShellLink;

public class MainDem2 {

	JShellLink link;
    String filePath;

    public MainDem2() {
        try {
            link = new JShellLink();
            filePath = JShellLink.getDirectory("")
            } catch (Exception e) {

        }

    }
    
    public void createDesktopShortcut() {

        try {
            link.setFolder(JShellLink.getDirectory("/root/Desktop"));
            link.setName("TestShortcut00");
            link.setPath(filePath);
            link.save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    

	public static void main(String[] args) {
		//MainDem2 sc = new MainDem2();
	     //   sc.createDesktopShortcut();
		System.setProperty("JSHORTCUT_HOME", System.getProperty("user.dir"));
		MainDem2 sc = new MainDem2();
	       sc.createDesktopShortcut();
		//System.out.println(System.getProperty("JSHORTCUT_HOME"));
		//System.out.println(JShellLink.getDirectory(""));
	        
	        

	}

}
