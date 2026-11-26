public class IdleState implements StateOfVendingMachine{

    @Override
    public void selectSnack(VendingMachine vendingMachine, String snackName) {
        System.out.println("Selected snack: " + snackName);
        vendingMachine.setSnack(snackName);
        vendingMachine.setState(new InsertMoneyState());
    }

    @Override
    public void insertMoney(VendingMachine vendingMachine, double amount) {
        System.out.println("Please select a snack to start first.");
    }

    @Override
    public void dispenseSnack(VendingMachine vendingMachine) {
        System.out.println("Please select a snack and insert money first.");
    }
}
