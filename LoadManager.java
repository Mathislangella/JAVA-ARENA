import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadManager {

    public static Tamer loadTamer(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.err.println("Aucune sauvegarde trouvée !");
            return null;
        }

        try (Scanner scanner = new Scanner(file)) {

            Tamer tamer = new Tamer(null, new Inventaire(), 0);

            // Gold
            tamer.setMoney(Integer.parseInt(scanner.nextLine()));

            // Inventaire
            tamer.getInventaireObj().clear();
            String[] items = scanner.nextLine().split(";");
            for (String item : items) {
                if (!item.isEmpty()) {
                    String[] parts = item.split(":");
                    tamer.getInventaireObj()
                            .setConsomable(new Consomable(parts[0]),
                                    Integer.parseInt(parts[1]));
                }
            }

            // Team
            String[] monsters = scanner.nextLine().split(";");
            for (String m : monsters) {
                if (!m.isEmpty()) {
                    String[] parts = m.split(":");
                    tamer.addTeam(new Monsters(parts[0],
                            Integer.parseInt(parts[1])));
                }
            }

            System.out.println("Chargement réussi !");
            return tamer;

        } catch (FileNotFoundException e) {
            System.err.println("Erreur chargement fichier");
            return null;
        }
    }
}
