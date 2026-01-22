import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Clear clear = new Clear();

    public void menu(Tamer tamer , Shop shop) {
        
        while (true){
            System.out.println("\n-----Menu Pincipale-----");
            System.out.println("0 Quitter");
            System.out.println("1 Voir l'etat de mon equipe");
            System.out.println("2 Action tamer");
            System.out.println("3 Aller a la boutique");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            switch (choix) {
                case "0":
                    Menuleave(tamer);
                    break;
                case "1":
                    ShowTeam(tamer);
                    break;
                case "2":
                    MenuTamer(tamer);
                    break;
                case "3":
                    MenuShop(tamer, shop);
                    break;
                default:
                    System.err.println("Vous devez choisir entre 0,1,2 et 3");
            }
        }
    }

    public String MenuLunch() {
        clear.ClearConsoleFake();
        String rep = "";
        while (true){
            System.out.println("0 Quiter");
            System.out.println("1 Commencer une nouvelle partie");
            System.out.println("2 Charger une partie existante");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            switch (choix) {
                case "0":
                    rep = "leave";
                    break;
                case "1":
                    rep = "new";
                    break;
                case "2":
                    rep = "load";
                    break;
                default:
                    System.out.println("Vous devez choisir entre 0,1 et 2");
            }
            if (!rep.equals("")) {
                break;
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
                tamer.addTeam(new Monsters(Starters[randIndex], 5));
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

    public void Menuleave(Tamer tamer) {
        while (true){
            System.out.println("0 Rettour au menu principal");
            System.out.println("1 Sauvegarder et quitter");
            System.out.println("2 Quitter sans sauvegarder");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            switch (choix) {
                case "0":
                    return;
                case "1":
                    SaveManager.saveTamer(tamer, "save.csv");
                    System.out.println("Merci d'avoir joué ! À bientôt !");
                    System.exit(0);
                    break;
                case "2":
                    System.out.println("Vous avez choisi de quitter sans sauvegarder.");
                    System.out.println("Merci d'avoir joué ! À bientôt !");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vous devez choisir entre 0,1 et 2");
            }
            
        }
    }

    public void MenuShop(Tamer tamer, Shop shop) {
        while (true) {
            System.out.println("Marchand : Bienvenu dans ma boutique.\n\n");
            System.out.println("╔══════╦════╦════════╦══════════════════════════════════════╗");
            System.out.println("║index ║Prix║Quantite║Object                                ║");
            System.out.println("╠══════╬════╬════════╬══════════════════════════════════════╣");
            for (int i = 0; i < shop.getInventaire().size(); i++) {
                System.out.printf("║%-6d║%-4d║%-8d║%-38s║\n",
                    i + 1,
                    shop.getInventaire().get(i).getPrix(),
                    shop.getInventaire().get(i).getQuantite(),
                    shop.getInventaire().get(i).getConsomable().getName()); }
            System.out.println("╚══════╩════╩════════╩══════════════════════════════════════╝\n");
            System.out.printf("Il vous reste %-4d pièce d'or\n", tamer.getMoney());
            System.out.println("0. Quitter le Marchand");
            System.out.println("Marchand : Que voulais vous acheter ?");
            String choix = scanner.nextLine();
            if (choix.equals("0")) {
                break;
            } 
            else if (Integer.parseInt(choix) > 0 && Integer.parseInt(choix) <= shop.getInventaire().size()) {
                String itemName = shop.getInventaire().get(Integer.parseInt(choix) - 1).getConsomable().getName();
                int itemPrice = shop.getInventaire().get(Integer.parseInt(choix) - 1).getPrix();

                if (tamer.getMoney() >= itemPrice) {
                    boolean success = shop.Buy(itemName, tamer.getMoney());
                    if (success) {
                        tamer.setMoney(tamer.getMoney() - itemPrice);
                        tamer.addConsomable(new Consomable(itemName), 1);

                        System.out.println("Vous avez acheté un(e) " + itemName +" pour " + itemPrice + " pièce(s) d'or.");
                    } else {
                        System.out.println("L'achat a échoué. Veuillez réessayer.");}
                } else {
                    System.out.println( "Vous n'avez pas assez d'argent pour acheter cet objet.");}
            } else {
                System.err.println("Vous devez choisir un nombre entre 0 et " +shop.getInventaire().size());
            }
        }
    }

    public void MenuTamer(Tamer tamer) {
        while (true){
            System.out.println("0 Rettour au menu principal");
            System.out.println("1 Attaquer un monstre sauvage");
            System.out.println("2 Ouvrir l'inventaire");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            switch (choix) {
                case "0":
                    return;
                case "1":
                    MenuBattle(tamer);
                    break;
                case "2":
                    MenuInventaire(tamer);
                    break;
                default:
                    System.err.println("Vous devez choisir entre 0,1 et 2");
            }
        }
    }

    public void MenuInventaire(Tamer tamer) {
        while (true) {
            System.out.println("Ash ouvre son sac à dos.\n");
            System.out.println("╔══════╦══════════╦══════════════════════════════════════╗");
            System.out.println("║Index ║Quantité  ║Objet                                 ║");
            System.out.println("╠══════╬══════════╬══════════════════════════════════════╣");
            int index = 1;
            for (var entry : tamer.getInventaire().entrySet()) {
                Consomable c = entry.getKey();
                int quantite = entry.getValue();
                System.out.printf("║%-6d║%-10d║%-38s║\n",index, quantite, c.getName());
                index++;
            }
            System.out.println("╚══════╩══════════╩══════════════════════════════════════╝\n");
            System.out.printf("Il vous reste %d pièces d'or\n", tamer.getMoney());
            System.out.println("0. Retour");

            String choix = scanner.nextLine();
            clear.ClearConsoleFake();

            if (choix.equals("0")) {
                return;
            } else {
                System.err.println("Choix invalide");
            }
        }
    }

    public void MenuBattle(Tamer tamer) {
        while (true){
            System.out.println("0 Rettour au menu principal");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            switch (choix) {
                case "0":
                    return;
                case "1":
                    System.out.println("Toujour pas fini");
                    break;
                default:
                    System.err.println("Toujour pas fini");
            }
        }
    }

    private static String center(String text, int width) {
        if (text == null) text = "";
        if (text.length() >= width) return text.substring(0, width);

        int left = (width - text.length()) / 2;
        int right = width - text.length() - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

}
