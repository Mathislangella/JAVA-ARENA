package src.menu;
import java.util.Scanner;

import src.items.Consomable;
import src.monsters.InfoMonstre;
import src.monsters.Monsters;
import src.outils.Clear;
import src.outils.LoadManager;
import src.outils.SaveManager;
import src.player.Inventaire;
import src.player.Tamer;
import src.shop.Shop;


public class Menu {
    Scanner scanner = new Scanner(System.in);
    Clear clear = new Clear();

//----------------- MENUS ----------------------//    
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

    public Tamer MenuLunch() {
        clear.ClearConsoleFake();
        Tamer tamer = null;

        while (tamer == null) {
            System.out.println("0 Quiter");
            System.out.println("1 Commencer une nouvelle partie");
            System.out.println("2 Charger une partie existante");

            String choix = scanner.nextLine();
            clear.ClearConsoleFake();

            switch (choix) {
                case "0":
                    System.exit(0); // quitte directement
                    break;
                case "1":
                    tamer = new Tamer(null, new Inventaire(), 2000);
                    MenuStarter(tamer);
                    break;
                case "2":
                    tamer = LoadManager.chooseAndLoadTamer();
                    if (tamer == null) {
                        System.out.println("Aucune sauvegarde trouvée. Création d'un nouveau Tamer...");
                        tamer = new Tamer(null, new Inventaire(), 2000);
                        MenuStarter(tamer);
                    }
                    break;
                default:
                    System.out.println("Vous devez choisir entre 0,1 et 2");
            }
        }

        return tamer;
    }

    private void ShowTeam(Tamer tamer) {

        final int COL_WIDTH = 21;

        System.out.println("\n-- Etat de l'equipe de " + tamer.getName() + " --");
        System.out.println("--------------------------------- VOTRE EQUIPE ---------------------------------");

        System.out.println("╔═════╦═════════════════════╦═════════════════════╦═════════════════════╗");
        System.out.println("║index║" +
                Center("1", COL_WIDTH) + "║" +
                Center("2", COL_WIDTH) + "║" +
                Center("3", COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Nom ║" +
                Center(tamer.getTeam().get(0).getNom(), COL_WIDTH) + "║" +
                Center(tamer.getTeam().get(1).getNom(), COL_WIDTH) + "║" +
                Center(tamer.getTeam().get(2).getNom(), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║Type ║" +
                Center(tamer.getTeam().get(0).getType(), COL_WIDTH) + "║" +
                Center(tamer.getTeam().get(1).getType(), COL_WIDTH) + "║" +
                Center(tamer.getTeam().get(2).getType(), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Lv  ║" +
                Center(String.valueOf(tamer.getTeam().get(0).getLevel()), COL_WIDTH) + "║" +
                Center(String.valueOf(tamer.getTeam().get(1).getLevel()), COL_WIDTH) + "║" +
                Center(String.valueOf(tamer.getTeam().get(2).getLevel()), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Pv  ║" +
                Center(tamer.getTeam().get(0).getPv() + "/" + tamer.getTeam().get(0).getPvmax(), COL_WIDTH) + "║" +
                Center(tamer.getTeam().get(1).getPv() + "/" + tamer.getTeam().get(1).getPvmax(), COL_WIDTH) + "║" +
                Center(tamer.getTeam().get(2).getPv() + "/" + tamer.getTeam().get(2).getPvmax(), COL_WIDTH) + "║");
        System.out.println("╠═════╬═════════════════════╬═════════════════════╬═════════════════════╣");

        System.out.println("║ Dmg ║" +
                Center(String.valueOf(tamer.getTeam().get(0).getDmg()), COL_WIDTH) + "║" +
                Center(String.valueOf(tamer.getTeam().get(1).getDmg()), COL_WIDTH) + "║" +
                Center(String.valueOf(tamer.getTeam().get(2).getDmg()), COL_WIDTH) + "║");

        System.out.println("╚═════╩═════════════════════╩═════════════════════╩═════════════════════╝");
    }

    private void MenuStarter(Tamer tamer) {
        while (true){
            System.out.println("Votre aventure va commencer !");
            System.out.println("Vous aurez 3 starter aléatoire");
            String[] Starters = {"Salamèche", "Carapuce", "Bulbizarre", "Feunnec", "Grenousse", "Marisson"};
            tamer.setTeam(null);
            for (int i = 0; i < 3; i++) {
                int randIndex = (int) (Math.random() * Starters.length);
                tamer.addTeam(new Monsters(Starters[randIndex], 5));
            }
            System.out.println("Vos starters sont : ");
            for (Monsters monstre : tamer.getTeam()) {
                System.out.println("- " + monstre.getNom() + " (Type: " + monstre.getType() + ")");
            }
            System.out.println("Voulez-vous garder ces starters ? (1 = oui /2 = non)");
            String choix = scanner.nextLine().toLowerCase();
            clear.ClearConsoleFake();
            if (choix.equals("1")) {
                break;
            } else if (choix.equals("2")) {
                tamer.setTeam(null);
            } else {
                System.out.println("Choix invalide. Veuillez répondre par '1' ou '2'.");
            }
        }
    }

    private void Menuleave(Tamer tamer) {
        while (true){
            System.out.println("0 Rettour au menu principal");
            System.out.println("1 Sauvegarder et quitter");
            System.out.println("2 Quitter sans sauvegarder");

            String choix = scanner.nextLine(); 
            clear.ClearConsoleFake();
            switch (choix) {
                case "0":
                    scanner.close();
                    return;
                case "1":
                    SaveManager.saveTamer(tamer);
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

    private void MenuShop(Tamer tamer, Shop shop) {
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

    private void MenuTamer(Tamer tamer) {
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
                    Battle(tamer);
                    return;
                case "2":
                    MenuInventaire(tamer,null);
                    break;
                default:
                    System.err.println("Vous devez choisir entre 0,1 et 2");
            }
        }
    }

    private String MenuInventaire(Tamer tamer,Monsters monstre_wild) {
        Monsters monstre = null;
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
            System.out.println("0. Retour");

            String choix = scanner.nextLine();
            clear.ClearConsoleFake();

            if (choix.equals("0")) {
                return "";
            } else if (Integer.parseInt(choix) > 0 && Integer.parseInt(choix) <= tamer.getInventaire().size()) {
                Consomable selectedItem = (Consomable) tamer.getInventaire().keySet().toArray()[Integer.parseInt(choix) - 1];
                System.out.println("Vous avez utilisé un(e) " + selectedItem.getName() + ".");
                if (selectedItem.getType() == "Capture"){
                    monstre = monstre_wild;
                }
                String repconso = UseConsomable(selectedItem, tamer,monstre);
                if (repconso == "capturer") {
                    return "capturer";
                }else if (repconso == "pas capturer"){
                    return "pas capturer";
                }else if (repconso == "trop de vie"){
                    return "trop de vie";
                }
            } else {
                System.err.println("Vous devez choisir un nombre entre 0 et " + tamer.getInventaire().size());
            }
        }
    }

//----------------- COMBAT ----------------------//
    private void InBattle(Tamer tamer, Monsters monstre_choisi, Monsters monstre_wild) {
        while (true) {
            System.out.print("""
                                                                
                                                                        ╔═══════════════════════════════════╗
                                                                        ║         Monstre sauvage           ║
                                                                        ║                                   ║
                                                                        ║   %s LV:%d                      
                                                                        ║   %s                              
                                                                        ║                                   ║
                                                                        ║                                   ║
                                                                        ╚═══════════════════════════════════╝

            ╔═══════════════════════════════════╗
            ║           Votre Monstre           ║
            ║                                   ║
            ║   %s LV:%d
            ║   %s
            ║   PV: %s                        
            ║   XP: %s                        
            ║                                   ║
            ║                                   ║
            ╚═══════════════════════════════════╝

            Veuillez choisir une action

                        ╔═════════════════════════════════════════════════════════════════════════════╗
                        ║   1 - Attaque   |    2 - Pokemon    |    3 - Objet      |    4 - Fuite      ║
                        ╚═════════════════════════════════════════════════════════════════════════════╝
            """.formatted(
                    monstre_wild.getNom(),monstre_wild.getLevel(),
                    Barre_de_vie(monstre_wild.getPv(), monstre_wild.getPvmax(), 20),

                    monstre_choisi.getNom(),monstre_choisi.getLevel(),
                    Barre_de_vie(monstre_choisi.getPv(), monstre_choisi.getPvmax(), 20),
                    monstre_choisi.getPv() + "/" + monstre_choisi.getPvmax(),
                    monstre_choisi.getXp() + "/" + monstre_choisi.getXp_max()
            ));

            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.println("Votre "+ monstre_choisi.getNom() + " attaque !");
                    monstre_wild.takeDmg(CalcateDmg(monstre_choisi, monstre_wild));
                    System.out.println("Le monstre sauvage perd " + monstre_choisi.getDmg() + " points de vie !");

                    System.out.println("le monstre sauvage riposte !");
                    monstre_choisi.takeDmg(CalcateDmg(monstre_wild,monstre_choisi));
                    System.out.println("Votre monstre perd " + monstre_wild.getDmg() + " points de vie !");

                    System.out.println("Appuyez sur une touche pour continuer...");
                    scanner.nextLine();
                    if (tamer.isteamdead()) {
                        System.out.println("Tous vos monstres sont vaincus !");
                        System.out.println("Vous avez perdu le combat !");
                        return;
                    }
                    if (monstre_wild.IsKO() == true) {
                        System.out.println("Vous avez vaincu le monstre sauvage !");
                        int xp_gagne = monstre_wild.getLevel() * 20;
                        System.out.println("Votre monstre gagne " + xp_gagne + " points d'expérience !");
                        monstre_choisi.gainXP(xp_gagne);
                        if (monstre_choisi.peutEvoluer()) {
                            System.out.println("Votre monstre peut évoluer !");
                            monstre_choisi.evoluer();
                            System.out.println("Félicitations ! Votre monstre a évolué en " + monstre_choisi.getNom() + " !");
                        }
                        clear.ClearConsoleFake();
                        return;
                    }else if (monstre_choisi.IsKO() == true) {
                        System.out.println("Votre monstre est vaincu !");
                        System.out.println("vous devez choisir un autre monstre pour continuer le combat !");
                        monstre_choisi = ChoosMonster(tamer);
                    }
                    break;
                case "2":
                    monstre_choisi = ChoosMonster(tamer);
                    break;
                case "3":
                    String rep = MenuInventaire(tamer,monstre_wild);
                    if (rep == "capturer") {
                        System.out.println("vous avez capturer "+monstre_wild.getNom()+" lv : "+monstre_wild.getLevel() );
                        tamer.addTeam(monstre_wild);
                        monstre_choisi.gainXP(50);
                        return;
                    }else if (rep == "pas capturer") {
                        System.out.println("vous n'avez pas réussi a capturer "+monstre_wild.getNom());
                    }else if (rep == "pas capturer") {
                        System.out.println("vous ne pouvez pas capturer "+monstre_wild.getNom()+" il a trop de pv .");
                    }
                    break;
                case "4":
                    System.err.println("Vous essayez de fuire");

                    System.err.println("Vous avez réussit a fuire");
                    return;
                default:
                    System.err.println("Vous devez choisir une action entre 1, 2 ou 3");
                    break;
            }
            clear.ClearConsoleFake();
        }
    }

    private void Battle(Tamer tamer) {
        System.out.println("Un monstre sauvage apparait !");
        System.out.println("Vous engagez le combat !");

        Monsters monstre_wild = GenerateMonsterWild(tamer);
        Monsters monstre_choisi = ChoosMonster(tamer);

        System.out.println("Vous avez choisi " + monstre_choisi.getNom() + " pour commencer le combat !");
        InBattle(tamer, monstre_choisi, monstre_wild);

    }

    private Monsters ChoosMonster(Tamer tamer) {
        while (true) {
            System.out.println("""
                ╔═══════════════════════════════════╗
                ║                                   ║
                ║         Choix du monstre          ║""");
            int index = 0;
            for (Monsters monster : tamer.getTeam()) {
                index += 1;
                System.out.print("║  "  + index + "  " +monster.getNom() + "   lv:" + monster.getLevel() + "    PV: " + monster.getPv() + "/" + monster.getPvmax() + "");
                if (monster.IsKO()){
                    System.out.println("|  Ce Pokemon est KO");
                }else{
                    System.out.println("");
                }
            }
            System.out.print("""
        ║                                   ║
        ╚═══════════════════════════════════╝                                      
            """);
            System.out.println("Choisissez quelle monstre vas commencer le combat:");
            String choix = scanner.nextLine();
            try {
                int intchoix = Integer.parseInt(choix) -1;
                if (intchoix >= 0 && intchoix < tamer.getTeam().size()) {
                    if (tamer.getTeam().get(intchoix).IsKO()){
                        System.err.println(tamer.getTeam().get(intchoix).getNom()+"est KO il ne peut pas être choisit");
                    }else {
                        System.out.println("Vous avez choisi "+ tamer.getTeam().get(intchoix).getNom());
                        Monsters rep = tamer.getTeam().get(intchoix);
                        return rep;
                    }
                }else{
                    System.err.println("Vous devez choisir un monstre entre 1, 2 ou 3");
                }
            } catch (Exception e){
                System.err.println("Vous devez choisir un monstre entre 1, 2 ou 3");
            }
        }
    }

    private Monsters GenerateMonsterWild(Tamer tamer) {
        int Avrlevel = tamer.averageLevel();
        return new Monsters("WILD", Avrlevel);
    }

//----------------- OUTILS ----------------------//
    private String UseConsomable(Consomable selectedItem, Tamer tamer ,Monsters monstre_wild) {
        if (selectedItem.getType().equals("Potion")) {
            System.out.println("Choisissez le monstre à soigner :");
            while (true) {
                for (int i = 0; i < tamer.getTeam().size(); i++) {
                    Monsters monstre = tamer.getTeam().get(i);
                    System.out.println((i + 1) + ". " + monstre.getNom() + " (PV: " + monstre.getPv() + "/" + monstre.getPvmax() + ")");
                }
                String choix = scanner.nextLine();
                int monstreIndex = Integer.parseInt(choix) - 1;
                if (monstreIndex >= 0 && monstreIndex < tamer.getTeam().size()) {
                    if (tamer.getTeam().get(monstreIndex).IsKO()) {
                        System.err.println("Choix invalide. Se pokemon est KO pour le relever utiliser un rappel");
                        return "";
                    }else{
                        if (tamer.getTeam().get(monstreIndex).getPv() == tamer.getTeam().get(monstreIndex).getPvmax()){
                            System.err.println("Choix invalide. Se pokemon a deja ses PV aux max");
                            return "";
                        }else {
                            Monsters monstreChoisi = tamer.getTeam().get(monstreIndex);
                        int newPv = Math.min(monstreChoisi.getPv() + selectedItem.getPuissance(), monstreChoisi.getPvmax());
                        monstreChoisi.setPv(newPv);
                        System.out.println(monstreChoisi.getNom() + " a été soigné et a maintenant " + monstreChoisi.getPv() + " PV.");
                        tamer.getInventaireObj().removeConsomable(selectedItem.getName(), 1);
                        return "";
                        }
                    }
                } else {
                    System.err.println("Choix invalide. Veuillez réessayer. choisissez un nombre entre 1 et " + tamer.getTeam().size());
                    return "";
                }
            }
        }else if(selectedItem.getType().equals("Rappel")) {
            System.out.println("Choisissez le sur le quel utiliser le rappel:");
            while (true) {
                for (int i = 0; i < tamer.getTeam().size(); i++) {
                    Monsters monstre = tamer.getTeam().get(i);
                    System.out.println((i + 1) + ". " + monstre.getNom() + " (PV: " + monstre.getPv() + "/" + monstre.getPvmax() + ")");
                }
                String choix = scanner.nextLine();
                int monstreIndex = Integer.parseInt(choix) - 1;
                if (monstreIndex >= 0 && monstreIndex < tamer.getTeam().size()) {
                    if (tamer.getTeam().get(monstreIndex).IsKO()) {
                        Monsters monstreChoisi = tamer.getTeam().get(monstreIndex);
                        int newPv = Math.min(monstreChoisi.getPv() + selectedItem.getPuissance(), monstreChoisi.getPvmax());
                        monstreChoisi.setPv(newPv);
                        System.out.println(monstreChoisi.getNom() + " a été relever et a maintenant " + monstreChoisi.getPv() + " PV.");
                        tamer.getInventaireObj().removeConsomable(selectedItem.getName(), 1);
                        return "";
                    }else{
                        System.err.println("Choix invalide. Se pokemon n'es pas KO pour le soigner utilisez une potion");
                        return "";
                    }
                } else {
                    System.err.println("Choix invalide. Veuillez réessayer. choisissez un nombre entre 1 et " + tamer.getTeam().size());
                    return "";
                }
            }
        }else if(selectedItem.getType().equals("Capture") && (monstre_wild != null)) {
            if (monstre_wild.Is30()){
                double pvRatio = (double) monstre_wild.getPv() / monstre_wild.getPvmax();
                double baseChance = 0.3; // 30%
                double chance = baseChance * selectedItem.getPuissance() * (1 - pvRatio);
                chance = Math.min(chance, 0.95);
                chance = Math.max(chance, 0.02);
                if (Math.random() < chance){
                    return "capturer";
                }
                return "pas capturer";
            }
            return "trop de vie";
        }
        return "";
    }

    private String Barre_de_vie(int pv_actuels, int pv_max, int longueur) {
        double pourcentage = (double) pv_actuels / pv_max;
        int remplissage = (int) (pourcentage * longueur);
        String barre = "[" + "=".repeat(remplissage) + " ".repeat(longueur - remplissage) + "]";
        return barre;
    }

    private static String Center(String text, int width) {
        if (text == null) text = "";
        if (text.length() >= width) return text.substring(0, width);

        int left = (width - text.length()) / 2;
        int right = width - text.length() - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

    private static int CalcateDmg(Monsters atq, Monsters def) {
        double temp =  (atq.getDmg() * InfoMonstre.getMultiplicateurType(atq.getType(), def.getType()));
        int rep = (int) Math.round(temp);
        return rep;
    }   
}
