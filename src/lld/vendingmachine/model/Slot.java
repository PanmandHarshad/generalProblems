package lld.vendingmachine.model;

import lld.vendingmachine.enums.SlotCode;

public class Slot {
    private final SlotCode slotCode;
    private final Product product;
    private final int capacity;
    private int quantity;

    public Slot(SlotCode slotCode, Product product, int capacity, int quantity) {
        if (slotCode == null || product == null) {
            throw new IllegalArgumentException("Slot code and product are required");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        if (quantity < 0 || quantity > capacity) {
            throw new IllegalArgumentException("Invalid initial quantity");
        }

        this.slotCode = slotCode;
        this.product = product;
        this.capacity = capacity;
        this.quantity = quantity;
    }

    public SlotCode getSlotCode() {
        return slotCode;
    }

    public Product getProduct() {
        return product;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void decreaseQuantity() {
        if (quantity == 0) {
            throw new IllegalStateException("Slot is out of stock");
        }
        quantity--;
    }

    public void refill(int newQuantity) {
        if (newQuantity <= 0 || newQuantity > capacity - quantity) {
            throw new IllegalArgumentException("Invalid refill amount");
        }
        quantity += newQuantity;
    }
}