package src.player;
import java.util.ArrayList;
import java.util.HashMap;

import src.items.Consomable;
import src.monsters.Monsters;

public class Tamer {
    private String name;
    private ArrayList<Monsters> team = new ArrayList<Monsters>();
    private Inventaire inventaire;
    private int money;

    public Tamer(ArrayList<Monsters> team, Inventaire inventaire, int money) {
        this.name = "Ash";
        setTeam(team);
        this.inventaire = inventaire;
        this.money = money;
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

    public HashMap<Consomable, Integer> getInventaire() {
        return this.inventaire.getInventaire();
    }
    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }
    public Inventaire getInventaireObj() {
        return inventaire;
    }

    public int getMoney() {
        return this.money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }
    public boolean removeMoney(int amount) {
        if (this.money >= amount) {
            this.money -= amount;
            return true;
        }
        return false;
    }

    public boolean isteamdead() {
        for (Monsters monster : this.team) {
            if (!monster.IsKO()) {
                return false;
            }
        }
        return true;
    }  

    public int averageLevel() {
        int totalLevel = 0;
        for (Monsters monster : this.team) {
            totalLevel += monster.getLevel();
        }
        return totalLevel / this.team.size();
    }

    public void addConsomable(Consomable consomable, int quantite) {
        this.inventaire.addConsomable(consomable, quantite);
    }

    public void useConsomable() {
        }

}
