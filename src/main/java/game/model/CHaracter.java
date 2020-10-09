package game.model;

import game.controller.CreateControl;

import java.util.ArrayList;
import java.util.List;

public class CHaracter {
    public static List<CHaracter> activeCharacters = new ArrayList<>();
    public static int idCounter = 0;
    private int charID = 0;
    protected /*public*/ String name;
    private String[] availableClass = {"Archer", "Mage", "Knight", "Bandit", "Troll", "Dragon"};
    /*protected*/ public String characterClass;
    protected long lvl; // If this does not work, try a double.
    protected int exp;
    public int atk;
    public int def;
    public int hp;
    private int xAxis;
    private int yAxis;

    CHaracter(String name, int num, int opt, String[] fromFile) {
        if (opt == 0) {
            this.charID = idCounter;
            idCounter++;
            this.name = name;
            this.characterClass = availableClass[num];
            this.lvl = 0;
            this.exp = 0;
            if (num == 0) {
                this.atk = 50;
                this.def = 75;
                this.hp = 100;
            }
            else if (num == 1) {
                this.atk = 75;
                this.def = 100;
                this.hp = 50;
            }
            else if (num == 2) {
                this.atk = 100;
                this.def = 50;
                this.hp = 75;
            }
            else if (num == 3) {
                this.atk = 100;
                this.def = 75;
                this.hp = 50;
            }
            else if (num == 4) {
                this.atk = 100;
                this.def = 100;
                this.hp = 50;
            }
            else {
                this.atk = 100;
                this.def = 100;
                this.hp = 175;
            }

            if (num >= 3) {
                String splitee = MapGenerator.positions.get(this.charID);
                String[] spliter = splitee.split("~");
                setX(Integer.parseInt(spliter[0]));
                setY(Integer.parseInt(spliter[1]));
            }
            else {
                setX(MapGenerator.mapCentre(MapGenerator.mapSize(CreateControl.pubLvl)));
                setY(MapGenerator.mapCentre(MapGenerator.mapSize(CreateControl.pubLvl)));
            }
        }
        else {
            this.charID = Integer.parseInt(fromFile[0]);
            this.name = fromFile[1];
            this.characterClass = fromFile[2];
            this.lvl =  Integer.parseInt(fromFile[3]);
            this.exp =  Integer.parseInt(fromFile[4]);
            this.atk =  Integer.parseInt(fromFile[5]);
            this.def =  Integer.parseInt(fromFile[6]);
            this.hp =  Integer.parseInt(fromFile[7]);
            setX(Integer.parseInt(fromFile[8]));
            setY(Integer.parseInt(fromFile[9]));
        }


        // Delete this later, or keep it ...
//        System.out.println("Character created successfully!");
//        System.out.println(this.characterClass);
//        System.out.println(this.name);
//        System.out.println("^^^^^");

        // Add character to array.
        addToActive(this);
//        System.out.println(activeCharacters.size()); // Prints the character list.
    }

    private void addToActive(CHaracter character) {
            activeCharacters.add(character);
    }

    public static void removeFromActive(int id) {
        for (int i = 0; i < activeCharacters.size(); i++) {
            if (id == activeCharacters.get(i).charID) {
                CHaracter character =  activeCharacters.get(i);
                activeCharacters.remove(character);
            }
        }
    }

    // For testing purposes.
    public static void arraySize() {
        System.out.println("Array size " + activeCharacters.size());
    }

    // For testing purposes.
    public static void listArray() {
        for (int i = 0; i < activeCharacters.size(); i++) {
            System.out.println(activeCharacters.get(i).characterClass);
        }
    }

    public static String forSave(CHaracter character) {
        String toSave = "";
        toSave += character.charID+"~";
        if (character.name.length() > 1) {
            toSave += character.name+"~";
        }
        else {
            toSave += "Villain"+"~";
        }
        toSave += character.characterClass+"~";
        toSave += character.lvl+"~";
        toSave += character.exp+"~";
        toSave += character.atk+"~";
        toSave += character.def+"~";
        toSave += character.hp+"~";
        toSave += character.xAxis+"~";
        toSave += character.yAxis;
        // System.out.println(toSave);
        return toSave;
    }

    public int getX() {
        return this.xAxis;
    }
    public int getY() {
        return this.yAxis;
    }
    public int getCharID() {
        return charID;
    }
    public void setCharID(int num) {
        this.charID = num;
    }
    public void setX(int num) {
        this.xAxis = num;
    }
    public void setY(int num) {
        this.yAxis = num;
    }

}
