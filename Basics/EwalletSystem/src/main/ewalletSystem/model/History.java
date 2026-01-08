package main.ewalletSystem.model;
import java.time.LocalDateTime;
public class History {

    private String action;
    private double amount;
    private String status;
    private LocalDateTime localDateTime;


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "History{" +
                " action='" + action + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

    public History( String action, double amount, String status) {
        this.action = action;
        this.amount = amount;
        this.status = status;
        this.localDateTime = LocalDateTime.now();
    }
}
