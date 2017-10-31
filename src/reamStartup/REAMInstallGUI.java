package reamStartup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class REAMInstallGUI extends JFrame implements Runnable, ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JButton WSButton = null;
    private JTextField WSInputfield = null;
	
	public REAMInstallGUI(String currpath, String sys_arch){
          super("REAM Install GUI  <--- Author: Adnane Belmamoun --->");
        
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 150;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
        setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        setBackground(Color.pink);
        
      setResizable(true);
      setVisible(true);
     this.getContentPane().setLayout(new BorderLayout(1,0));
     
     JPanel WS_jp = new  JPanel();
     WS_jp.setPreferredSize(new Dimension(550,100));
     WS_jp.setVisible(true);
     //RS_jp.setBackground(new Color(150));
     WS_jp.setBorder(BorderFactory.createTitledBorder("Select the location where to install REAM software"));//)createEmptyBorder(5,5,5,5));
     WSButton = new JButton("select path >>>");
     //WSButton.setPreferredSize(new Dimension(120, 30));
     WSButton.setActionCommand("select_wspath");
     WSButton.addActionListener(this);
     WSInputfield = new JTextField(currpath+"/REAM");//Ciy / Zip / Address
    // WSInputfield.setPreferredSize(new Dimension(270, 30));
     WSButton.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
     WSInputfield.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
     WS_jp.add(WSButton);
     WS_jp.add(WSInputfield);
     add(WS_jp);
     
	}

	public void run() {
		// TODO Auto-generated method stub
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
