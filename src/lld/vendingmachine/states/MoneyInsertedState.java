package lld.vendingmachine.states;

import lld.vendingmachine.VendingMachine;
import lld.vendingmachine.enums.Denomination;
import lld.vendingmachine.enums.SlotCode;

public class MoneyInsertedState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {
        machine.getCashManager().insertMoney(denomination);
    }

    @Override
    public void selectProduct(VendingMachine machine, SlotCode slotCode) {
        machine.getInventory().findSlot(slotCode);
        machine.getPurchaseSession().setSelectedSlot(slotCode);
        machine.setCurrentState(new ProductSelectedState());
    }

    @Override
    public void confirmPurchase(VendingMachine machine) {
        throw new IllegalStateException("Cannot confirm purchase in idle state");
    }

    @Override
    public void cancel(VendingMachine machine) {
        machine.cancelPurchase();
    }
}
