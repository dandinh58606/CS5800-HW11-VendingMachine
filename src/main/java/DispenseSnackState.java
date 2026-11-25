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

        if(vendingMachine.getDispenserChain() != null) {
            vendingMachine.getDispenserChain().dispenseSnack(selectedSnack, insertedMoney);
        }

        vendingMachine.resetTransaction();
        vendingMachine.setState(new IdleState());
    }

}
