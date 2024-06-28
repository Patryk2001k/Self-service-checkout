public class CartItem {
    Screening screening;
    int seatNumber;
    String type;  // movie or additional things like popcorn
    double price;
    String name;  // For example movie name

    public CartItem(Screening screening, int seatNumber, String type, double price, String name) {
        this.screening = screening;
        this.seatNumber = seatNumber;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public Screening getScreening() {
        return screening;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (type.equals("film")) {
            return "Film: " + name + ", Sala: " + screening.room.name + ", Godzina: " + screening.time + ", Miejsce: " + seatNumber + ", Cena: " + price + " PLN";
        } else {
            return "Dodatek: " + name + ", Cena: " + price + " PLN";
        }
    }
}
