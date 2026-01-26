package src.player;
import java.util.HashMap;

import src.items.Consomable;

public class Inventaire {

    private HashMap<Consomable, Integer> inventaire = new HashMap<>();

    public Inventaire() {
        addConsomable(new Consomable("Potion"), 3);
        addConsomable(new Consomable("Poké Ball"), 5);
    }

    public HashMap<Consomable, Integer> getInventaire() {
        return inventaire;
    }

    public void addConsomable(Consomable consomable, int quantite) {
        inventaire.put(consomable,
                inventaire.getOrDefault(consomable, 0) + quantite);
    }
    public void setConsomable(Consomable consomable, int quantite) {
        inventaire.put(consomable, quantite);
    }

    public void clear() {
        inventaire.clear();
    }

    public boolean removeConsomable(String nom, int quantite) {
        for (Consomable c : inventaire.keySet()) {
            if (c.getName().equalsIgnoreCase(nom)) {
                int qte = inventaire.get(c);
                if (qte <= quantite) {
                    inventaire.remove(c);
                } else {
                    inventaire.put(c, qte - quantite);
                }
                return true;
            }
        }
        return false;
    }
}
