package game.model.artifacts;

public class Artifacts {
	private int Weapon = 0;
	private int Armour = 0;
	private int Helm = 0;

	public int getArmour() {
		return Armour;
	}
	public int getHelm() {
		return Helm;
	}
	public int getWeapon() {
		return Weapon;
	}
	public void setArmour(int armour) {
		Armour = armour; // make def 200.
	}
	public void setHelm(int helm) {
		Helm = helm; // make hp 100.
	}
	public void setWeapon(int weapon) {
		Weapon = weapon; // make atf 100.
	}
}