package lld.vendingmachine.states;

import lld.vendingmachine.VendingMachine;
import lld.vendingmachine.enums.Denomination;
import lld.vendingmachine.enums.SlotCode;

public class DispensingState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine machine, Denomination denomination) {
        throw new IllegalStateException("Operation not allowed in dispensing state");
    }

    @Override
    public void selectProduct(VendingMachine machine, SlotCode slotCode) {
        throw new IllegalStateException("Operation not allowed in dispensing state");
    }

    @Override
    public void confirmPurchase(VendingMachine machine) {
        throw new IllegalStateException("Operation not allowed in dispensing state");
    }

    @Override
    public void cancel(VendingMachine machine) {
        throw new IllegalStateException("Operation not allowed in dispensing state");
    }
}
