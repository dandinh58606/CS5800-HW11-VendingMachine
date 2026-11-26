public class PepsiSnackDispenser extends SnackDispenserHandler{

    private Snack pepsi;

    public PepsiSnackDispenser(SnackDispenserHandler next, Snack snack) {
        super(next);
        this.pepsi = snack;
    }

    @Override
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(snackName.equals(pepsi.getSnackName())) {
            if(pepsi.getSnackQuantity() > 0) {
                if(paymentAmount >= pepsi.getSnackPrice()) {
                    pepsi.decrementQuantity();
                    return pepsi;
                }
                else {
                    System.out.println("Insufficient Funds, please add in more money for this item");
                    return null;
                }
            }
            else {
                System.out.println("No more of this item is available. Please try another item.");
                return null;
            }
        }
        else {
            return super.dispenseSnack(snackName, paymentAmount);
        }
    }
}
