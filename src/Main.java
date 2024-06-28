import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Cart cart = new Cart();
        CinemaManager cinemaManager = new CinemaManager();
        Menu menu = new Menu();
        UserManager userManager = new UserManager();
        boolean addingItems = true;

        mainMenu(addingItems, cinemaManager, myObj, menu, cart, userManager, false);

        boolean flag = true;
        while(flag){
            System.out.println("Czy chcesz przejść do płatności? (tak/nie)");
            String proceedToPayment = myObj.next();
            myObj.nextLine();
            if (proceedToPayment.equals("tak")) {
                PaymentProcessor.processPayment(myObj, cart);
                flag = false;
            } else if(proceedToPayment.equals("nie")){
                mainMenu(addingItems, cinemaManager, myObj, menu, cart, userManager, true);
            } else {
                System.out.println("Wpisano niepoprawne stwierdzenie");
            }
        }
    }

    static void mainMenu(boolean addingItems, CinemaManager cinemaManager, Scanner myObj,
                         Menu menu, Cart cart, UserManager userManager, boolean skip) {
        while (addingItems) {
            if (!skip) {
                cinemaManager.displayMovies();
                Movie selectedMovie = cinemaManager.selectMovie(myObj);
                Screening selectedScreening = cinemaManager.selectScreening(selectedMovie, myObj);
                cinemaManager.selectSeat(selectedScreening, myObj, cart);
            }
            boolean continueShopping = true;
            while (continueShopping) {
                menu.displayCart(cart);
                int menuChoice = menu.displayMenuAndGetChoice(myObj);
                switch (menuChoice) {
                    case 1:
                        menu.addAddonToCart(cart, myObj);
                        break;
                    case 2:
                        continueShopping = false;
                        addingItems = true;
                        skip = false;
                        break;
                    case 3:
                        continueShopping = false;
                        addingItems = false;
                        break;
                    case 4:
                        menu.cartMenu(cart, myObj);
                        break;
                    case 5:
                        authMenu(userManager, myObj);
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
                        break;
                }
            }
        }
    }

    static void authMenu(UserManager userManager, Scanner myObj) {
        boolean inAuthMenu = true;
        while (inAuthMenu) {
            System.out.println("1. Logowanie");
            System.out.println("2. Rejestracja");
            System.out.println("3. Powrót do głównego menu");
            System.out.print("Wybierz opcję: ");
            int choice = myObj.nextInt();
            myObj.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Podaj nazwę użytkownika: ");
                    String loginUsername = myObj.nextLine();
                    System.out.print("Podaj hasło: ");
                    String loginPassword = myObj.nextLine();
                    if (userManager.login(loginUsername, loginPassword)) {
                        System.out.println("Zalogowano pomyślnie.");
                        userMenu(userManager, myObj);
                        inAuthMenu = false;
                    } else {
                        System.out.println("Nieprawidłowa nazwa użytkownika lub hasło.");
                    }
                    break;
                case 2:
                    System.out.print("Podaj nazwę użytkownika: ");
                    String registerUsername = myObj.nextLine();
                    System.out.print("Podaj hasło: ");
                    String registerPassword = myObj.nextLine();
                    if (userManager.register(registerUsername, registerPassword)) {
                        System.out.println("Rejestracja zakończona sukcesem.");
                        userMenu(userManager, myObj);
                    } else {
                        System.out.println("Nazwa użytkownika jest już zajęta.");
                    }
                    break;
                case 3:
                    inAuthMenu = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
                    break;
            }
        }
    }

    static void userMenu(UserManager userManager, Scanner myObj) {
        boolean inUserMenu = true;
        while (inUserMenu) {
            System.out.println("1. Zobacz ustawienia użytkownika");
            System.out.println("2. Wprowadź metodę płatności");
            System.out.println("3. Powrót do menu głównego");
            System.out.print("Wybierz opcję: ");
            int choice = myObj.nextInt();
            myObj.nextLine();
            switch (choice) {
                case 1:
                    User user = userManager.getLoggedInUser();
                    if (user != null) {
                        System.out.println(user);
                    } else {
                        System.out.println("Brak zalogowanego użytkownika.");
                    }
                    break;
                case 2:
                    System.out.print("Podaj nową metodę płatności: ");
                    String paymentMethod = myObj.nextLine();
                    userManager.getLoggedInUser().setPaymentMethod(paymentMethod);
                    System.out.println("Metoda płatności zaktualizowana.");
                    break;
                case 3:
                    inUserMenu = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
                    break;
            }
        }
    }
}
