import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private StateOfVendingMachine state;
    private SnackDispenserHandler dispenserChain;
    private List<Snack> snacks;
    private String selectedSnack;
    private double insertedMoney;

    public VendingMachine(List<Snack> snacks, SnackDispenserHandler dispenserChain) {
        this.state = new IdleState();
        if (snacks != null) {
            this.snacks = snacks;
        } else {
            this.snacks = new ArrayList<>();
        }
        this.dispenserChain = dispenserChain;
        this.selectedSnack = null;
        this.insertedMoney = 0.0;
    }

    public void selectSnack(String snackName) {
        state.selectSnack(this, snackName);
    }

    public void insertMoney(double amount) {
        state.insertMoney(this, amount);
    }

    public void dispenseSnack() {
        state.dispenseSnack(this);
    }

    // State getters and setters
    public StateOfVendingMachine getState() {
        return state;
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    // Transaction helpers used by the state classes
    public String getSnack() {
        return selectedSnack;
    }

    public void setSnack(String selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public double getMoney() {
        return insertedMoney;
    }

    public void addMoney(double amount) {
        this.insertedMoney += amount;
    }

    public void resetTransaction() {
        this.selectedSnack = null;
        this.insertedMoney = 0.0;
    }

    // Chain and inventory accessors
    public SnackDispenserHandler getDispenserChain() {
        return dispenserChain;
    }

    public void setDispenserChain(SnackDispenserHandler dispenserChain) {
        this.dispenserChain = dispenserChain;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
}