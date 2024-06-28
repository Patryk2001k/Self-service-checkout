import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SoftwareSettings {
    Movie[] movies = new Movie[10];
    ScreeningRoom[] rooms = new ScreeningRoom[3];
    List<Screening> screenings = new ArrayList<>();

    public SoftwareSettings() {
        generateMovies();
        generateRooms();
        generateScreenings();
    }

    private void generateMovies() {
        for (int i = 0; i < 10; i++) {
            Movie.createMovie(movies, "Film" + i, "Tytuł" + i, "Opis" + i, 120, 5, i);
        }
        Movie.createMovie(movies, "Film" , "Tytuł" , "Opis" , 120, 5, 0);
    }

    private void generateRooms() {
        rooms[0] = new ScreeningRoom("Sala 1", 100);
        rooms[1] = new ScreeningRoom("Sala 2", 150);
        rooms[2] = new ScreeningRoom("Sala 3", 200);
    }

    private void generateScreenings() {
        LocalTime[] times = { LocalTime.of(12, 0), LocalTime.of(15, 0), LocalTime.of(18, 0) };
        for (Movie movie : movies) {
            for (ScreeningRoom room : rooms) {
                for (LocalTime time : times) {
                    Screening screening = new Screening(movie, room, time);
                    screening.generateOccupiedSeats((int) (Math.random() * room.capacity / 2)); // Generowanie losowych zajętych miejsc
                    screenings.add(screening);
                }
            }
        }
    }
}
