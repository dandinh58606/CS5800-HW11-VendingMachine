import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private Snack coke;
    private Snack pepsi;
    private Snack cheetos;
    private Snack doritos;
    private Snack kitKat;
    private Snack snickers;
    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        coke = new Snack("Coke", 1.50, 3);
        pepsi = new Snack("Pepsi", 1.50, 3);
        cheetos = new Snack("Cheetos", 2.00, 2);
        doritos = new Snack("Doritos", 2.00, 2);
        kitKat = new Snack("KitKat", 1.75, 2);
        snickers = new Snack("Snickers", 1.75, 1);

        List<Snack> snacks = Arrays.asList(coke, pepsi, cheetos, doritos, kitKat, snickers);

        SnackDispenserHandler chain = new CokeSnackDispenser(
                new PepsiSnackDispenser(
                        new CheetosSnackDispenser(
                                new DoritosSnackDispenser(
                                        new KitKatSnackDispenser(
                                                new SnickersSnackDispenser(null, snickers),
                                                kitKat),
                                        doritos),
                                cheetos),
                        pepsi),
                coke);

        vendingMachine = new VendingMachine(snacks, chain);
    }

    @Test
    void testSnackAvailabilityAndDecrement() {
        assertTrue(snickers.isAvailable());
        assertEquals(1, snickers.getSnackQuantity());

        snickers.decrementQuantity();
        assertFalse(snickers.isAvailable());
        assertEquals(0, snickers.getSnackQuantity());

        // Ensure quantity never drops below 0
        snickers.decrementQuantity();
        assertEquals(0, snickers.getSnackQuantity());
    }

    @Test
    void testStateTransitionsDuringValidPurchase() {
        assertInstanceOf(IdleState.class, vendingMachine.getState());

        vendingMachine.selectSnack("Coke");
        assertInstanceOf(InsertMoneyState.class, vendingMachine.getState());
        assertEquals("Coke", vendingMachine.getSnack());

        vendingMachine.insertMoney(2.00);
        assertInstanceOf(DispenseSnackState.class, vendingMachine.getState());
        assertEquals(2.00, vendingMachine.getMoney(), 0.001);

        vendingMachine.dispenseSnack();
        assertInstanceOf(IdleState.class, vendingMachine.getState());
        assertEquals(2, coke.getSnackQuantity());
        assertNull(vendingMachine.getSnack());
        assertEquals(0.0, vendingMachine.getMoney(), 0.001);
    }

    @Test
    void testChainOfResponsibilityDispensesLastSnackInChain() {
        // Snickers is at the very end of the chain
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(1.75);
        vendingMachine.dispenseSnack();

        assertEquals(0, snickers.getSnackQuantity());
        assertFalse(snickers.isAvailable());
        assertInstanceOf(IdleState.class, vendingMachine.getState());
    }

    @Test
    void testOutOfStockDoesNotDispenseAndResetsState() {
        // First purchase depletes Snickers (quantity 1 -> 0)
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(1.75);
        vendingMachine.dispenseSnack();
        assertEquals(0, snickers.getSnackQuantity());

        // Second purchase should fail and return machine to IdleState
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        assertEquals(0, snickers.getSnackQuantity());
        assertEquals(0.0, vendingMachine.getMoney(), 0.001);
        assertInstanceOf(IdleState.class, vendingMachine.getState());
    }

    @Test
    void testInsufficientFundsDoesNotDecrementStock() {
        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(1.00); // Cheetos costs $2.00
        vendingMachine.dispenseSnack();

        assertEquals(2, cheetos.getSnackQuantity());
        assertEquals(0.0, vendingMachine.getMoney(), 0.001);
        assertInstanceOf(IdleState.class, vendingMachine.getState());
    }

    @Test
    void testInvalidActionsInIdleAndWaitingStates() {
        // Inserting money or dispensing while Idle should keep machine in IdleState
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();
        assertInstanceOf(IdleState.class, vendingMachine.getState());
        assertEquals(0.0, vendingMachine.getMoney(), 0.001);

        // Selecting another snack or dispensing before paying keeps machine in WaitingForMoneyState
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.selectSnack("Coke");
        vendingMachine.dispenseSnack();
        assertInstanceOf(InsertMoneyState.class, vendingMachine.getState());
        assertEquals("Pepsi", vendingMachine.getSnack());
        assertEquals(3, pepsi.getSnackQuantity());
    }
}

