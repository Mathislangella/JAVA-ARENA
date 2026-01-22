import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class SaveManager {

    public static void saveTamer(Tamer tamer, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {

            // Ligne 1 : Gold
            writer.write(tamer.getMoney() + "\n");

            // Ligne 2 : Inventaire (nom:quantite)
            boolean first = true;
            for (Map.Entry<Consomable, Integer> entry :
                    tamer.getInventaire().entrySet()) {

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

            System.out.println("Sauvegarde réussie !");

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
        }
    }
}
