

public abstract class SnackDispenserHandler {
    private SnackDispenserHandler next;
    public SnackDispenserHandler(SnackDispenserHandler next) {
        this.next = next;
    }
    public Snack dispenseSnack(String snackName, double paymentAmount) {
        if(next != null) {
            return next.dispenseSnack(snackName, paymentAmount);
        }
        System.out.println("Snack not found: "+snackName);
        return null;
    }
}
