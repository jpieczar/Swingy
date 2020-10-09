package game.controller;

import game.model.*;

import javax.validation.ValidationException;

public class CreateControl {
    public static int pubLvl;
    public static String hold1;
    public static int hold2;

    public static void createControl(String name, int num, int lvl) {
        pubLvl = lvl;
        hold1 = name;
        hold2 = num;

        MapGenerator.addPositions(MapGenerator.mapSize(lvl));
        Villain.addVillains(MapGenerator.mapSize(lvl));
        System.out.println(":::Villains Placed:::");
        createTest(name, num);
        System.out.println(":::Hero Created:::");
    }

    public static void createTest(String name, int num) {
        try {
            ValidateThis validatethis = new ValidateThis(name, num);
            validatethis.validateValidator();
            Hero hero = new Hero(name, num);
        }
        catch (ValidationException e) {
            System.out.println(e);
            System.out.println("<::>Try again ...<::>");
            System.exit(1);
//            Console.newLoad("new");
        }
    }
}
