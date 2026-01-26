package src.outils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.items.Consomable;
import src.monsters.Monsters;
import src.player.Inventaire;
import src.player.Tamer;

public class LoadManager {

    private static final String SAVE_FOLDER = "csv";

    // Affiche les saves existantes et permet d'en choisir une
    public static Tamer chooseAndLoadTamer() {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists() || folder.listFiles() == null || folder.listFiles().length == 0) {
            System.out.println("Aucune sauvegarde trouvée !");
            return null;
        }

        File[] files = folder.listFiles((d, name) -> name.endsWith(".csv"));
        if (files == null) return null;

        Scanner sc = new Scanner(System.in);

        System.out.println("Sauvagardes disponibles :");
        for (int i = 0; i < files.length; i++) {
            Tamer tamer = loadTamer(files[i].getPath(), false); // false = pas afficher message "Chargement réussi"
            System.out.printf("%d. %s - Argent: %d, Team: ", i+1, files[i].getName(), tamer.getMoney());
            for (Monsters m : tamer.getTeam()) {
                System.out.print(m.getNom() + " (Lv" + m.getLevel() + ") ");
            }
            System.out.println();
        }

        System.out.println("Choisissez une sauvegarde à charger (0 pour annuler) :");
        int choix = -1;
        while (choix < 0 || choix > files.length) {
            try {
                choix = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) { choix = -1; }
        }

        if (choix == 0) return null;

        return loadTamer(files[choix-1].getPath(), true);
    }

    // charge un tamer depuis un fichier
    private static Tamer loadTamer(String filename, boolean showMessage) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            Tamer tamer = new Tamer(null, new Inventaire(), 0);

            // Gold
            tamer.setMoney(Integer.parseInt(scanner.nextLine()));

            // Inventaire
            tamer.getInventaireObj().clear();
            String lineItems = scanner.nextLine();
            if (!lineItems.isEmpty()) {
                String[] items = lineItems.split(";");
                for (String item : items) {
                    if (!item.isEmpty()) {
                        String[] parts = item.split(":");
                        tamer.getInventaireObj().setConsomable(new Consomable(parts[0]),
                                Integer.parseInt(parts[1]));
                    }
                }
            }

            // Team
            String lineTeam = scanner.nextLine();
            if (!lineTeam.isEmpty()) {
                String[] monsters = lineTeam.split(";");
                for (String m : monsters) {
                    String[] parts = m.split(":");
                    tamer.addTeam(new Monsters(parts[0], Integer.parseInt(parts[1])));
                }
            }

            if (showMessage) System.out.println("Chargement réussi !");
            return tamer;

        } catch (FileNotFoundException e) {
            System.err.println("Erreur chargement fichier : " + filename);
            return null;
        }
    }
}
