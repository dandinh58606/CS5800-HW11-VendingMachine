public class KitKatSnackDispenser extends SnackDispenserHandler{

    private Snack kitkat;

    public KitKatSnackDispenser(SnackDispenserHandler next, Snack snack) {
        super(next);
        this.kitkat = snack;
    }

    @Override
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(snackName.equals(kitkat.getSnackName())) {
            if(kitkat.getSnackQuantity() > 0) {
                if(paymentAmount >= kitkat.getSnackPrice()) {
                    kitkat.decrementQuantity();
                    return kitkat;
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
