package game.controller;

import game.model.characters.*;

import game.model.map.*;

public class Control {
	public static int pubLvl;
	public static String hold1;
	public static int hold2;
	public static void createControl(String name, int num, int lvl) {
		pubLvl = lvl;
		hold1 = name;
		hold2 = num;
		MapGenerator.addPositions(MapGenerator.mapSize(lvl));
		Villain.addVillains(MapGenerator.mapSize(lvl));
		new Hero(name, num);
		MapGenerator.drawMap(MapGenerator.mapSize(lvl));
	}

	public static void moveHero(String direction) {
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
		}
	}
}