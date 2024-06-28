public class User {
    private String username;
    private String password;
    private String paymentMethod;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.paymentMethod = "Brak";
    }

    public String getUsername() {
        return username;
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return "Nazwa użytkownika: " + username + ", Metoda płatności: " + paymentMethod;
    }
}
