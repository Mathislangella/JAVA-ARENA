package src.shop;
import src.items.Consomable;

public class ShopItem {

    private Consomable consomable;
    private int prix;
    private int quantite;

    public ShopItem(String nom, int prix, int quantite) {
        this.consomable = new Consomable(nom);
        this.prix = prix;
        this.quantite = quantite;
    }

    public Consomable getConsomable() {
        return consomable;
    }

    public int getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void buyOne() {
        if (quantite > 0) {
            quantite--;
        }
    }
}
