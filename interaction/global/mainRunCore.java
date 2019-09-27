package ccc.mainComponent.UI;

import java.awt.Desktop.Action;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.Timer;

import ccc.interaction.global.popUpBox;
import ccc.mainComponent.SocketCore;
import ccc.mainComponent.Systems;
import ccc.mainComponent.globalID;
import internalSecurity.emergencyStop;
import servicePackage.serviceLoader;
import servicePackage.stabilizer;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.imageio.IIOException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JLabel;

public class mainRunCore extends JFrame{

	public JFrame frmAiProject;
	private JTextField textField;
	private static Systems system;
	
	private static boolean startUP = true;
	private JTextArea displayer;
	private String text;
	private Class services;
	private Class stabiliz;
	
	public Timer timer;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField sendServer;
	private JTextField serverIP;
	private JTextField textField_2;
	private static JTextField serverStatus;
	private JLabel lblServerStatus;

	private ServerSocket server;
	private SocketCore sock;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				system = new Systems();
				try {
					mainRunCore window = new mainRunCore();
					window.frmAiProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	private JTextArea textArea;

	/**
	 * Create the application.
	 */
	public mainRunCore() {
		initializeComponent();
		this.frmAiProject.setVisible(true);
		sock = new SocketCore();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeComponent() {
	
		
		frmAiProject = new JFrame();
		frmAiProject.setResizable(false);
		frmAiProject.setTitle("AI6 Project");
		frmAiProject.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				startUP();
				serverIP.setText(SocketCore.getlocalIP());
			}
		});
		frmAiProject.setBounds(100, 100, 800, 480);
		frmAiProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAiProject.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 774, 413);
		frmAiProject.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Console", null, panel, null);
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 764, 341);
		panel.add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		displayer = new JTextArea(5,30);
		displayer.setForeground(Color.BLACK);
		displayer.setBackground(Color.WHITE);
		displayer.setBounds(0, 0, 752, 339);
		panel_1.add(displayer);
		displayer.setEditable(false);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					text = textField.getText();
					disloader(submitString(text));
				}
			}
		});
		textField.setBounds(10, 353, 599, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Submit or Enter");
		btnNewButton.setBounds(619, 352, 129, 23);
		panel.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Chat", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 0, 749, 385);
		panel_2.add(tabbedPane_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("LAN", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 744, 367);
		panel_3.add(tabbedPane_2);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_2.addTab("Client", null, panel_4, null);
		panel_4.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 739, 287);
		panel_4.add(textArea);
		
		sendServer = new JTextField();
		sendServer.setBounds(0, 298, 561, 20);
		panel_4.add(sendServer);
		sendServer.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("Send Text");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnNewButton_1.setBounds(571, 297, 158, 23);
		panel_4.add(btnNewButton_1);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_2.addTab("Server", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 0, 739, 328);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		serverIP = new JTextField();
		serverIP.setEditable(false);
		serverIP.setBounds(97, 11, 177, 20);
		panel_6.add(serverIP);
		serverIP.setColumns(10);
		
		
		JLabel lblServerIp = new JLabel("Server IP : ");
		lblServerIp.setBounds(10, 14, 88, 14);
		panel_6.add(lblServerIp);
		
		textField_2 = new JTextField();
		textField_2.setBounds(97, 42, 177, 20);
		panel_6.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSelectPort = new JLabel("Select Port : ");
		lblSelectPort.setBounds(10, 45, 88, 14);
		panel_6.add(lblSelectPort);
		
		JLabel lblAnyNumberBetween = new JLabel("Any number between 0-255");
		lblAnyNumberBetween.setBounds(284, 45, 162, 14);
		panel_6.add(lblAnyNumberBetween);
		
		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
            startCOMServer();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		btnStartServer.setBounds(10, 103, 162, 23);
		panel_6.add(btnStartServer);
		
		JButton button = new JButton("Stop Server");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(182, 103, 162, 23);
		panel_6.add(button);
		
		lblServerStatus = new JLabel("Server Status :");
		lblServerStatus.setBounds(10, 78, 88, 14);
		panel_6.add(lblServerStatus);
		
		serverStatus = new JTextField();
		serverStatus.setText("Not Running");
		serverStatus.setEditable(false);
		serverStatus.setColumns(10);
		serverStatus.setBounds(97, 73, 177, 20);
		panel_6.add(serverStatus);
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					text = textField.getText();
					disloader(submitString(text));
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = textField.getText();
			
				disloader(submitString(text));
			}
		});
		textField.requestFocusInWindow();

	
		////////////////////////////////////////////////////////////////
		
		
	}
	
	private void startUP() {
		
		if(startUP == true) {
			
			globalID.addID(1.0);
			text = globalID.getId() + "System : loading all network and setting.";
			disloader(text);

			
			
			globalID.addID(0.1);
			text = globalID.getId() + "System : loading inner network loader.";
			try {
				services = Class.forName("servicePackage.serviceLoader");
			
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
			disloader(text);
			
			try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
			
			if(services != null) {
				globalID.addID(0.1);
				text = globalID.getId() + "System : loaded serviceLoader.";
	
				disloader(text);	
				
			}
			
			
			globalID.addID(0.1);
			text = globalID.getId() + "System : loading default stabilizer.";
			
			disloader(text);
			
			try {
				stabiliz = Class.forName("servicePackage.stabilizer");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			if(stabiliz != null) {
				if(stabilizer.getStabilizerValue() == true) {
					globalID.addID(0.1);
					text = globalID.getId() + "System : loaded stabilizer.";
					disloader(text);	
				}
			}else {
				popUpBox.alertError("Couldn't load Stabilizer", "Core File Missing", true);
			}
			
			globalID.addID(0.1);
			text = globalID.getId() + "System : loaded all needed components.";
			disloader(text);
		
		}
	}


	private String submitString(String text) {
		if(text.equals("")) {
			text = "<empty>";
		}
		text = system.submitString(text);
		return text;
	}
	private void disloader(String input) {
		if(globalID.getpureId()!=0.0) {
			displayer.setText(displayer.getText() + "\n" + input);
		}else {
			displayer.setText(input);
		}
	}
	
	public void refreshScreen() {
	
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.setRepeats(true);
		timer.setDelay(17);
		timer.start();
	}
	
	public static void setCOMText(String text) {
		serverStatus.setText(text);
	
	}
	
	public void COMServer() {
		try {
			sock.COMserver = new ServerSocket(sock.comPort,100);
			while(true) {
				try {
				serverStatus.setText("Running and Waiting");
				sock.COMSock = sock.COMserver.accept();
				
				sock.COMoutput = new ObjectOutputStream(sock.COMSock.getOutputStream());
				sock.COMoutput.flush();
				sock.COMinput = new ObjectInputStream(sock.COMSock.getInputStream());
				
				chatting();
				}catch(EOFException e) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	private void chatting() throws IOException{
		String message = "";
		
		try {
			message = (String) sock.COMinput.readObject();
			textArea.append("\n" + message);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
