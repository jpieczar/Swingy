package game.controller;

import game.model.CHaracter;
import game.model.Output;

import java.util.Random;
import java.util.Scanner;

public class GamePlay {
    public static void normalDo(String direction) {
        int x = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getX();
        int y = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getY();
        switch (direction) {
            case "North":
                y++;
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(y);
                break;
            case "South":
                y--;
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setY(y);
                break;
            case "East":
                x++;
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(x);
                break;
            case "West":
                x--;
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).setX(x);
                break;
            case "Save":
                Output.deleteFile();
                Output.makeFile();
                Output.saveState();
                System.out.println("<::>Saved<::>");
//                Scanner scan = new Scanner(System.in);
//                String playerInput = scan.nextLine();
                break;
            case "Quit":
                System.out.println("<::>Goodbye<::>");
                System.exit(0);
                break;
            default:
                System.out.println("<::>Enter a valid command!!!<::>");
        }
    }
    public static void fightDo(String command, int villainID, String holdPos) {
        Scanner scan = new Scanner(System.in);
        switch (command) {
            case "Fight":
                fightFunction(villainID);
                break;
            case "Run":
                int rand = new Random().nextInt(2) + 1;
                if (rand == 1) {
                    switch (holdPos) {
                        case "North":
                            normalDo("South");
                            break;
                        case "South":
                            normalDo("North");
                            break;
                        case "East":
                            normalDo("West");
                            break;
                        case "West":
                            normalDo("East");
                            break;
                    }
                    System.out.println(">>> You managed to run away!!! <<<");
//                    String playerInput = scan.nextLine();
                }
                else if (rand == 2) {
                    System.out.println(">>> You cannot run. You have to fight ... <<<");
//                    String playerInput = scan.nextLine();
                    fightFunction(villainID);
                }
                break;
            case "Quit":
                System.out.println("<::>Goodbye<::>");
                System.exit(0);
                break;
            default:
                System.out.println("<::>Enter a valid command!!!<::>");
        }
    }

    public static void fightFunction(int villainID) {
        Scanner scan = new Scanner(System.in);
        int rand = new Random().nextInt(2) + 1;
        int randArt = new Random().nextInt(3) + 1;
        int vSum = CHaracter.activeCharacters.get(villainID).atk;
        vSum += CHaracter.activeCharacters.get(villainID).def;
        vSum += CHaracter.activeCharacters.get(villainID).hp;
        int hSum = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).atk;
        hSum += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).def;
        hSum += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).hp;

        if (hSum == vSum) {
            if (rand == 1) {
                CHaracter.removeFromActive(villainID);
                System.out.println(">>> You won the fight!!! <<<");
                giveArt(randArt);
//                String playerInput = scan.nextLine();
            }
            else {
                CHaracter.removeFromActive(villainID);
                System.out.println(">>> You won the fight!!! <<<");
                giveArt(randArt);
//                String playerInput = scan.nextLine();
            }
        }
        else if (hSum > vSum) {
            CHaracter.removeFromActive(villainID);
            System.out.println(">>> You won the fight!!! <<<");
            giveArt(randArt);
//            String playerInput = scan.nextLine();
        }
        else {
            System.out.println(">>> You lost the fight ... <<<");
            System.out.println(">>> GAME OVER <<<");
            System.out.println("<::>Goodbye<::>");
            System.exit(0);
        }
    }

    public static void giveArt(int rand) {
        String[] artifacts = {"Weapon", "Armour", "Helm"};
        String choice = artifacts[rand - 1];
        switch (choice) {
            case "Weapon":
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).atk = 100;
                System.out.println(">>> You found a weapon!!! 100 atk <<<");
                break;
            case "Armour":
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).def = 100;
                System.out.println(">>> You found armour!!! 100 def <<<");
                break;
            case "Helm":
                CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).hp = 100;
                System.out.println(">>> You found a helm!!! 100 hp <<<");
                break;
        }
    }
}
