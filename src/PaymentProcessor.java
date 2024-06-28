import java.util.Scanner;

public class PaymentProcessor {
    public static void processPayment(Scanner myObj, Cart cart) {
        System.out.println("Wybierz metodę płatności: ");
        System.out.println("1. Karta kredytowa");
        System.out.println("2. BLIK");
        System.out.println("3. Przelew bankowy");
        int paymentChoice = myObj.nextInt();
        myObj.nextLine();
        String paymentMethod = "";
        switch (paymentChoice) {
            case 1 -> paymentMethod = "Karta kredytowa";
            case 2 -> paymentMethod = "BLIK";
            case 3 -> paymentMethod = "Przelew bankowy";
            default -> {
                System.out.println("Nieprawidłowy wybór metody płatności, użycie domyślnej: Karta kredytowa.");
                paymentMethod = "Karta kredytowa";
            }
        }

        boolean paymentSuccess = PaymentGateway.processPayment(paymentMethod, cart.getTotalAmount());
        if (paymentSuccess) {
            System.out.println("Płatność zakończona sukcesem.");

            for (CartItem item : cart.items) {
                if (item.type.equals("film")) {
                    TicketPrinter.printTicket(item.screening, item.seatNumber, item.price);
                }
            }
            System.out.println("Dziękujemy za skorzystanie z kasy samoobsługowej. Miłego seansu!");
        } else {
            System.out.println("Płatność nieudana. Spróbuj ponownie.");
        }
    }
}
