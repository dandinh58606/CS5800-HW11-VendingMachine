public class CokeSnackDispenser extends SnackDispenserHandler{

    private Snack coke;

    public CokeSnackDispenser(SnackDispenserHandler next, Snack snack) {
        super(next);
        this.coke = snack;
    }

    @Override
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(snackName.equals(coke.getSnackName())) {
            if(coke.getSnackQuantity() > 0) {
                if(paymentAmount >= coke.getSnackPrice()) {
                    coke.decrementQuantity();
                    return coke;
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

