package lld.vendingmachine;

import lld.vendingmachine.dispenser.SimulatedDispenser;
import lld.vendingmachine.enums.*;
import lld.vendingmachine.model.*;
import lld.vendingmachine.service.*;
import lld.vendingmachine.states.IdleState;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // 1. Create product
        Product coke = new Product(
                "P1",
                "Coke",
                ProductType.BEVERAGE,
                30
        );

        // 2. Create inventory
        Slot slot = new Slot(
                SlotCode.A1,
                coke,
                10,
                5
        );

        Inventory inventory = new Inventory();
        inventory.addSlot(slot);

        // 3. Initialize cash reserve
        CashManager cashManager = new CashManager(
                Map.of(
                        Denomination.TEN, 5,
                        Denomination.TWENTY, 5,
                        Denomination.FIFTY, 2,
                        Denomination.HUNDRED, 1
                )
        );

        // 4. Create purchase service
        PurchaseService purchaseService = new PurchaseService(
                inventory,
                cashManager,
                new SimulatedDispenser()
        );

        // 5. Create vending machine
        VendingMachine machine = new VendingMachine(
                inventory,
                cashManager,
                purchaseService,
                new PurchaseSession(),
                new IdleState()
        );

        System.out.println("===== BEFORE PURCHASE =====");

        System.out.println("Stock: " + slot.getQuantity());
        System.out.println("State: " +
                machine.getCurrentState().getClass().getSimpleName());

        // 6. Simulate customer actions
        machine.insertMoney(Denomination.FIFTY);

        machine.selectProduct(SlotCode.A1);

        // Keep reference because machine resets session after success.
        PurchaseSession session = machine.getPurchaseSession();

        machine.confirmPurchase();

        // 7. Verify results
        System.out.println("\n===== AFTER PURCHASE =====");

        System.out.println("Stock: " + slot.getQuantity());

        System.out.println("Purchase status: " +
                session.getStatus());

        System.out.println("Machine state: " +
                machine.getCurrentState().getClass().getSimpleName());

        System.out.println("Inserted cash: " +
                cashManager.getInsertedTotal());

        // 8. Assertions
        if (slot.getQuantity() != 4) {
            throw new AssertionError("Inventory update failed");
        }

        if (session.getStatus() != PurchaseStatus.SUCCESS) {
            throw new AssertionError("Purchase failed");
        }

        if (!(machine.getCurrentState() instanceof IdleState)) {
            throw new AssertionError("Machine did not return to Idle");
        }

        if (cashManager.getInsertedTotal() != 0) {
            throw new AssertionError("Inserted cash was not cleared");
        }

        System.out.println("\nHAPPY PATH TEST PASSED");
    }
}