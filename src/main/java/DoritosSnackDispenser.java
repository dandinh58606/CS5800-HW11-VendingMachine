public class DoritosSnackDispenser extends SnackDispenserHandler{

    private Snack doritos;

    public DoritosSnackDispenser(SnackDispenserHandler next, Snack snack) {
        super(next);
        this.doritos = snack;
    }

    @Override
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(snackName.equals(doritos.getSnackName())) {
            if(doritos.getSnackQuantity() > 0) {
                if(paymentAmount >= doritos.getSnackPrice()) {
                    doritos.decrementQuantity();
                    return doritos;
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
