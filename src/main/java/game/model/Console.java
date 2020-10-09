package game.model;

import game.controller.CreateControl;
import game.controller.GamePlay;

import java.util.Scanner;

public class Console {
    public static void newLoad(String opt) {
        int gameLvl = 0;
        if (opt.equals("new") || opt.equals("New")) {
            System.out.println(":::Creating:::");
            Scanner scan = new Scanner(System.in);
            System.out.print("<::> Enter a name: ");
            String userName = scan.nextLine();
            System.out.println("<::> Choose a class (Archer is default): 0-Archer 1-Mage 2-Knight <::>");
            String userClass = scan.nextLine();
            if(userClass.length() == 0) {
                userClass = "0";
            }
            int userClass2 = Integer.parseInt(userClass);
            CreateControl.createControl(userName, userClass2, 1);
//            MapGenerator.drawMap(MapGenerator.mapSize(1));
            playGame(1);
        }
        else if (opt.equals("load") || opt.equals("Load")) {
            try {
                gameLvl = Output.loadGame();
                playGame(gameLvl);
            }
            catch (Exception e) {
                System.out.println("Did not load game. Wop, wop");
                System.out.println(e);
            }
        }
        else {
            System.out.println("Error selecting");
            System.exit(1);
        }
    }

    public static void playGame(int lvlIn) {
        int lvl = lvlIn;
        String holdPos = "";
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\033[H\033[2J"); // Clears the screen.
            MapGenerator.drawMap(MapGenerator.mapSize(lvl));
            System.out.println(MapGenerator.mapPane);
            System.out.println(":=:=:=:STATS:=:=:=:");
            System.out.println(Hero.getStats());
//            CHaracter.listArray();
            System.out.println(":=:=:=:STATS:=:=:=:\n");
            if (Hero.isLimit(MapGenerator.mapSize(lvl)) == 1) {
                Hero.lvlUp(lvl);
                lvl++;

                CHaracter tempHero =  CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1);
                CHaracter.activeCharacters.clear();
                CHaracter.idCounter = 0;
                MapGenerator.addPositions(MapGenerator.mapSize(lvl));
                Villain.addVillains(MapGenerator.mapSize(lvl));
                CHaracter.activeCharacters.add(tempHero);
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(MapGenerator.mapCentre(MapGenerator.mapSize(lvl)));
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(MapGenerator.mapCentre(MapGenerator.mapSize(lvl)));
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setCharID(CHaracter.idCounter++);
            }
            else if (Hero.isVillain() != 0) {
                // get villain details like charID and characterClass.
                System.out.println("!!!");
                System.out.println(">>> You encountered a " + CHaracter.activeCharacters.get(Hero.isVillain()).characterClass + " <<<");
                System.out.println("!!!");
                System.out.println("<::> Enter Fight, Run, or 'Quit' to exit: Fight, Run, Quit <::>");
                String playerInput = scan.nextLine();
                GamePlay.fightDo(playerInput, Hero.isVillain(), holdPos);
            }
            else {
                System.out.println("<::> Enter a direction, enter 'Save' to save the game, or 'Quit' to exit: North, South, East, West, Save, Quit <::>");
                String playerInput = scan.nextLine();
                holdPos = playerInput;
                GamePlay.normalDo(playerInput);
            }
        }
    }
}
