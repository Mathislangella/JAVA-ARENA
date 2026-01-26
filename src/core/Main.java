package src.core;

import src.menu.Menu;
import src.player.Tamer;
import src.shop.Shop;

// execute dans un cmd: javac *.java && java Main
public class Main {
    public static void main(String[] args) {
//-------------------Initialisation-----------------//
        Menu menu = new Menu();
        Tamer tamer = null;
        Shop shop = new Shop();
//-------------------------------------------------//
        tamer = menu.MenuLunch();
        menu.menu(tamer, shop);
        
    }
}
