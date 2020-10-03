package game.model.characters;

import java.io.File;
import java.io.FileNotFoundException; // Delete this.
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner; // Delete this.

// import game.model.characters.*;
import game.controller.*;

public class Output extends CHaracter {
	public Output(String[] maker) {
		super("", 0, 1, maker);
	}

	public static void makeFile() {
		try {
			File output = new File("saveFile.txt");
			output.createNewFile();
		}
		catch (Exception e) {
			System.out.println("No file made. Wop, wop.");
		}
	}

	public static void deleteFile() {
		try {
			File file = new File("saveFile.txt");
			file.delete();
		}
		catch (Exception e) {
			System.out.println("File could not be deleted. Wop, wop.");
		}
	}

	public static void writeFile(String writeThis) {
		try {
			FileWriter output1 = new FileWriter("saveFile.txt", true);
			PrintWriter output2 = new PrintWriter(output1);
			output2.write(writeThis + "\n");
			output2.close();
		}
		catch (Exception e) {
			System.out.println("Could not write to file. Wop, wop.");
		}
	}

	public static void saveState() {
		int size = 0;
		String map;
		for (int i = 0; i < CHaracter.activeCharacters.size(); i++) {
			size++;
		}
		map = String.valueOf(size -= 2);
		writeFile(map);
		for (int i = 0; i < CHaracter.activeCharacters.size(); i++) {
			writeFile(CHaracter.forSave(CHaracter.activeCharacters.get(i)));
		}
	}

	public static void loadGame() throws FileNotFoundException {
		File savedGame = new File("saveFile.txt");
		Scanner scnr = new Scanner(savedGame);
		String line = scnr.nextLine();
		// int count = 0;
		try {
			int size = Integer.parseInt(line);
			if (size == 9) {
				size = 1;
				Control.pubLvl = size;
			}
			else {
				size = (((size - 10) * 2) + 10)/9;
				Control.pubLvl = size;
				System.out.println("bruh "+Control.pubLvl);
			}
			// count = MapGenerator.mapSize(size);
			// System.out.println("The map size is "+size+" x "+size);
		}
		catch (Exception e) {
			System.out.println("Could not load game. Wop, wop.");
		}
		while (scnr.hasNextLine()) {
			line = scnr.nextLine();
			String[] maker = line.split("~");
			try {
				new Output(maker);
				// super("", 0, 1, maker);
				// Fix this now
			}
			catch (Exception e) {
				System.out.println("Could not load characters. Wop, wop");
			}
		}
		scnr.close();
	}
}