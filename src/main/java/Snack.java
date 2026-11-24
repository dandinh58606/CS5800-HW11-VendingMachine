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

    public boolean isAvailable() {
        return (snackQuantity > 0);
    }

    public void decrementQuantity() {
        if(this.isAvailable()) {
            snackQuantity -= 1;
        }
    }

    @Override
    public String toString() {
        String formatted_snackprice = String.format("%.2f", snackPrice);
        return ("Name: "+snackName+", Price: $"+formatted_snackprice+", Stock Remaining: "+snackQuantity);
    }
}
