import java.util.Scanner;
import java.util.regex.Pattern;

public class PaymentGateway {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean processPayment(String paymentMethod, double amount) {
        System.out.println("Przetwarzanie płatności...");
        System.out.println("Metoda płatności: " + paymentMethod);
        System.out.println("Kwota: " + amount + " PLN");

        boolean paymentSuccess = false;

        switch (paymentMethod) {
            case "Karta kredytowa" -> paymentSuccess = processCreditCardPayment(amount);
            case "BLIK" -> paymentSuccess = processBlikPayment(amount);
            case "Przelew bankowy" -> paymentSuccess = processBankTransferPayment(amount);
            default -> System.out.println("Nieznana metoda płatności.");
        }

        if (paymentSuccess) {
            System.out.println("Płatność autoryzowana.");
        } else {
            System.out.println("Płatność nieudana.");
        }

        return paymentSuccess;
    }

    private static boolean processCreditCardPayment(double amount) {
        System.out.print("Podaj numer karty (16 cyfr): ");
        String cardNumber = scanner.nextLine();
        while (!cardNumber.matches("\\d{16}")) {
            System.out.print("Nieprawidłowy numer karty. Podaj ponownie: ");
            cardNumber = scanner.nextLine();
        }

        System.out.print("Podaj datę ważności (MM/RRRR) np (03/2028): ");
        String expirationDate = scanner.nextLine();
        while (!expirationDate.matches("(0[1-9]|1[0-2])/(\\d{4})")) {
            System.out.print("Nieprawidłowa data ważności. Podaj ponownie (MM/RR): ");
            expirationDate = scanner.nextLine();
        }

        System.out.print("Podaj kod CVV (3 cyfry): ");
        String cvv = scanner.nextLine();
        while (!cvv.matches("\\d{3}")) {
            System.out.print("Nieprawidłowy kod CVV. Podaj ponownie: ");
            cvv = scanner.nextLine();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean processBlikPayment(double amount) {
        System.out.print("Podaj kod BLIK (6 cyfr): ");
        String blikCode = scanner.nextLine();
        while (!blikCode.matches("\\d{6}")) {
            System.out.print("Nieprawidłowy kod BLIK. Podaj ponownie: ");
            blikCode = scanner.nextLine();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean processBankTransferPayment(double amount) {
        System.out.print("Podaj nazwę banku: ");
        String bankName = scanner.nextLine();
        while (bankName.trim().isEmpty()) {
            System.out.print("Nazwa banku nie może być pusta. Podaj ponownie: ");
            bankName = scanner.nextLine();
        }

        System.out.print("Podaj numer konta (26 cyfr): ");
        String accountNumber = scanner.nextLine();
        while (!accountNumber.matches("\\d{26}")) {
            System.out.print("Nieprawidłowy numer konta. Podaj ponownie: ");
            accountNumber = scanner.nextLine();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
