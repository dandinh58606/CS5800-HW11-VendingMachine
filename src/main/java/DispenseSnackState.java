public class DispenseSnackState implements  StateOfVendingMachine{

    @Override
    public void selectSnack(VendingMachine vendingMachine, String snackName) {
        System.out.println("Currently dispensing snack, please wait.");
    }

    @Override
    public void insertMoney(VendingMachine vendingMachine, double amount) {
        System.out.println("Currently dispensing snack, please wait before inserting more money.");
    }

    @Override
    public void dispenseSnack(VendingMachine vendingMachine) {
        String selectedSnack = vendingMachine.getSnack();
        double insertedMoney = vendingMachine.getMoney();
        Snack snackDispensed = null;

        if(vendingMachine.getDispenserChain() != null) {
            snackDispensed = vendingMachine.getDispenserChain().dispenseSnack(selectedSnack, insertedMoney);
        }

        if(snackDispensed != null) {
            double change = insertedMoney - snackDispensed.getSnackPrice();
            System.out.println("Dispensing " + snackDispensed.getSnackName() + ". Change returned: $" + String.format("%.2f", change));
        }
        else {
            System.out.println("Refunding $" + String.format("%.2f", insertedMoney));
        }

        vendingMachine.resetTransaction();
        vendingMachine.setState(new IdleState());
    }

}
