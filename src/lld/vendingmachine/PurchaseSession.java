package lld.vendingmachine;

import lld.vendingmachine.enums.PurchaseStatus;
import lld.vendingmachine.enums.SlotCode;

public class PurchaseSession {
    private PurchaseStatus status = PurchaseStatus.PENDING;
    private SlotCode selectedSlot;

    public SlotCode getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(SlotCode selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public int getTotalAmount() {
        return 0;
    }
}
