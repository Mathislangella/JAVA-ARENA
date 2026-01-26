package src.monsters;
public class Monsters {

    private InfoMonstre info;

    private int pvMax;
    private int pv;
    private int dmg;

    private int level;
    private int xp;
    private int xpCap;
    private boolean KO = false;

    public Monsters(String nom,int level) {
        if (nom == "WILD"){
            this.info = InfoMonstre.getWild(level);
        }else{
            this.info = InfoMonstre.get(nom);
        }
        this.pvMax = info.pvBase;
        this.pv = pvMax;
        this.dmg = info.attaqueBase;
    
        this.level = level;
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
            KO = false;
        }
    }

    public boolean peutEvoluer() {
        return info.evolution != null && level >= info.lvmax;
    }

    public void evoluer() {
        if (!peutEvoluer()) return;

        info = InfoMonstre.get(info.evolution);
        pvMax = info.pvBase;
        dmg = info.attaqueBase;
        pv = pvMax;
    }

    public void takeDmg(int amount) {
        pv -= amount;
        if (pv <= 0) {
            pv = 0;
            KO = true;
        }
    }

    public String getNom() { return info.nom; }
    public String getType() { return info.type; }
    public int getPv() { return this.pv; }
    public int getPvmax() { return this.pvMax; }
    public int getXp() { return this.xp; }
    public int getXp_max() { return this.xpCap; }
    public int getDmg() { return this.dmg; }
    public int getLevel() { return level; }
    public boolean IsKO() { return KO; }

    public void setPv(int pv) {
        this.pv = pv;
        if (this.pv <= 0) {
            this.pv = 0;
            this.KO = true;
        } else if (this.pv > this.pvMax) {
            this.pv = this.pvMax;
        }
    }
}