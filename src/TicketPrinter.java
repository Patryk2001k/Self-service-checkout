public class TicketPrinter {
    public static void printTicket(Screening screening, int seatNumber, double amountPaid) {
        System.out.println("\n------ BILET KINOWY ------");
        System.out.println("Film: " + screening.movie.title);
        System.out.println("Sala: " + screening.room.name);
        System.out.println("Godzina: " + screening.time);
        System.out.println("Miejsce: " + seatNumber);
        System.out.println("Kwota zapłacona: " + amountPaid + " PLN");
        System.out.println("--------------------------\n");
    }
}
