package src.shop;
import java.util.ArrayList;

public class Shop {

    private ArrayList<ShopItem> inventaire = new ArrayList<>();

    // Inventaire de base
    public Shop() {
        inventaire.add(new ShopItem("Potion", 300, 10));
        inventaire.add(new ShopItem("Super Potion", 700, 5));
        inventaire.add(new ShopItem("Hyper Potion", 1200, 2));

        inventaire.add(new ShopItem("Poké Ball", 200, 15));
        inventaire.add(new ShopItem("Super Ball", 600, 7));
        inventaire.add(new ShopItem("Hyper Ball", 1200, 3));

        inventaire.add(new ShopItem("Rappel", 1500, 3));
    }

    public boolean Buy(String nom, int money) {
        for (ShopItem item : inventaire) {
            if (item.getConsomable().getName().equalsIgnoreCase(nom)) {

                if (item.getQuantite() <= 0) return false;
                if (money < item.getPrix()) return false;

                item.buyOne();
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<ShopItem> getInventaire() {
        return inventaire;
    }
}
