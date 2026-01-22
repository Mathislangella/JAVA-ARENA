import java.util.ArrayList;

public class Consomable {
    private String name;
    private String type;
    private int puissance;
    private static ArrayList<Consomable> base = new ArrayList<>();

    static {
        base.add(new Consomable("Potion", "Potion", 20));
        base.add(new Consomable("Super Potion", "Potion", 50));
        base.add(new Consomable("Hyper Potion", "Potion", 200));

        base.add(new Consomable("Poké Ball", "Capture", 1));
        base.add(new Consomable("Super Ball", "Capture", 2));
        base.add(new Consomable("Hyper Ball", "Capture", 3));

        base.add(new Consomable("Rappel", "Rappel", 50));
        base.add(new Consomable("Rappel Max", "Rappel", 100));
        base.add(new Consomable("Rappel Complet", "Rappel", 100));
    }

    public Consomable(String name) {
        for (Consomable c : base) {
            if (c.name.equalsIgnoreCase(name)) {
                this.name = c.name;
                this.type = c.type;
                this.puissance = c.puissance;
                return;
            }
        }
        throw new IllegalArgumentException("Consomable inconnu : " + name);
    }

    private Consomable(String name, String type, int puissance) {
        this.name = name;
        this.type = type;
        this.puissance = puissance;
    }

    public String getName() { 
        return name; }
    public String getType() { 
        return type; }
    public int getPuissance() { 
        return puissance; }
}
