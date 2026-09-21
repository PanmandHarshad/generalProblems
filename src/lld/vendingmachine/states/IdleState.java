package lld.vendingmachine.states;

import lld.vendingmachine.VendingMachine;
import lld.vendingmachine.enums.Denomination;
import lld.vendingmachine.enums.SlotCode;

public class IdleState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {
        machine.getCashManager().insertMoney(denomination);
        machine.setCurrentState(new MoneyInsertedState());
    }

    @Override
    public void selectProduct(VendingMachine machine, SlotCode slotCode) {
        throw new IllegalStateException("Cannot select the product with money");
    }

    @Override
    public void confirmPurchase(VendingMachine machine) {
        throw new IllegalStateException("Cannot confirm purchase in idle state");
    }

    @Override
    public void cancel(VendingMachine machine) {
        throw new IllegalStateException("Cannot cancel in idle state");
    }
}
