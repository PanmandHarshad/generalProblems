package lld.vendingmachine.states;

import lld.vendingmachine.PurchaseSession;
import lld.vendingmachine.VendingMachine;
import lld.vendingmachine.enums.Denomination;
import lld.vendingmachine.enums.PurchaseStatus;
import lld.vendingmachine.enums.SlotCode;
import lld.vendingmachine.service.PurchaseService;

public class ProductSelectedState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {
        machine.getCashManager().insertMoney(denomination);
    }

    @Override
    public void selectProduct(VendingMachine machine, SlotCode slotCode) {
        machine.getInventory().findSlot(slotCode);
        machine.getPurchaseSession().setSelectedSlot(slotCode);
    }

    @Override
    public void confirmPurchase(VendingMachine machine) {

        PurchaseSession session = machine.getPurchaseSession();
        PurchaseService purchaseService = machine.getPurchaseService();

        // Validation errors leave the machine in ProductSelectedState.
        purchaseService.validatePurchase(session);

        // Only enter DispensingState after successful validation.
        machine.setCurrentState(new DispensingState());

        try {
            purchaseService.confirmPurchase(session);

            // Preserve the completed session if transaction history is needed.
            machine.resetSession();
            machine.setCurrentState(new IdleState());

        } catch (RuntimeException ex) {
            // A failure after dispensing starts requires investigation.
            session.setStatus(PurchaseStatus.RECOVERY_REQUIRED);
            machine.setCurrentState(new OutOfServiceState());

            throw ex;
        }
    }

    @Override
    public void cancel(VendingMachine machine) {
        machine.cancelPurchase();
    }
}
