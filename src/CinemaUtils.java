import java.util.Set;

public class CinemaUtils {
    public static void displaySeatingChart(Screening screening) {
        int seatsPerRow = 10;
        int rows = (int) Math.ceil((double) screening.room.capacity / seatsPerRow);
        Set<Integer> occupiedSeats = screening.occupiedSeats;

        System.out.println("Sala: " + screening.room.name + " Film: " + screening.movie.title + " Godzina: " + screening.time);
        System.out.println("Legenda: [X] - miejsce zajęte, [ ] - miejsce wolne\n");

        for (int row = 0; row < rows; row++) {
            // First, print the seats
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                int seatNumber = row * seatsPerRow + seat;
                if (seatNumber <= screening.room.capacity) {
                    if (occupiedSeats.contains(seatNumber)) {
                        System.out.print("[X] ");
                    } else {
                        System.out.print("[ ] ");
                    }
                }
            }
            System.out.println();
            // Then, print the seat numbers
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                int seatNumber = row * seatsPerRow + seat;
                if (seatNumber <= screening.room.capacity) {
                    // Use formatted string to center-align the seat numbers
                    System.out.print(String.format("%3d ", seatNumber));
                }
            }
            System.out.println();
        }
    }
}
