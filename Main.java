import java.util.Scanner;

// execute dans un cmd: javac *.java && java Main
public class Main {
    public static void main(String[] args) {
//-------------------Initialisation-------------------------
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu();
        Tamer tamer = null;
        Shop shop = new Shop();
//----------------------------------------------------------
        String choix = menu.MenuLunch();
        switch (choix) {
            case "leave":
                scanner.close();
                return;
            case "new":
                tamer = new Tamer(null,new Inventaire(),2000 );
                menu.MenuStarter(tamer);
                break;
            case "load":
                tamer = LoadManager.loadTamer("save.csv");
                if (tamer == null) {
                    tamer = new Tamer(null,new Inventaire(),2000);
                }
                break;
            default:
                System.out.println("Choix invalide");
                scanner.close();
                return;
        }

        while (true) {
            menu.menu(tamer, shop);
        }
    }
}
