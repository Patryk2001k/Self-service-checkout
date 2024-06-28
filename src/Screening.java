import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Screening {
    Movie movie;
    ScreeningRoom room;
    LocalTime time;
    Set<Integer> occupiedSeats;

    public Screening(Movie movie, ScreeningRoom room, LocalTime time) {
        this.movie = movie;
        this.room = room;
        this.time = time;
        this.occupiedSeats = new HashSet<>();
    }

    public void generateOccupiedSeats(int numberOfOccupiedSeats) {
        while (occupiedSeats.size() < numberOfOccupiedSeats) {
            int seat = (int) (Math.random() * room.capacity) + 1;
            occupiedSeats.add(seat);
        }
    }

    public void occupySeat(int seatNumber) {
        occupiedSeats.add(seatNumber);
    }

    public void releaseSeat(int seatNumber) {
        occupiedSeats.remove(seatNumber);
    }

    @Override
    public String toString() {
        return "Sala: " + room.name + " godz: " + time + " Film: " + movie.title;
    }
}
