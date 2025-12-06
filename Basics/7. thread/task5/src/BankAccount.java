public class BankAccount {
    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        notify();
    }

    public synchronized void withdraw(int amount) {
        while (balance < amount) {
            System.out.println("Not enough balance. Waiting to withdraw " + amount);
            try {
                wait();
            } catch (InterruptedException e) {
                throw  new RuntimeException(e);
            }
        }

        balance -= amount;
        System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
    }
}

