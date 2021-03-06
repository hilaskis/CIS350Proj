package gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Engine.TwitterEngine;

import java.io.*;


@SuppressWarnings("serial")
/**
 * Creates the GUI and runs the application
 * 
 * @author Ben
 */
public class TwitterGUI extends javax.swing.JFrame{

	private String[] options={"Authenticate User",
			"Get Status", "Get UserTimeline","Post Status",
			"Search for Statuses"};
	private JComboBox combo;
	private JPanel eastPanel, westPanel;
	private JFrame GUI;
	private JMenuBar menu;
	private JMenu file, generate, sort, help; 
	private JMenuItem fileExport, fileDeleteTable, fileQuit,
			fileDeleteStatus, generateWordFrequencyList, 
			generateTopTrendingList, help_About;
	private JRadioButtonMenuItem sortByCreation,
			sortByLoginName, sortByDisplayName,
			sortByFreindCount, sortByFollowerCount;
	private ButtonGroup group;
	private TwitterResultsPanel results;
	private AuthenticatePanel authP;
	private GetStatusPanel getStatusP;
	private GetTimePanel getTimeP;
	private PostPanel postP;
	private SearchPanel searchP;
	
	private TwitterEngine engine;
	
	/**
	 * Packs and sets the GUI
	 */
	public TwitterGUI(){
		engine = new TwitterEngine();
		GUI = new JFrame("Twitter Lite");
		menuInit();
		WestPanelInit();
		EastPanelInit();
		GUI.pack();
		GUI.setVisible(true);
	}
	/**
	 * Creates the Eastern Panel using the
	 * class TwitterResultsPanel();
	 */
	private void EastPanelInit() {
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout
				(eastPanel, BoxLayout.Y_AXIS));
		results = new TwitterResultsPanel(engine);
		eastPanel.add(results);
		GUI.add(eastPanel, BorderLayout.EAST);
	}
	/**
	 * Contains the Panels in the Operation Section.
	 * The combobox allows selection bettween the different
	 * options.
	 */
	private void WestPanelInit() {
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout
				(westPanel, BoxLayout.Y_AXIS));
		
		combo = new JComboBox(options);
		combo.setMaximumSize(new Dimension(300, 60));
		combo.addActionListener(switchHandeler);
		
		authP = new AuthenticatePanel(engine);
		getStatusP = new GetStatusPanel(engine);
		getTimeP = new GetTimePanel(engine);
		postP = new PostPanel(engine);
		searchP = new SearchPanel(engine);
		TitledBorder operationTitle = 
				BorderFactory.createTitledBorder
        		("Select Operation");
		combo.setBorder(operationTitle);
		westPanel.add(combo);
		westPanel.add(Box.createVerticalGlue());
		westPanel.add(authP);
		GUI.add(westPanel, BorderLayout.WEST);
	}
	/**
	 * menuInit() geneartes the JMenuBar to be used.
	 */
	private void menuInit() {
		menu = new JMenuBar();
		//Creates the File Menu
		file = new JMenu("File");
		fileDeleteStatus = new JMenuItem("Delete Status");
		fileDeleteTable = new
			JMenuItem("Delete Table Status");
		fileExport = new JMenuItem("Export to XML ...");
		fileQuit = new JMenuItem("Quit");
		fileDeleteStatus.addActionListener(menuHandeler);
		fileDeleteTable.addActionListener(menuHandeler);
		fileExport.addActionListener(menuHandeler);
		fileQuit.addActionListener(menuHandeler);
		file.add(fileDeleteStatus);
		file.add(fileDeleteTable);
		file.add(fileExport);
		file.add(fileQuit);
		menu.add(file);
		//Creates the Generate Menu
		generate = new JMenu("Generate");
		generateWordFrequencyList = new JMenuItem
				("Word Frequency List");
		generateTopTrendingList = new JMenuItem
				("Top Trending List");
		generateWordFrequencyList.addActionListener
			(menuHandeler);
		generateTopTrendingList.addActionListener
			(menuHandeler);
		generate.add(generateWordFrequencyList);
		generate.add(generateTopTrendingList);
		menu.add(generate);
		
		//Creates the Sort Menu
		sort = new JMenu("Sort");
		sortByCreation = new JRadioButtonMenuItem
				("By Creation Date");
		sortByLoginName = new JRadioButtonMenuItem
				("By Login Name");
		sortByDisplayName = new JRadioButtonMenuItem
				("By Display Name");
		sortByFreindCount = new JRadioButtonMenuItem
				("By Freinds Count");
		sortByFollowerCount = new JRadioButtonMenuItem
				("By Followers Count");
		sortByCreation.addActionListener(menuHandeler);
		sortByLoginName.addActionListener(menuHandeler);
		sortByDisplayName.addActionListener(menuHandeler);
		sortByFreindCount.addActionListener(menuHandeler);
		sortByFollowerCount.addActionListener(menuHandeler);
		group = new ButtonGroup();
		group.add(sortByCreation);
		group.add(sortByLoginName);
		group.add(sortByDisplayName);
		group.add(sortByFreindCount);
		group.add(sortByFollowerCount);
		sort.add(sortByCreation);
		sort.add(sortByLoginName);
		sort.add(sortByDisplayName);
		sort.add(sortByFreindCount);
		sort.add(sortByFollowerCount);
		menu.add(sort);
		//Create the Help Menu
		help = new JMenu("Help");
		help_About = new JMenuItem("About...");
		help_About.addActionListener(menuHandeler);
		help.add(help_About);
		menu.add(help);
		//Add Menu to the GUI
		GUI.setJMenuBar(menu);
	}
	/**
	 * Handels all of the actions in the JMenuBar
	 */
	private ActionListener menuHandeler = new 
			ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println(e);
			if(e.getActionCommand().equals
					("Delete Status")){
				engine.deleteStatus();
			}
			if(e.getActionCommand().equals
					("Delete Table Status")){
				engine.deleteTweet();
			}
			if(e.getActionCommand().equals
					("Export to XML ...")){
				saveButtonAction();
			}
			if(e.getActionCommand().equals("Quit")){
				System.exit(0);
			}
			if(e.getActionCommand().equals("About...")){
				JOptionPane.showMessageDialog(null, 
						"Produced by Benjamin Summers \n" +
						"                  10/28/2011 \n" +
						"         For a JTwitterProgram");
			}
			if(e.getActionCommand().equals(
					"By Freinds Count"))
				engine.sortByFriends();
			if(e.getActionCommand().equals(
					"By Followers Count"))
				engine.sortByFollowers();
			if(e.getActionCommand().equals(
					"By Creation Date"))
				engine.sortByDate();
			if(e.getActionCommand().equals(
					"By Display Name"))
				engine.sortByDate();
			if(e.getActionCommand().equals(
					"By Login Name"))
				engine.sortByLoginName();
			if(e.getActionCommand().equals(
					"Word Frequency List"))
				results.wordFrequencyCount(
						engine.wordFrequencyList());
			if(e.getActionCommand().equals(
					"Top Trending List")){
				results.topTrendingOutput(
						engine.topTrendingList());
			}
		}	
	};
	/**
	 * Handels which panel is active bellow the ComboBox
	 */
	private ActionListener switchHandeler = new 
			ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			westPanel.remove(authP);
			westPanel.remove(getStatusP);
			westPanel.remove(getTimeP);
			westPanel.remove(postP);
			westPanel.remove(searchP);
			//0 is the combo box, and 1 is whats beneath
			//westPanel.remove(1);
			GUI.pack();
			switch(combo.getSelectedIndex()){
			case 0: westPanel.add(authP); GUI.pack(); break;
			case 1: westPanel.add(getStatusP); GUI.pack();
				break;
			case 2: westPanel.add(getTimeP); GUI.pack();
				break; //timeline
			case 3: westPanel.add(postP); GUI.pack(); break;
			case 4: westPanel.add(searchP); GUI.pack();
				break; //search
			}	
			GUI.pack();
		}
	};
	/**
	 * uses the saveXML action to save a the twitter Status
	 * list as an XML file.
	 */
	private void saveButtonAction() {
        if (engine.getModel().getRowCount() > 0) {
            /* open the CURRENT directory */
            JFileChooser chooser = new JFileChooser(
            		new File("."));
            chooser.setDialogTitle("Export As XML");
            int status = chooser.showSaveDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile()
                	.getAbsolutePath();
                if (engine.saveAsXML(filename)) {
                    JOptionPane.showMessageDialog(this,
                            "XML Export Successful..",
                            "Export As XML",
                            JOptionPane.
                            INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                    		"XML Export Failed.",
                            "Export As XML",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "No books to save at this time.",
                    "Export As XML",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
	/**
	 * runs the main application starting with TwitterGUI
	 * which instantiates all other methods that are part
	 * of the GUI
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TwitterGUI();
            }
        });  
    }
	
}