package reamGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import redfinBots.RFSEngine;


public class RSresultFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public RSresultFrame(Vector<Vector<String>> Data, Vector<Vector<String>> FullData,RFSEngine rfseng,String RSkey) {
        super("Search Job #" + (++openFrameCount)+"---> "+RSkey);
        //Set the window size or call pack...
        setSize(300,300);
        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
      //Create and set up the content pane.
        RSresultTabPane newContentPane = new RSresultTabPane(Data,FullData,rfseng);
        newContentPane.setOpaque(true); //content panes must be opaque
        setContentPane(newContentPane);
        setIconImage(createFDImage()); //create an icon from scratch
        
        
        setSize(new Dimension(440, 730));
        setVisible(true);
        pack();
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

        
    }

