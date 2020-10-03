package game.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.model.characters.*;

public class MapGenerator {
	public static String mapPane = "";

	public static List<String> positions = new ArrayList<>();

	public static int mapSize(int level) {
		int num = (level - 1) * 5 + 10 - (level % 2);
		System.out.println(num + " x " + num);
		return num;
	}

	public static int mapCentre(int size) { // This is also the number of villains
		return (size / 2) + 1;
		// System.out.println(num);
	}

	public static void addPositions(int size) {
		int num = ((size / 2) + 1) * 2;
		for (int i = 0; i < num; i++) {
			int rand = new Random().nextInt(size) + 1;
			int dnar = new Random().nextInt(size) + 1;
			if (rand == dnar) {
				i--;
			}
			else {
				String pos = (rand+"~"+dnar);
				for (int j = 0; j < positions.size(); j++) {
					if (pos == positions.get(j)) {
						i--;
						continue;
					}
				}
				positions.add(pos);
			}
		}
		// For testing purposes.
		for (int i = 0; i < positions.size(); i++) {
			System.out.println(positions.get(i));
		}
	}

	public static void drawMap(int size) {
		mapPane = "";
		// activeCharacters
		// -positions
		// -class
		// size * size
		//   1 2 3 4 5 6 7 8 9
		// 9[. . . . . # . . . ]
		// 8[. . . # . . # . . ]
		// 7[. . . . . . . . . ]
		// 6[# . . . . . . # . ]
		// 5[. . # . A . . . . ]
		// 4[. . . . . . . . . ]
		// 3[# . . # . . . . . ]
		// 2[. . . . # . . . # ]
		// 1[. . . . . . . . . ]
		for (int i = size; i > 0; i--) {
			mapPane = mapPane+"|";
			// System.out.print("[");
			for (int j = 1; j < (size + 1); j++) {
				if (searchList(i, j) == 1) {
					mapPane = mapPane+"X";
					mapPane = mapPane+" ";
				}
				else if (searchList(i, j) == 2) {
					mapPane = mapPane+"O";
					mapPane = mapPane+" ";
				}
				else {
					mapPane = mapPane+".";
					mapPane = mapPane+" ";
				}
			}
			mapPane = mapPane+"|\n";
		}
	}
	private static int searchList(int i, int j) {
		for (int k = 0; k < CHaracter.activeCharacters.size(); k++) {
			int x = CHaracter.activeCharacters.get(k).getX();
			int y = CHaracter.activeCharacters.get(k).getY();
			if (x == j && y == i) {
				if (CHaracter.activeCharacters.get(k).getCharID() == (CHaracter.activeCharacters.size() - 1)) {
					return 2;
				}
				return 1;
			}
		}
		return 0;
	}
}