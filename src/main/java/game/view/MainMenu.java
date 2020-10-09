package game.view;

import game.controller.MainMenuController;

public class MainMenu {
    public static void firstOpt(int opt) {
        System.out.println("Enter either 'new' or 'load' to either start a new game or load an existing game.");
        if (opt == 1) {
            System.out.println("Build GUI");
            MainFrame.MainFrames();
        }
        else {
            System.out.println("====== {Main Menu} ======");
            MainMenuController.consoleFirstOpt();
        }
    }
}
