package main.ewalletSystem.helper;

public class AccountResult {
    private int error;
    private double amount;

    public AccountResult() {
    }

    public AccountResult(int error, double amount) {
        this.error = error;
        this.amount = amount;
    }

    public AccountResult(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
