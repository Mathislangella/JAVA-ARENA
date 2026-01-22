import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Clear clear = new Clear();

    public void menu(Tamer tamer) {
        
        while (true){
            System.out.println("\n--Menu--");
            System.out.println("0 Quitter");
            System.out.println("1 Voir l'etat de mon equipe");
            System.out.println("2 Action tamer");
            System.out.println("3 Aller a la boutique");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            if (choix.equals("0")) {
                Menuleave();
            }else if(choix.equals("1")) {
                ShowTeam(tamer);
            }else if(choix.equals("2")) {
                return;
            }else if(choix.equals("3")) {
                return;
            }
        }
    }

    public String MenuLunch() {
        String rep = "";
        while (true){
            System.out.println("0 Quiter");
            System.out.println("1 Commencer une nouvelle partie");
            System.out.println("2 Charger une partie existante");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            if (choix.equals("0")) {
                rep = "leave";
                break;
            }else if (choix.equals("1")){
                rep = "new";
                break;
            }else if (choix.equals("2")){
                rep = "load";
                // cherhce un fichier de sauvegarde csv
                break;
            }else{
                System.err.println("vous devez choisir enrte 0,1 et 2");
            }
        }
        return rep;
    }

    public void ShowTeam(Tamer tamer) {

        final int COL_WIDTH = 21;

        System.out.println("\n-- Etat de l'equipe de " + tamer.getName() + " --");
        System.out.println("--------------------------------- VOTRE EQUIPE ---------------------------------");

        System.out.println("╔═════╦═════════════════════╦═════════════════════╦═════════════════════╗");
        System.out.println("║index║" +
                center("1", COL_WIDTH) + "║" +
                center("2", COL_WIDTH) + "║" +
                center("3", COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Nom ║" +
                center(tamer.getTeam().get(0).getNom(), COL_WIDTH) + "║" +
                center(tamer.getTeam().get(1).getNom(), COL_WIDTH) + "║" +
                center(tamer.getTeam().get(2).getNom(), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║Type ║" +
                center(tamer.getTeam().get(0).getType(), COL_WIDTH) + "║" +
                center(tamer.getTeam().get(1).getType(), COL_WIDTH) + "║" +
                center(tamer.getTeam().get(2).getType(), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Lv  ║" +
                center(String.valueOf(tamer.getTeam().get(0).getLevel()), COL_WIDTH) + "║" +
                center(String.valueOf(tamer.getTeam().get(1).getLevel()), COL_WIDTH) + "║" +
                center(String.valueOf(tamer.getTeam().get(2).getLevel()), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Pv  ║" +
                center(tamer.getTeam().get(0).getPv() + "/" + tamer.getTeam().get(0).getPvmax(), COL_WIDTH) + "║" +
                center(tamer.getTeam().get(1).getPv() + "/" + tamer.getTeam().get(1).getPvmax(), COL_WIDTH) + "║" +
                center(tamer.getTeam().get(2).getPv() + "/" + tamer.getTeam().get(2).getPvmax(), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Dmg ║" +
                center(String.valueOf(tamer.getTeam().get(0).getDmg()), COL_WIDTH) + "║" +
                center(String.valueOf(tamer.getTeam().get(1).getDmg()), COL_WIDTH) + "║" +
                center(String.valueOf(tamer.getTeam().get(2).getDmg()), COL_WIDTH) + "║");

        System.out.println("╚═════╩═════════════════════╩═════════════════════╩═════════════════════╝");
    }


    public void MenuStarter(Tamer tamer) {
        while (true){
            System.out.println("Votre aventure va commencer !");
            System.out.println("Vous aurez 3 starter aléatoire");
            String[] Starters = {"Salamèche", "Carapuce", "Bulbizarre", "Feunnec", "Grenousse", "Marisson"};
            for (int i = 0; i < 3; i++) {
                int randIndex = (int) (Math.random() * Starters.length);
                tamer.addTeam(new Monsters(Starters[randIndex]));
            }
            System.out.println("Vos starters sont : ");
            for (Monsters monstre : tamer.getTeam()) {
                System.out.println("- " + monstre.getNom() + " (Type: " + monstre.getType() + ")");
            }
            System.out.println("Voulez-vous garder ces starters ? (y/n)");
            String choix = scanner.nextLine().toLowerCase();
            clear.ClearConsoleFake();
            if (choix.equals("y")) {
                break;
            } else if (choix.equals("n")) {
                tamer.setTeam(null);
            } else {
                System.out.println("Choix invalide. Veuillez répondre par 'y' ou 'n'.");
            }
        }
    }

    public void Menuleave() {
        while (true){
            System.out.println("0 Rettour au menu principal");
            System.out.println("1 Sauvegarder et quitter");
            System.out.println("2 Quitter sans sauvegarder");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            if (choix.equals("0")) {
                return;
            }else if (choix.equals("1")) {
                // sauvegarder
                return;
            }else if (choix.equals("2")) {
                System.out.println("Vous avez choisi de quitter sans sauvegarder.");
                System.out.println("Merci d'avoir joué ! À bientôt !");
                System.exit(0);
            }else {
                System.err.println("Vous devez choisir entre 0,1 et 2");
            }
        }
    }

    public void MenuShop() {
        // à implémenter
    }

    public void MenuTamer() {
        // à implémenter
    }

    private static String center(String text, int width) {
        if (text == null) text = "";
        if (text.length() >= width) return text.substring(0, width);

        int left = (width - text.length()) / 2;
        int right = width - text.length() - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

}
