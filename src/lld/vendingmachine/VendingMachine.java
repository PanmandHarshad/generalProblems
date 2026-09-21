package lld.vendingmachine;

import lld.vendingmachine.enums.Denomination;
import lld.vendingmachine.enums.PurchaseStatus;
import lld.vendingmachine.enums.SlotCode;
import lld.vendingmachine.model.Inventory;
import lld.vendingmachine.service.CashManager;
import lld.vendingmachine.service.PurchaseService;
import lld.vendingmachine.states.IdleState;
import lld.vendingmachine.states.VendingMachineState;

public class VendingMachine {

    private final Inventory inventory;
    private final CashManager cashManager;
    private final PurchaseService purchaseService;

    private PurchaseSession purchaseSession;
    private VendingMachineState currentState;

    // Constructor + dependency initialization
    public VendingMachine(Inventory inventory, CashManager cashManager, PurchaseService purchaseService,
                          PurchaseSession purchaseSession, VendingMachineState currentState) {
        this.inventory = inventory;
        this.cashManager = cashManager;
        this.purchaseService = purchaseService;
        this.purchaseSession = purchaseSession;
        this.currentState = currentState;
    }

    public void insertMoney(Denomination denomination) {
        currentState.insertMoney(this, denomination);
    }

    public void selectProduct(SlotCode slotCode) {
        currentState.selectProduct(this, slotCode);
    }

    public void confirmPurchase() {
        currentState.confirmPurchase(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public void setCurrentState(VendingMachineState state) {
        this.currentState = state;
    }

    // Getters for dependencies and session.

    public Inventory getInventory() {
        return inventory;
    }

    public CashManager getCashManager() {
        return cashManager;
    }

    public PurchaseService getPurchaseService() {
        return purchaseService;
    }

    public PurchaseSession getPurchaseSession() {
        return purchaseSession;
    }

    public void resetSession() {
        purchaseSession = new PurchaseSession();
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    // Cancellation and session-reset methods.
    public void cancelPurchase() {
        cashManager.refundInsertedMoney();
        purchaseSession.setStatus(PurchaseStatus.CANCELLED);
        resetSession();
        setCurrentState(new IdleState());
    }

}
