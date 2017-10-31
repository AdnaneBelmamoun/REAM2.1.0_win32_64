package reamGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import redfinBots.RE_Object;

public class ShowREDetailsFrame extends JFrame
							implements ActionListener, ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	private static String RE_Fulladd="";

	public ShowREDetailsFrame(RE_Object REobj){
		
		super("Real Estate #" + RE_Fulladd);
        //Set the window size or call pack...
        setSize(300,300);
        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
      //Create and set up the content pane.
        JPanel newContentPane = new JPanel(new BorderLayout());
        newContentPane.setBorder(BorderFactory.createTitledBorder("Real Estate Details "));
        JTextArea Screenarea= new JTextArea("");
        Screenarea.setPreferredSize(new Dimension(400,600));
        
        Screenarea.setText(REobj.tostring());
        
        
        newContentPane.add(Screenarea);
        newContentPane.setOpaque(true); //content panes must be opaque
        setContentPane(newContentPane);
        setIconImage(createFDImage()); //create an icon from scratch
        
        
        setSize(new Dimension(440, 730));
        setVisible(true);
        pack();
	}
	
	
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Creates an icon-worthy Image from scratch.
    protected static Image createFDImage() {
        //Create a 16x16 pixel image.
        BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        //Draw into it.
        Graphics g = bi.getGraphics();
        g.setColor(Color.green);
        g.fillRect(0, 0, 15, 15);
        g.setColor(Color.RED);
        g.fillOval(5, 3, 6, 6);
        //Clean up.
        g.dispose();
        //Return it.
        return bi;
    }

    //Returns an Image or null.
    protected static Image getFDImage() {
        java.net.URL imgURL = RSresultFrame.class.getResource("FD.jpg");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
