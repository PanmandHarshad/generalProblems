package lld.vendingmachine.model;

import lld.vendingmachine.enums.SlotCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inventory {
    private final Map<SlotCode, Slot> inventoryMap = new HashMap<>();

    public void addSlot(Slot slot) {
        Objects.requireNonNull(slot, "Slot cannot be null");
        Slot existing = inventoryMap.putIfAbsent(slot.getSlotCode(), slot);

        if (existing != null) {
            throw new IllegalStateException("Slot already exist: " + slot.getSlotCode());
        }
    }

    public Slot findSlot(SlotCode slotCode) {
        Slot slot = inventoryMap.get(slotCode);

        if (slot == null) {
            throw new IllegalArgumentException("Slot not found: " + slotCode);
        }

        return slot;
    }

    public boolean checkAvailability(SlotCode slotCode) {
        return findSlot(slotCode).isAvailable();
    }

    public void decreaseStock(SlotCode slotCode) {
        findSlot(slotCode).decreaseQuantity();
    }
}
