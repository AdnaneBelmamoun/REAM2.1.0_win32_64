package reamStartup;

	import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

	public class AskUser extends JDialog {

		private static final long serialVersionUID = 1L;
		private boolean UserAnswer_YesInstall=false ;

		
		public AskUser(JFrame parent, String title, String message,String icon_path){
			super(parent, title, true);
			parent.setVisible(false);
			// set the position of the window
			Point p = new Point(400, 400);
			setLocation(p.x, p.y);
			//setIconImage(Toolkit.getDefaultToolkit().getImage("/root/workspace/CRMLSConnect/src/mainREAMstart/info.png"));
			//setIconImage(Toolkit.getDefaultToolkit().getImage("FD.jpg"));
			/*try {
				setIconImage((Image)ImageIO.read(new URL("file:///root/workspace/CRMLSConnect/src/mainREAMstart/FD.jpg")));
				//setVisible(true);
			} catch (IOException e1) {e1.printStackTrace();	}*/
			//ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("/info.png")); 
			//setIconImage();
			Image iii=null;		
try {
	 iii=ImageIO.read(new URL(icon_path));//"file:///root/workspace/CRMLSConnect/src/mainREAMstart/info.png"));
} catch (MalformedURLException e1) {e1.printStackTrace();
} catch (IOException e1) {e1.printStackTrace();}
			
			final JOptionPane AskuserToCreateWS = new JOptionPane(
				    message,
				    JOptionPane.QUESTION_MESSAGE,
				    JOptionPane.YES_NO_OPTION,new ImageIcon(iii));
			/*final JDialog dialog = new JDialog(parent, 
                    "REAM Starter Engine --> create REAM Workspace ",
                    true);*/
           setContentPane(AskuserToCreateWS);
		   setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		   addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent we) {
			//System.out.println("Window event :\n"+ we.getNewState()+"\n"+we.getSource().toString());
		}
		});
		   	AskuserToCreateWS.addPropertyChangeListener(
		new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent e) {
		   String prop = e.getPropertyName();
		   @SuppressWarnings("unused")
		Object src = e.getSource();
		 //  System.out.println("property name : "+ prop);
		 //  System.out.println("src name : "+ src.getClass());
		 //  System.out.println("JOptionPane.VALUE_PROPERTY : "+ JOptionPane.VALUE_PROPERTY);
		   if (isVisible() 
		    && (e.getSource() == AskuserToCreateWS)
		    && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
		       //If you were going to check something
		       //before closing the window, you'd do
		       //it here.
			  
		       setVisible(false);
		   }
		}
		});
		pack();
		setVisible(true);
		
		int value = ((Integer)AskuserToCreateWS.getValue()).intValue();
		if (value == JOptionPane.YES_OPTION) {
			System.out.print("YES option selected");
			setUserAnswer_YesInstall(true);
		} else if (value == JOptionPane.NO_OPTION) {
			System.out.print("No option selected");
			setUserAnswer_YesInstall(false);
			// here the system must exit and leave a helping message to user
			// telling him that a workspace directory is necessary and must be
			// created or defined before any further processing.
			// please refer to Ream author:
			
				}
		
		}
		
	/*	public AskUser(JFrame parent, String title, String message) {
			super(parent, title);
			// set the position of the window
			Point p = new Point(400, 400);
			setLocation(p.x, p.y);

			
			// Create a message
			JPanel messagePane = new JPanel();
			messagePane.add(new JLabel(message));
			// get content pane, which is usually the
			// Container of all the dialog's components.
			getContentPane().add(messagePane);

			// Create a button
			JPanel buttonPane = new JPanel();
			JButton button = new JButton("yes");
			buttonPane.add(button);
			// set action listener on the button
			button.addActionListener(new MyActionListener());
			getContentPane().add(buttonPane, BorderLayout.PAGE_END);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pack();
			setVisible(true);
		}
	*/
/*
		// override the createRootPane inherited by the JDialog, to create the rootPane.
		// create functionality to close the window when "Escape" button is pressed
		public JRootPane createRootPane() {
			JRootPane rootPane = new JRootPane();
			KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
			Action action = new AbstractAction() {
				
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					System.out.println("escaping..");
					setVisible(false);
					dispose();
				}
			};
			InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(stroke, "ESCAPE");
			rootPane.getActionMap().put("ESCAPE", action);
			return rootPane;
		}
*/
		// an action listener to be used when an action is performed
		// (e.g. button is pressed)
	/*	class MyActionListener implements ActionListener {

			//close and dispose of the window.
			public void actionPerformed(ActionEvent e) {
				System.out.println("disposing the window..");
				setVisible(false);
				dispose();
			}
		}
*/
		public static void main(String[] a) {

		//	String REAM_WS_path = "";
			String userdir = System.getProperty("user.dir");
			System.out.println(" userdir PROPERTY : "+ Paths.get(userdir).toAbsolutePath().toString().replace("\\","/"));
			String infoicon_path = Paths.get(userdir).toAbsolutePath().toString().replace("\\","/")+"/icons/info.png";//src/mainREAMstart/FD.jpg"; 
			final JFrame jf = new JFrame();
try {
	Image im = ImageIO.read(new File(infoicon_path));//(new URL(infoicon_path));
	jf.setIconImage(im);
    } catch (IOException e) {e.printStackTrace();}			
			jf.setVisible(true);
			
			String msg = "The REAM Workspace does not Exist "
					+ "in your system.\n"
				    + "In order to use REAM you need to define \n "
				    + "a location for the REAM Workspace \n \n"
				    + "      Do you want to create it?         ";
			
			
			@SuppressWarnings("unused")
			AskUser dialog = new AskUser(jf, "REAM Starter Engine", msg,infoicon_path);//,icon_path
			
	    
			System.gc();
			System.exit(0);
			
	
	}

		public boolean isUserAnswer_YesInstall() { return UserAnswer_YesInstall;}
		public void setUserAnswer_YesInstall(boolean userAnswer_YesInstall) {
			UserAnswer_YesInstall = userAnswer_YesInstall;
			}
	}
	