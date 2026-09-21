package lld.vendingmachine.service;

import lld.vendingmachine.enums.Denomination;

import java.util.HashMap;
import java.util.Map;

public class CashManager {

    private final Map<Denomination, Integer> cashReserve;
    private final Map<Denomination, Integer> insertedCash;

    CashManager(Map<Denomination, Integer> cashReserve) {
        this.cashReserve = new HashMap<>(cashReserve);
        this.insertedCash = new HashMap<>();
    }

    public void insertMoney(Denomination denomination) {
        if (denomination != null) {
            insertedCash.put(denomination, insertedCash.getOrDefault(denomination, 0) + 1);
        }
    }

    public int getInsertedTotal() {
        return insertedCash.entrySet().stream().mapToInt(entry -> entry.getKey().getValue() * entry.getValue()).sum();
    }

    public boolean canMakeChange(int amount) {
        validateAmount(amount);

        if (amount == 0) {
            return true;
        }

        return calculateChange(amount) != null;
    }

    public void returnChange(int amount) {
        validateAmount(amount);

        if (amount == 0) {
            return;
        }

        Map<Denomination, Integer> changePlan = calculateChange(amount);

        if (changePlan == null) {
            throw new IllegalArgumentException("Cannot make change");
        }

        // Apply the plan only after we know the complete solution exists.
        for (Map.Entry<Denomination, Integer> entry : changePlan.entrySet()) {
            Denomination denomination = entry.getKey();
            int count = entry.getValue();

            int available = cashReserve.getOrDefault(denomination, 0);
            cashReserve.put(denomination, available - count);
        }
    }

    public void refundInsertedMoney() {
        insertedCash.clear();
    }

    public void completePayment() {
        for (Map.Entry<Denomination, Integer> entry : insertedCash.entrySet()) {
            cashReserve.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        insertedCash.clear();
    }

    private Map<Denomination, Integer> calculateChange(int amount) {
        Map<Denomination, Integer> result = new HashMap<>();

        if (findChange(amount, Denomination.values().length - 1, result)) {
            return result;
        }

        return null;
    }

    private boolean findChange(int remaining, int index, Map<Denomination, Integer> result) {

        if (remaining == 0) {
            return true;
        }

        if (remaining < 0 || index < 0) {
            return false;
        }

        Denomination denomination = Denomination.values()[index];
        int available = cashReserve.getOrDefault(denomination, 0);

        int maxUsable = Math.min(available, remaining / denomination.getValue());

        // Try larger number of current denomination first.
        for (int count = maxUsable; count >= 0; count--) {

            if (count > 0) {
                result.put(denomination, count);
            }

            int newRemaining = remaining - count * denomination.getValue();

            if (findChange(newRemaining, index - 1, result)) {
                return true;
            }

            result.remove(denomination);
        }

        return false;
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount cannot be negative"
            );
        }
    }
}
