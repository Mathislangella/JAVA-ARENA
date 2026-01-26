package src.outils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import src.items.Consomable;
import src.monsters.Monsters;
import src.player.Tamer;

public class SaveManager {

    private static final String SAVE_FOLDER = "csv"; // dossier des sauvegardes

    // Crée automatiquement le nom de fichier suivant save1.csv, save2.csv...
    public static String getNextSaveFile() {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists()) folder.mkdir();

        int i = 1;
        File file;
        do {file = new File(folder, "save" + i + ".csv");
            i++;
        } while (file.exists());

        return file.getPath();
    }

    public static void saveTamer(Tamer tamer) {
        String filename = getNextSaveFile();

        try (FileWriter writer = new FileWriter(filename)) {

            // Ligne 1 : Gold
            writer.write(tamer.getMoney() + "\n");

            // Ligne 2 : Inventaire (nom:quantite)
            boolean first = true;
            for (Map.Entry<Consomable, Integer> entry : tamer.getInventaire().entrySet()) {
                if (!first) writer.write(";");
                writer.write(entry.getKey().getName() + ":" + entry.getValue());
                first = false;
            }
            writer.write("\n");

            // Ligne 3 : Team (nom:level)
            ArrayList<Monsters> team = tamer.getTeam();
            for (int i = 0; i < team.size(); i++) {
                Monsters m = team.get(i);
                writer.write(m.getNom() + ":" + m.getLevel());
                if (i < team.size() - 1) writer.write(";");
            }
            writer.write("\n");

            System.out.println("Sauvegarde réussie dans " + filename);

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
