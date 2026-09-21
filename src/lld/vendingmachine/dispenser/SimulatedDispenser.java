package lld.vendingmachine.dispenser;

import lld.vendingmachine.enums.DispenseResult;
import lld.vendingmachine.enums.SlotCode;

public class SimulatedDispenser implements Dispenser {

    @Override
    public DispenseResult dispense(SlotCode slotCode) {
        System.out.println("Dispensing product from " + slotCode);

        // Assume successful physical delivery for the happy path.
        return DispenseResult.SUCCESS;
    }
}
