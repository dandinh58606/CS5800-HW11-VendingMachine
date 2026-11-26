public class CheetosSnackDispenser extends SnackDispenserHandler{

    private Snack cheetos;

    public CheetosSnackDispenser(SnackDispenserHandler next, Snack snack) {
        super(next);
        this.cheetos = snack;
    }

    @Override
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(snackName.equals(cheetos.getSnackName())) {
            if(cheetos.getSnackQuantity() > 0) {
                if(paymentAmount >= cheetos.getSnackPrice()) {
                    cheetos.decrementQuantity();
                    return cheetos;
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
