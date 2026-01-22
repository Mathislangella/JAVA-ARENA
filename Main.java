import java.util.Scanner;

// execute dans un cmd: javac *.java && java Main.java 
public class Main {
    public static void main(String[] args) {
        // outils
        Clear clear = new Clear();
        Scanner scanner = new Scanner(System.in);
        // initialisation des objets
        Menu menu = new Menu();
        Tamer tamer = null;

        clear.ClearConsoleFake();
        switch (menu.MenuLunch()) {
            case "leave":
                scanner.close();
                return;
            case "new":
                tamer = new Tamer(null, new Inventaire(), 50);
                // create un fichier csv avec les info du tamer
            case "load":
                //load game
                break;
        }
        menu.MenuStarter(tamer);

        while (true) {
            menu.menu(tamer);
        }
    }
}