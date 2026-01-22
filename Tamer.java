import java.util.ArrayList;

public class Tamer {
    private String name;
    private ArrayList<Monsters> team = new ArrayList<Monsters>();
    private Inventaire inventaire;
    private int credit;

    public Tamer(ArrayList<Monsters> team, Inventaire inventaire, int credit) {
        this.name = "Ash";
        setTeam(team);
        this.inventaire = inventaire;
        this.credit = credit;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Monsters> getTeam() {
        return this.team;
    }
    public void setTeam(ArrayList<Monsters> team) {
        if (team == null) {
            this.team = new ArrayList<Monsters>();
        } else {
            this.team = team;
        }
    }
    public void addTeam(Monsters monstre) {
        this.team.add(monstre);
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }
    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public int getCredit() {
        return this.credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
}
