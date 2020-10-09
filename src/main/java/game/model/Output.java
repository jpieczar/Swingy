package game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import game.controller.CreateControl;

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
//            int size = 0;
//            String map;
//            for (int i = 0; i < CHaracter.activeCharacters.size(); i++) {
//                size++;
//            }
//            map = String.valueOf(size -= 2);
//            writeFile(map);
//            for (int i = 0; i < CHaracter.activeCharacters.size(); i++) {
//                writeFile(CHaracter.forSave(CHaracter.activeCharacters.get(i)));
//            }
            long saveLvl = 0;
            String saveStr = "";
            int i = 0;
            while (CHaracter.activeCharacters.get(i).name.equals("") || CHaracter.activeCharacters.get(i).name.equals("Villain")) {
                i++;
            }
            saveLvl = CHaracter.activeCharacters.get(i).lvl + 1;
            saveStr = saveStr + saveLvl;
            writeFile(saveStr);
            for (int j = 0; j < CHaracter.activeCharacters.size(); j++) {
                writeFile(CHaracter.forSave(CHaracter.activeCharacters.get(j)));
            }
        }

        public static int loadGame() throws FileNotFoundException {
            int size = 0;
            File savedGame = new File("saveFile.txt");
            Scanner scnr = new Scanner(savedGame);
            String line = scnr.nextLine();
            try {
                size = Integer.parseInt(line);
//                MapGenerator.addPositions(MapGenerator.mapSize(lvl));
//                Villain.addVillains(MapGenerator.mapSize(lvl));
//                System.out.println(":::Villains Placed:::");
//                createTest(name, num);
//                System.out.println(":::Hero Created:::");
            }
            catch (Exception e) {
                System.out.println("Could not load game. Wop, wop.");
            }
            while (scnr.hasNextLine()) {
                line = scnr.nextLine();
                String[] maker = line.split("~");
                try {
                    new Output(maker);
//                    super("", 0, 1, maker);
                }
                catch (Exception e) {
                    System.out.println("Could not load characters. Wop, wop");
                }
            }
            scnr.close();
            return size;
        }
}
