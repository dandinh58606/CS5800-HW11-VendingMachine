import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        // 1. Create the 6 snacks (Snickers starts with quantity 2 so we can test hitting 0)
        Snack coke = new Snack("Coke", 1.50, 3);
        Snack pepsi = new Snack("Pepsi", 1.50, 3);
        Snack cheetos = new Snack("Cheetos", 2.00, 2);
        Snack doritos = new Snack("Doritos", 2.00, 2);
        Snack kitKat = new Snack("KitKat", 1.75, 2);
        Snack snickers = new Snack("Snickers", 1.75, 2);

        List<Snack> snacks = Arrays.asList(coke, pepsi, cheetos, doritos, kitKat, snickers);

        // 2. Build the Chain of Responsibility in exact order:
        // Coke -> Pepsi -> Cheetos -> Doritos -> KitKat -> Snickers -> null
        SnackDispenserHandler chain = new CokeSnackDispenser(new PepsiSnackDispenser(new CheetosSnackDispenser(new DoritosSnackDispenser(new KitKatSnackDispenser(new SnickersSnackDispenser(null, snickers), kitKat), doritos), cheetos), pepsi), coke);

        // 3. Initialize the VendingMachine
        VendingMachine vendingMachine = new VendingMachine(snacks, chain);

        System.out.println("=== CASE 1: Normal Purchase with Exact Change (Coke) ===");
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.50);
        vendingMachine.dispenseSnack();

        System.out.println("\n=== CASE 2: Normal Purchase with Change Returned (Doritos) ===");
        vendingMachine.selectSnack("Doritos");
        vendingMachine.insertMoney(3.00);
        vendingMachine.dispenseSnack();

        System.out.println("\n=== CASE 3: Insufficient Funds (Cheetos) ===");
        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();

        System.out.println("\n=== CASE 4: Invalid State Actions (Inserting money while Idle) ===");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        System.out.println("\n=== CASE 5: Snickers Quantity Reaches 0 & Out-of-Stock Check ===");
        // Purchase 1 of 2
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        // Purchase 2 of 2 (quantity hits 0)
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(1.75);
        vendingMachine.dispenseSnack();
        System.out.println("Snickers status after 2 purchases -> " + snickers);

        // Attempt 3 (out of stock!)
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();
    }
}