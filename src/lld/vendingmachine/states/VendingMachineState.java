package lld.vendingmachine.states;

import lld.vendingmachine.VendingMachine;
import lld.vendingmachine.enums.Denomination;
import lld.vendingmachine.enums.SlotCode;

public interface VendingMachineState {
    void insertMoney(VendingMachine machine, Denomination denomination);

    void selectProduct(VendingMachine machine, SlotCode slotCode);

    void confirmPurchase(VendingMachine machine);

    void cancel(VendingMachine machine);
}
