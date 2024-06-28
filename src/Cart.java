import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item);
        if (item.getType().equals("film") && item.getScreening() != null) {
            item.getScreening().occupySeat(item.getSeatNumber());
        }
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            CartItem item = items.get(index);
            if (item.getType().equals("film") && item.getScreening() != null) {
                item.getScreening().releaseSeat(item.getSeatNumber());
            }
            items.remove(index);
        } else {
            System.out.println("Nieprawidłowy indeks.");
        }
    }

    public void clearCart() {
        for (CartItem item : items) {
            if (item.getType().equals("film") && item.getScreening() != null) {
                item.getScreening().releaseSeat(item.getSeatNumber());
            }
        }
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }
        System.out.println("Twój koszyk:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ". " + items.get(i));
        }
        System.out.println("Łączna kwota: " + getTotalAmount() + " PLN");
    }
}
