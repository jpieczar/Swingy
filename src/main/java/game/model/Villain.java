package game.model;

import java.util.Random;

public class Villain extends CHaracter{
    static String[] nothing = {};
    public Villain(int num, String[] nothing) {
        super("", num, 0, nothing);
    }

    public static void addVillains(int size) {
//        int num = ((size / 2) + 1) * 2;
        for (int i = 0; i < size; i++) {
            int rand = new Random().nextInt(3) + 3;
            new Villain(rand, nothing);
        }
    }
}
