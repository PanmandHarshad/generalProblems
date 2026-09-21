package lld.vendingmachine.service;

import lld.vendingmachine.PurchaseSession;
import lld.vendingmachine.dispenser.Dispenser;
import lld.vendingmachine.enums.DispenseResult;
import lld.vendingmachine.enums.PurchaseStatus;
import lld.vendingmachine.enums.SlotCode;
import lld.vendingmachine.model.Inventory;
import lld.vendingmachine.model.Slot;

public class PurchaseService {

    private final Inventory inventory;
    private final CashManager cashManager;
    private final Dispenser dispenser;

    public PurchaseService(Inventory inventory, CashManager cashManager, Dispenser dispenser) {
        this.inventory = inventory;
        this.cashManager = cashManager;
        this.dispenser = dispenser;
    }

    public void confirmPurchase(PurchaseSession session) {
        // 1. Get selected slot.
        SlotCode selectedSlot = session.getSelectedSlot();

        // 2. Validate stock.
        if (!inventory.checkAvailability(selectedSlot)) {
            throw new RuntimeException("Product is not available in selected slot");
        }

        // 3. Validate inserted amount.
        if (cashManager.getInsertedTotal() < inventory.findSlot(selectedSlot).getProduct().getPrice()) {
            throw new RuntimeException("Insufficient amount");
        }

        // 4. Validate change availability.
        int insertedAmount = cashManager.getInsertedTotal();
        int price = inventory.findSlot(selectedSlot).getProduct().getPrice();
        int change = insertedAmount - price;

        if (!cashManager.canMakeChange(change)) {
            throw new IllegalStateException("Exact change unavailable");
        }

        // 5. Request product dispensing.
        if (dispenser.dispense(selectedSlot) != DispenseResult.SUCCESS) {
            session.setStatus(PurchaseStatus.RECOVERY_REQUIRED);
            throw new RuntimeException("Not able to dispense the product");
        }

        // 6. On confirmed success, decrease stock.
        inventory.decreaseStock(selectedSlot);

        // 7. Return change and settle payment.
        cashManager.returnChange(change);

        // 8. Mark session successful.
        cashManager.completePayment();
        session.setStatus(PurchaseStatus.SUCCESS);
    }

    public void validatePurchase(PurchaseSession session) {

        SlotCode selectedSlot = session.getSelectedSlot();

        if (selectedSlot == null) {
            throw new IllegalStateException("No product selected");
        }

        Slot slot = inventory.findSlot(selectedSlot);

        if (!slot.isAvailable()) {
            throw new IllegalStateException("Product is out of stock");
        }

        int price = slot.getProduct().getPrice();
        int insertedAmount = cashManager.getInsertedTotal();

        if (insertedAmount < price) {
            throw new IllegalStateException("Insufficient money");
        }

        int change = insertedAmount - price;

        if (!cashManager.canMakeChange(change)) {
            throw new IllegalStateException("Exact change unavailable");
        }
    }
}