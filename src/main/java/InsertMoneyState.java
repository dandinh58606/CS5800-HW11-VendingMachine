public class InsertMoneyState implements StateOfVendingMachine{

    @Override
    public void selectSnack(VendingMachine vendingMachine, String snackName) {
        System.out.println("Snack already selected: " + snackName);
    }

    @Override
    public void insertMoney(VendingMachine vendingMachine, double amount) {
        if(amount <= 0) {
            System.out.println("Please insert a valid amount of money.");
            return;
        }
        vendingMachine.addMoney(amount);
        System.out.println("Inserted $" + amount + ". Total inserted: $" + vendingMachine.getMoney());
        System.out.println("Please select a snack to start first.");
        vendingMachine.setState(new DispenseSnackState());
    }

    @Override
    public void dispenseSnack(VendingMachine vendingMachine) {
        System.out.println("Please insert money first.");
    }
}
