public class Movie {
    String name;
    String description;
    String title;
    int duration;
    int rating;

    static Movie[] createMovie(Movie[] moviesArray, String name, String title, String description,
                               int duration, int rating, int position) {
        Movie myMovie = new Movie();
        myMovie.name = name;
        myMovie.description = description;
        myMovie.title = title;
        myMovie.duration = duration;
        myMovie.rating = rating;
        moviesArray[position] = myMovie;
        return moviesArray;
    }
}
