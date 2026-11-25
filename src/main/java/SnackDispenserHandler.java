

public abstract class SnackDispenserHandler {
    private SnackDispenserHandler next;
    public SnackDispenserHandler(SnackDispenserHandler next) {
        this.next = next;
    }
    public void dispenseSnack(String snackName, double paymentAmount) {
        if(next != null) {
            next.dispenseSnack(snackName, paymentAmount);
        }
    }
}
