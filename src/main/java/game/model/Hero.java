package game.model;

public class Hero extends CHaracter {
    static String[] nothing = {};
    public Hero(String name, int num) {
        super(name, num, 0, nothing);
    }

    public static void lvlUp(int level) {
        int num = (level * 1000) + ((level - 1)*(level - 1)) * 450;
        CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).exp += num;
        CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).lvl++;
        System.out.println("lvlUp: "+num);
    }

    public static String getStats() {
        String[] arr = {"", "", "", "", "", ""};
        arr[0] += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).characterClass;
        arr[1] += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).lvl;
        arr[2] += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).exp;
        arr[3] += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).atk;
        arr[4] += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).def;
        arr[5] += CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).hp;
        String str = "<::> Class: "+arr[0]+"\n";
        str += "<::> Lvl: "+arr[1]+"\n";
        str += "<::> Exp: "+arr[2]+"\n";
        str += "<::> Atk: "+arr[3]+"\n";
        str += "<::> Def: "+arr[4]+"\n";
        str += "<::> Hp: "+arr[5];
        return str;
    }

    public static int isLimit(int size) {
        int num = 0;
        for (int i = 0; i < (CHaracter.activeCharacters.size() - 1); i++) {
            int x1 = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getX();
            int x2 = size;
            int y1 = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getY();
            int y2 = size;
            if ((x1 == 0) || (y1 == 0)) {
                num = 1;
            }
            if ((x1 == (x2 + 1)) || (y1 == (y2 + 1))) {
                num = 1;
            }
        }
        return num;
    }

    public static int isVillain() {
        int num = 0;
        int x1 = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getX();
        int y1 = CHaracter.activeCharacters.get(CHaracter.activeCharacters.size() - 1).getY();
        for (int i = 0; i < (CHaracter.activeCharacters.size() - 1); i++) {
            int x2 = CHaracter.activeCharacters.get(i).getX();
            int y2 = CHaracter.activeCharacters.get(i).getY();
            if (x1 == x2 && y1 == y2) {
                return CHaracter.activeCharacters.get(i).getCharID();
            }
        }
        return num;
    }
}
