package game.view;

import java.awt.*;
import javax.swing.*;

import game.controller.Control;

import game.model.map.MapGenerator;

import game.model.characters.*;

import java.awt.event.*;

public class MainFrame {
	public static int classChosen = 0;
	public static String nameChosen;

	public static void MainFrames() {
		JFrame frameMain = new JFrame();
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setTitle("Swingy by jpieczar");

		// Main menu *START* // Done
		JPanel mainMenu = new JPanel();
		JPanel mainMenu2 = new JPanel();
		JLabel mainTitle = new JLabel();
		JButton newGame = new JButton();
		newGame.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				frameMore();
				frameMain.setVisible(false);
			}
		});
		JButton loadGame = new JButton();
		loadGame.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				try {
					Output.loadGame();
				}
				catch (Exception e) {
					System.out.println("Did not load game. Wop, wop");
				}
				frameNext(MapGenerator.mapSize(Control.pubLvl));
				frameMain.setVisible(false);
			}
		});

		newGame.setText("New Game");
		loadGame.setText("Load Game");
		mainTitle.setText("           @==={::::::::::::> ~Swingy~ <::::::::::::}===@");
		mainTitle.setFont(new java.awt.Font("Monospaced", 1, 20));

		mainMenu.setSize(600, 400);
		mainMenu.setBackground(Color.GRAY);
		mainMenu2.setBackground(Color.GRAY);
		mainMenu2.setLayout(new FlowLayout(1));
		mainMenu.setLayout(new GridLayout(2, 1));

		mainMenu.add(mainTitle);
		mainMenu2.add(newGame);
		mainMenu2.add(loadGame);
		mainMenu.add(mainMenu2);
		// Main menu *END*

		

		frameMain.add(mainMenu);
		frameMain.pack();
		frameMain.setSize(850, 650);
		frameMain.setVisible(true);
		frameMain.setResizable(false);
	}

	public static void frameMore() {
		JFrame frameMain = new JFrame();
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setTitle("Swingy by jpieczar");
		// Create menu *START* // Done
		JPanel createMenu = new JPanel();
		JLabel createTitle = new JLabel();
		JLabel selectedClass = new JLabel();
		JPanel createMore = new JPanel();
		JPanel createEvenMore = new JPanel();
		JTextField enterName = new JTextField();
		JButton createHero = new JButton();
		createHero.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				nameChosen = enterName.getText();
				Control.createControl(nameChosen, classChosen, 1);
				frameNext(MapGenerator.mapSize(1));
				frameMain.setVisible(false);
			}
		});
		JButton createArcher = new JButton();
		createArcher.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				classChosen = 0;
				System.out.println("Archer");
				selectedClass.setText("Archer");
			}
		});
		JButton createMage = new JButton();
		createMage.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				classChosen = 1;
				System.out.println("Mage");
				selectedClass.setText("Mage");
			}
		});
		JButton createKnight = new JButton();
		createKnight.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				classChosen = 2;
				System.out.println("Knight");
				selectedClass.setText("Knight");
			}
		});

		selectedClass.setText("");
		createTitle.setText("Hero Creator");
		createTitle.setFont(new java.awt.Font("Monospaced", 1, 20));
		createHero.setText("Create Hero");
		enterName.setText("Enter a name");
		createArcher.setText("Archer");
		createMage.setText("Mage");
		createKnight.setText("Knight");

		createMenu.setSize(600, 400);
		createMenu.setBackground(Color.GRAY);
		createMore.setBackground(Color.GRAY);
		createEvenMore.setBackground(Color.GRAY);
		createMore.setLayout(new BoxLayout(createMore, BoxLayout.PAGE_AXIS));
		createEvenMore.setLayout(new FlowLayout());

		createMenu.setLayout(new BoxLayout(createMenu, BoxLayout.PAGE_AXIS));
		createMenu.add(createTitle);
		createMore.add(createArcher);
		createMore.add(createMage);
		createMore.add(createKnight);
		createEvenMore.add(selectedClass);
		createEvenMore.add(enterName);
		createEvenMore.add(createHero);
		createMenu.add(createMore);
		createMenu.add(createEvenMore);
		// Create menu *END*
		frameMain.add(createMenu);
		frameMain.pack();
		frameMain.setSize(850, 650);
		frameMain.setVisible(true);
		frameMain.setResizable(false);
	}

	public static void frameNext(int size) {
		MapGenerator.drawMap(size); // Do in while loop.
		JFrame frameMain = new JFrame();
		JTextArea jt = new JTextArea(); // Map view works.
		JPanel mapView1 = new JPanel();
		JPanel mapView2 = new JPanel();
		JLabel message = new JLabel();
		message.setText(" ... ");
		JTextArea  artMessage = new JTextArea();
		artMessage.setText(Hero.getStats());
		JButton North = new JButton();
		North.setText("North");
		North.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				Control.moveHero("North");
				MapGenerator.drawMap(size);
				jt.setText(MapGenerator.mapPane);
				message.setText("[Moved North]");
				if (Hero.isFight() == 1) {
					System.out.println("Fight");
				}
				if (Hero.isLimit(size) == 1) {
					System.out.println("End");
					frameMain.setVisible(false);
					Hero.lvlUp(Control.pubLvl);
					Control.pubLvl++;
					// add villains
					// CHaracter.activeCharacters.clear();
					// CHaracter.listArray();
					// Control.createControl(Control.hold1, Control.hold2, Control.pubLvl);
					frameNext(MapGenerator.mapSize(Control.pubLvl));
				}
			}
		});
		JButton South = new JButton();
		South.setText("South");
		South.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				Control.moveHero("South");
				MapGenerator.drawMap(size);
				jt.setText(MapGenerator.mapPane);
				message.setText("[Moved South]");
				if (Hero.isFight() == 1) {
					System.out.println("Fight");
				}
				if (Hero.isLimit(size) == 1) {
					System.out.println("End");
					frameMain.setVisible(false);
					Control.pubLvl++;
					frameNext(MapGenerator.mapSize(Control.pubLvl));
				}
			}
		});
		JButton East = new JButton();
		East.setText("East");
		East.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				Control.moveHero("East");
				MapGenerator.drawMap(size);
				jt.setText(MapGenerator.mapPane);
				message.setText("[Moved East]");
				if (Hero.isFight() == 1) {
					System.out.println("Fight");
				}
				if (Hero.isLimit(size) == 1) {
					System.out.println("End");
					frameMain.setVisible(false);
					Control.pubLvl++;
					frameNext(MapGenerator.mapSize(Control.pubLvl));
				}
			}
		});
		JButton West = new JButton();
		West.setText("West");
		West.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				Control.moveHero("West");
				MapGenerator.drawMap(size);
				jt.setText(MapGenerator.mapPane);
				message.setText("[Moved West]");
				if (Hero.isFight() == 1) {
					System.out.println("Fight");
				}
				if (Hero.isLimit(size) == 1) {
					System.out.println("End");
					frameMain.setVisible(false);
					Control.pubLvl++;
					frameNext(MapGenerator.mapSize(Control.pubLvl));
				}
			}
		});
		JButton Action = new JButton();
		Action.setText("Action");
		Action.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				message.setText("");
			}
		});
		JButton Action2 = new JButton();
		Action2.setText("Action2");
		Action2.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				message.setText("");
			}
		});
		JButton SaveGame = new JButton();
		SaveGame.setText("Save");
		SaveGame.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent evnt) {
				Output.deleteFile();
				Output.makeFile();
				Output.saveState();
				message.setText("Game saved");
			}
		});
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setTitle("Swingy by jpieczar");
		frameMain.setLayout(new FlowLayout());
		mapView1.setLayout(new FlowLayout());
		mapView2.setLayout(new FlowLayout());

		// jt.setColumns(20);
		// jt.setRows(5);
		mapView1.setForeground(Color.GRAY);
		mapView2.setForeground(Color.GRAY);
		jt.setForeground(Color.GREEN);
		jt.setBackground(Color.BLACK);
		jt.setText(MapGenerator.mapPane);
		jt.setFont(new java.awt.Font("Monospaced", 1, 20));

		jt.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				jt.setEditable(true);
	
			}
	
			@Override
			public void focusGained(FocusEvent e) {
				jt.setEditable(false);
	
			}
		});

		// for (int i = 0; i < (CHaracter.activeCharacters.size() - 1); i++) {
		// 	int x1 = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getX();
		// 	int x2 = CHaracter.activeCharacters.get(i).getX();
		// 	int y1 = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getY();
		// 	int y2 = CHaracter.activeCharacters.get(i).getY();
		// 	if ((x1 == x2) && (y1 == y2)) {
		// 		message = "Beans";
		// 		System.out.println("Beans");
		// 	}
		// }

		mapView1.add(North);
		mapView1.add(South);
		mapView1.add(East);
		mapView1.add(West);
		mapView1.add(Action);
		mapView1.add(Action2);
		mapView1.add(SaveGame);
		mapView1.add(message);
		mapView2.add(artMessage);
		frameMain.add(jt);
		frameMain.add(mapView1);
		frameMain.add(mapView2);
		frameMain.pack();
		frameMain.setSize(850, 650);
		frameMain.setVisible(true);
		frameMain.setResizable(true);
	}
}