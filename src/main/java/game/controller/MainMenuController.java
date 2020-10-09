package game.controller;

import game.model.Console;

import java.util.Scanner;

public class MainMenuController {
    public static void consoleFirstOpt() {
        Scanner scan = new Scanner(System.in);
        String userIn = scan.nextLine();
        Console.newLoad(userIn);
    }
}
