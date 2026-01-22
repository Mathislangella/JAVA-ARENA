public class Monsters {

    private InfoMonstre info;

    private int pvMax;
    private int pv;
    private int dmg;

    private int level;
    private int xp;
    private int xpCap;

    public Monsters(String nom) {
        this.info = InfoMonstre.get(nom);

        this.pvMax = info.pvBase;
        this.pv = pvMax;
        this.dmg = info.attaqueBase;

        this.level = 1;
        this.xp = 0;
        this.xpCap = 100;
    }

    public void gainXP(int amount) {
        xp += amount;
        while (xp >= xpCap) {
            xp -= xpCap;
            level++;
            pvMax += 5;
            dmg += 2;
            pv = pvMax;
            xpCap += 50;
        }
    }

    public boolean peutEvoluer() {
        return info.evolution != null && level >= info.niveauEvolution;
    }

    public void evoluer() {
        if (!peutEvoluer()) return;

        info = InfoMonstre.get(info.evolution);
        pvMax = info.pvBase;
        dmg = info.attaqueBase;
        pv = pvMax;
    }

    public String getNom() { return info.nom; }
    public String getType() { return info.type; }
    public int getPv() { return this.pv; }
    public int getPvmax() { return this.pvMax; }
    public int getDmg() { return this.dmg; }
    public int getLevel() { return level; }
}
