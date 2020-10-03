package game.model.characters;

import java.util.Random;

public class Villain extends CHaracter {
	static String[] nothing = {};
	public Villain(int num, String[] nothing) {
		super("", num, 0, nothing);
	}

	public static void addVillains(int size) {
		int num = ((size / 2) + 1) * 2;
		for (int i = 0; i < num; i++) {
			int rand = new Random().nextInt(3) + 3;
			new Villain(rand, nothing);
		}
	}

	// Deal damage. // if num is charID of the villain.
	// public static int dealDamage(int num) {
	// 	int damage = 0;
	// 	switch (CHaracter.activeCharacters.get(num).characterClass) {
	// 		case "Bandit":
	// 			damage = 12;
	// 			break;
	// 		case "Troll":
	// 			damage = 25;
	// 			break;
	// 		case "Dragon":
	// 			damage = 50;
	// 			break;
	// 	}
	// 	return damage;
	// }

	// Drop artifact.
	public static String dropArt() {
		String[] drop = {"Weapon", "", "Armour", "", "Helm", ""};
		int rand = new Random().nextInt(6);
		return drop[rand];
	}
}