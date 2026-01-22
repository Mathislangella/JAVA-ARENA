import java.util.HashMap;

public class InfoMonstre {
    public final String nom;
    public final String type;
    public final int pvBase;
    public final int attaqueBase;
    public final String evolution;
    public final int niveauEvolution;
    private static HashMap<String, InfoMonstre> base = new HashMap<>();
    // Feu > Plante > Eau > Feu
    private static HashMap<String, String> fortContre = new HashMap<>();

    static {
        fortContre.put("Feu", "Plante");
        fortContre.put("Plante", "Eau");
        fortContre.put("Eau", "Feu");

        // ===== GEN 1 =====
        ajouter(new InfoMonstre("Salamèche", "Feu", 39, 12, "Reptincel", 16));
        ajouter(new InfoMonstre("Reptincel", "Feu", 58, 18, "Dracaufeu", 36));
        ajouter(new InfoMonstre("Dracaufeu", "Feu", 78, 26, null, 0));

        ajouter(new InfoMonstre("Carapuce", "Eau", 44, 10, "Carabaffe", 16));
        ajouter(new InfoMonstre("Carabaffe", "Eau", 59, 16, "Tortank", 36));
        ajouter(new InfoMonstre("Tortank", "Eau", 79, 24, null, 0));

        ajouter(new InfoMonstre("Bulbizarre", "Plante", 45, 10, "Herbizarre", 16));
        ajouter(new InfoMonstre("Herbizarre", "Plante", 60, 16, "Florizarre", 32));
        ajouter(new InfoMonstre("Florizarre", "Plante", 80, 24, null, 0));

        // ===== GEN 6 =====
        ajouter(new InfoMonstre("Feunnec", "Feu", 40, 11, "Roussil", 16));
        ajouter(new InfoMonstre("Roussil", "Feu", 59, 17, "Goupelin", 36));
        ajouter(new InfoMonstre("Goupelin", "Feu", 75, 25, null, 0));

        ajouter(new InfoMonstre("Grenousse", "Eau", 41, 11, "Croâporal", 16));
        ajouter(new InfoMonstre("Croâporal", "Eau", 54, 17, "Amphinobi", 36));
        ajouter(new InfoMonstre("Amphinobi", "Eau", 72, 26, null, 0));

        ajouter(new InfoMonstre("Marisson", "Plante", 56, 9, "Boguérisse", 16));
        ajouter(new InfoMonstre("Boguérisse", "Plante", 61, 15, "Blindépique", 36));
        ajouter(new InfoMonstre("Blindépique", "Plante", 88, 23, null, 0));
    }

    private InfoMonstre(String nom, String type, int pvBase, int attaqueBase,String evolution, int niveauEvolution) {
        this.nom = nom;
        this.type = type;
        this.pvBase = pvBase;
        this.attaqueBase = attaqueBase;
        this.evolution = evolution;
        this.niveauEvolution = niveauEvolution;
    }

    private static void ajouter(InfoMonstre p) {
        base.put(p.nom, p);
    }

    public static InfoMonstre get(String nom) {
        if (!base.containsKey(nom)) {
            throw new IllegalArgumentException("Pokémon inconnu : " + nom);
        }
        return base.get(nom);
    }

    public static double getMultiplicateurType(String typeAttaquant, String typeDefenseur) {
        if (fortContre.get(typeAttaquant).equals(typeDefenseur)) {return 2.0;}
        if (fortContre.get(typeDefenseur).equals(typeAttaquant)) {return 0.5;}
        return 1.0;
    }
}
