import game.view.MainMenu;

public class Main {
	public static void main(String[] args) {
		System.out.println("\033[H\033[2J");
		System.out.println("@==={::::::::::::> ~Swingy~ <::::::::::::}===@");
		if (args.length == 1) {
			if (args[0].equals("g") || args[0].equals("G")) {
				System.out.println("GUI");
				MainMenu.firstOpt(1);
			}
			else if (args[0].equals("c") || args[0].equals("C")) {
				System.out.println("<Console>");
				MainMenu.firstOpt(0);
			}
			else {
				System.out.println("Use either 'g' or 'c' for an argument.");
			}
		}
		else {
			System.out.println("This needs an argument to run.");
		}
	}
}