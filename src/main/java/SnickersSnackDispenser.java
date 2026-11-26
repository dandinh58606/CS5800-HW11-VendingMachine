public class SnickersSnackDispenser extends SnackDispenserHandler{

    private Snack snickers;

    public SnickersSnackDispenser(SnackDispenserHandler next, Snack snack) {
        super(next);
        this.snickers = snack;
    }

    @Override
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(snackName.equals(snickers.getSnackName())) {
            if(snickers.getSnackQuantity() > 0) {
                if(paymentAmount >= snickers.getSnackPrice()) {
                    snickers.decrementQuantity();
                    return snickers;
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
