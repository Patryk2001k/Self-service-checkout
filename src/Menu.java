import java.util.Scanner;

public class Menu {
    public void displayCart(Cart cart) {
        cart.displayItems();
    }

    public int displayMenuAndGetChoice(Scanner myObj) {
        System.out.println("Menu:");
        System.out.println("1. Dodaj dodatek");
        System.out.println("2. Dodaj kolejny film");
        System.out.println("3. Przejdź do płatności");
        System.out.println("4. Koszyk");
        System.out.println("5. Logowanie / Rejestracja");
        System.out.print("Wybierz opcję: ");
        return myObj.nextInt();
    }

    public void addAddonToCart(Cart cart, Scanner myObj) {
        System.out.println("Dostępne dodatki:");
        String[] addons = {"Popcorn", "Napój", "Zestaw"};
        double[] addonsPrices = {10.0, 5.0, 15.0};
        for (int i = 0; i < addons.length; i++) {
            System.out.println(i + ". " + addons[i] + " - " + addonsPrices[i] + " PLN");
        }

        int addonChoice;
        while (true) {
            System.out.print("Wybierz numer dodatku: ");
            addonChoice = myObj.nextInt();
            myObj.nextLine();  // Clear the buffer
            if (addonChoice >= 0 && addonChoice < addons.length) {
                break;
            }
            System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
        }

        CartItem addonItem = new CartItem(null, -1, "addon", addonsPrices[addonChoice], addons[addonChoice]);
        cart.addItem(addonItem);
        System.out.println("Dodano dodatek: " + addons[addonChoice]);
    }

    public boolean cartMenu(Cart cart, Scanner myObj) {
        while (true) {
            cart.displayItems();
            System.out.println("Koszyk:");
            System.out.println("1. Usuń przedmiot z koszyka");
            System.out.println("2. Wyczyść koszyk");
            System.out.println("3. Powrót do głównego menu");
            System.out.print("Wybierz opcję: ");
            int cartChoice = myObj.nextInt();
            switch (cartChoice) {
                case 1:
                    removeItemFromCart(cart, myObj);
                    return !cart.isEmpty();
                case 2:
                    clearCart(cart);
                    return false;
                case 3:
                    return true;
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
                    break;
            }
        }
    }

    public void removeItemFromCart(Cart cart, Scanner myObj) {
        if (cart.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }

        cart.displayItems();
        System.out.print("Wybierz numer przedmiotu do usunięcia: ");
        int itemIndex = myObj.nextInt();
        cart.removeItem(itemIndex);
        System.out.println("Przedmiot usunięty.");
    }

    public void clearCart(Cart cart) {
        cart.clearCart();
        System.out.println("Koszyk wyczyszczony.");
    }
}
