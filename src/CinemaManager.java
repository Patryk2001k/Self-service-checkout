import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaManager {
    SoftwareSettings softSet = new SoftwareSettings();

    public void displayMovies() {
        System.out.println("Witaj przy kasie samoobsługowej");
        System.out.println("Wybierz film na który chcesz pójść:");
        for (int i = 0; i < softSet.movies.length; i++) {
            if (softSet.movies[i] != null) {
                System.out.println(i + ". " + softSet.movies[i].title + " - " + softSet.movies[i].description);
            } else {
                System.out.println(i + ". Brak filmu");
            }
        }
    }

    public Movie selectMovie(Scanner myObj) {
        int movieChoice;
        while (true) {
            System.out.print("Wybierz numer filmu: ");
            movieChoice = myObj.nextInt();
            myObj.nextLine();
            if (movieChoice >= 0 && movieChoice < softSet.movies.length && softSet.movies[movieChoice] != null) {
                break;
            }
            System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
        }
        Movie selectedMovie = softSet.movies[movieChoice];
        System.out.println("Wybrałeś: " + selectedMovie.title + " - " + selectedMovie.description);
        return selectedMovie;
    }

    public Screening selectScreening(Movie selectedMovie, Scanner myObj) {
        System.out.println("Dostępne seanse:");
        List<Screening> availableScreenings = new ArrayList<>();
        for (Screening screening : softSet.screenings) {
            if (screening.movie.equals(selectedMovie)) {
                availableScreenings.add(screening);
            }
        }
        for (int i = 0; i < availableScreenings.size(); i++) {
            System.out.println(i + ". " + availableScreenings.get(i));
        }

        int screeningChoice;
        while (true) {
            System.out.print("Wybierz numer seansu: ");
            screeningChoice = myObj.nextInt();
            myObj.nextLine();
            if (screeningChoice >= 0 && screeningChoice < availableScreenings.size()) {
                break;
            }
            System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
        }
        Screening selectedScreening = availableScreenings.get(screeningChoice);
        System.out.println("Wybrałeś seans: " + selectedScreening);
        return selectedScreening;
    }

    public void selectSeat(Screening selectedScreening, Scanner myObj, Cart cart) {
        CinemaUtils.displaySeatingChart(selectedScreening);

        System.out.print("Wybierz miejsce (1-" + selectedScreening.room.capacity + "): ");
        int seatChoice = myObj.nextInt();
        myObj.nextLine();
        while (seatChoice < 1 || seatChoice > selectedScreening.room.capacity || selectedScreening.occupiedSeats.contains(seatChoice)) {
            System.out.println("Nieprawidłowy wybór lub miejsce zajęte, spróbuj ponownie.");
            System.out.print("Wybierz miejsce (1-" + selectedScreening.room.capacity + "): ");
            seatChoice = myObj.nextInt();
            myObj.nextLine();
        }
        System.out.println("Wybrałeś miejsce numer " + seatChoice);
        double ticketPrice = 20.0;
        CartItem cartItem = new CartItem(selectedScreening, seatChoice, "film", ticketPrice, selectedScreening.movie.title);
        cart.addItem(cartItem);
    }
}
