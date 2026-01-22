import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Consomable> inventaire = new ArrayList<>();

    public Inventaire() {
        inventaire.add(new Consomable("potion"));
    }
    
    public ArrayList<Consomable> geInventaire() {
        return this.inventaire;
    }
    public void setInventaire(ArrayList<Consomable> inventaire) {
        this.inventaire = inventaire;
    }
}
