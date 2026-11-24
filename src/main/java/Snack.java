public class Snack {

    private String snackName;
    private double snackPrice;
    private int snackQuantity;

    public Snack(String name, double price, int quantity) {
        this.snackName = name;
        this.snackPrice = price;
        this.snackQuantity = quantity;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    public double getSnackPrice() {
        return snackPrice;
    }

    public void setSnackPrice(double snackPrice) {
        this.snackPrice = snackPrice;
    }

    public int getSnackQuantity() {
        return snackQuantity;
    }

    public void setSnackQuantity(int snackQuantity) {
        this.snackQuantity = snackQuantity;
    }

}
