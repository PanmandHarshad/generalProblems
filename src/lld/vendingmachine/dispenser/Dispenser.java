package lld.vendingmachine.dispenser;

import lld.vendingmachine.enums.DispenseResult;
import lld.vendingmachine.enums.SlotCode;

public interface Dispenser {
    DispenseResult dispense(SlotCode slotCode);
}