package game.view;


import java.awt.*;
import javax.swing.*;

import game.controller.*;

import game.model.*;

import java.awt.event.*;

public class MainFrame {
    public static void MainFrames() {
        final int[] gameLvl = {0};
        final JFrame frameMain = new JFrame();
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setTitle("Swingy by jpieczar");

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
                    frameMain.setVisible(false);
                    gameLvl[0] = Output.loadGame();
                    frameNext(gameLvl[0]);
                }
                catch (Exception e) {
                    frameMain.setVisible(false);
                    System.out.println("Did not load game. Wop, wop");
                }
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
        final JFrame frameMain = new JFrame();
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setTitle("Swingy by jpieczar");
        final int[] classChosen = {0};
        JPanel createMenu = new JPanel();
        JLabel createTitle = new JLabel();
        final JLabel selectedClass = new JLabel();
        JPanel createMore = new JPanel();
        JPanel createEvenMore = new JPanel();
        final JTextField enterName = new JTextField();
        JButton createHero = new JButton();
        createHero.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                frameMain.setVisible(false);
                CreateControl.createControl(enterName.getText(), classChosen[0], 1);
                frameNext(1);
            }
        });
        JButton createArcher = new JButton();
        createArcher.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                classChosen[0] = 0;
                System.out.println("Archer");
                selectedClass.setText("Archer");
            }
        });
        JButton createMage = new JButton();
        createMage.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                classChosen[0] = 1;
                System.out.println("Mage");
                selectedClass.setText("Mage");
            }
        });
        JButton createKnight = new JButton();
        createKnight.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                classChosen[0] = 2;
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

    public static void frameNext(final int size) {
        final int[] lvl = {size};
        final String[] holdPos = {""};
        MapGenerator.drawMap(MapGenerator.mapSize(lvl[0])); // Do in while loop.
        final JFrame frameMain = new JFrame();
        final JTextArea jt = new JTextArea(); // Map view works.
        JPanel mapView1 = new JPanel();
        JPanel mapView2 = new JPanel();
        final JLabel message = new JLabel();
        message.setText("[           ]");
        final JTextArea  artMessage = new JTextArea();
        artMessage.setText(Hero.getStats());
        JButton North = new JButton();
        North.setText("North");
        North.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                GamePlay.normalDo("North");
                MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                jt.setText(MapGenerator.mapPane);
                message.setText("[Moved North]");
                holdPos[0] = "North";
                if (Hero.isLimit(MapGenerator.mapSize(lvl[0])) == 1) {
                    Hero.lvlUp(lvl[0]);
                    lvl[0]++;

                    CHaracter tempHero =  CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1);
                    CHaracter.activeCharacters.clear();
                    CHaracter.idCounter = 0;
                    MapGenerator.addPositions(MapGenerator.mapSize(lvl[0]));
                    Villain.addVillains(MapGenerator.mapSize(lvl[0]));
                    CHaracter.activeCharacters.add(tempHero);
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setCharID(CHaracter.idCounter++);
                    frameMain.setVisible(false);
                    frameNext(lvl[0]);
                }
                else if (Hero.isVillain() != 0) {
                    String playerInput;
                    message.setText(">>> You encountered a " + CHaracter.activeCharacters.get(Hero.isVillain()).characterClass + " <<<");
                    playerInput = JOptionPane.showInputDialog("<::> Enter Fight, or Run: Fight, Run <::>");
                    GamePlay.fightDo(playerInput, Hero.isVillain(), holdPos[0]);
                    MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                    artMessage.setText(Hero.getStats());
                    jt.setText(MapGenerator.mapPane);
                }
            }
        });
        JButton South = new JButton();
        South.setText("South");
        South.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                GamePlay.normalDo("South");
                MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                jt.setText(MapGenerator.mapPane);
                message.setText("[Moved South]");
                holdPos[0] = "South";
                if (Hero.isLimit(MapGenerator.mapSize(lvl[0])) == 1) {
                    Hero.lvlUp(lvl[0]);
                    lvl[0]++;

                    CHaracter tempHero =  CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1);
                    CHaracter.activeCharacters.clear();
                    CHaracter.idCounter = 0;
                    MapGenerator.addPositions(MapGenerator.mapSize(lvl[0]));
                    Villain.addVillains(MapGenerator.mapSize(lvl[0]));
                    CHaracter.activeCharacters.add(tempHero);
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setCharID(CHaracter.idCounter++);
                    frameMain.setVisible(false);
                    frameNext(lvl[0]);
                }
                else if (Hero.isVillain() != 0) {
                    String playerInput;
                    message.setText(">>> You encountered a " + CHaracter.activeCharacters.get(Hero.isVillain()).characterClass + " <<<");
                    playerInput = JOptionPane.showInputDialog("<::> Enter Fight, or Run: Fight, Run <::>");
                    GamePlay.fightDo(playerInput, Hero.isVillain(), holdPos[0]);
                    MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                    artMessage.setText(Hero.getStats());
                    jt.setText(MapGenerator.mapPane);
                    }
            }
        });
        JButton East = new JButton();
        East.setText("East");
        East.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                GamePlay.normalDo("East");
                MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                jt.setText(MapGenerator.mapPane);
                message.setText("[Moved East]");
                holdPos[0] = "East";
                if (Hero.isLimit(MapGenerator.mapSize(lvl[0])) == 1) {
                    Hero.lvlUp(lvl[0]);
                    lvl[0]++;

                    CHaracter tempHero =  CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1);
                    CHaracter.activeCharacters.clear();
                    CHaracter.idCounter = 0;
                    MapGenerator.addPositions(MapGenerator.mapSize(lvl[0]));
                    Villain.addVillains(MapGenerator.mapSize(lvl[0]));
                    CHaracter.activeCharacters.add(tempHero);
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setCharID(CHaracter.idCounter++);
                    frameMain.setVisible(false);
                    frameNext(lvl[0]);
                }
                else if (Hero.isVillain() != 0) {
                    String playerInput;
                    message.setText(">>> You encountered a " + CHaracter.activeCharacters.get(Hero.isVillain()).characterClass + " <<<");
                    playerInput = JOptionPane.showInputDialog("<::> Enter Fight, or Run: Fight, Run <::>");
                    GamePlay.fightDo(playerInput, Hero.isVillain(), holdPos[0]);
                    MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                    artMessage.setText(Hero.getStats());
                    jt.setText(MapGenerator.mapPane);
                }
            }
        });
        JButton West = new JButton();
        West.setText("West");
        West.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                GamePlay.normalDo("West");
                MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                jt.setText(MapGenerator.mapPane);
                message.setText("[Moved West]");
                holdPos[0] = "West";
                if (Hero.isLimit(MapGenerator.mapSize(lvl[0])) == 1) {
                    Hero.lvlUp(lvl[0]);
                    lvl[0]++;

                    CHaracter tempHero =  CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1);
                    CHaracter.activeCharacters.clear();
                    CHaracter.idCounter = 0;
                    MapGenerator.addPositions(MapGenerator.mapSize(lvl[0]));
                    Villain.addVillains(MapGenerator.mapSize(lvl[0]));
                    CHaracter.activeCharacters.add(tempHero);
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(MapGenerator.mapCentre(MapGenerator.mapSize(lvl[0])));
                    CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setCharID(CHaracter.idCounter++);
                    frameMain.setVisible(false);
                    frameNext(lvl[0]);
                }
                else if (Hero.isVillain() != 0) {
                    String playerInput;
                    message.setText(">>> You encountered a " + CHaracter.activeCharacters.get(Hero.isVillain()).characterClass + " <<<");
                    playerInput = JOptionPane.showInputDialog("<::> Enter Fight, or Run: Fight, Run <::>");
                    GamePlay.fightDo(playerInput, Hero.isVillain(), holdPos[0]);
                    MapGenerator.drawMap(MapGenerator.mapSize(lvl[0]));
                    artMessage.setText(Hero.getStats());
                    jt.setText(MapGenerator.mapPane);
                }
            }
        });
//        JButton Action = new JButton();
//        Action.setText("Fight");
//        Action.addActionListener(new ActionListener () {
//            public void actionPerformed(ActionEvent evnt) {
//                message.setText("");
//            }
//        });
//        JButton Action2 = new JButton();
//        Action2.setText("Run");
//        Action2.addActionListener(new ActionListener () {
//            public void actionPerformed(ActionEvent evnt) {
//                message.setText("");
//            }
//        });
        JButton SaveGame = new JButton();
        SaveGame.setText("Save");
        SaveGame.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evnt) {
                GamePlay.normalDo("Save");
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
//        mapView1.add(Action);
//        mapView1.add(Action2);
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
