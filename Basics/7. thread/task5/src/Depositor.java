public class Depositor extends Thread{
    private final BankAccount account;

    public Depositor(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        int[] deposits = {100, 200, 150, 300};

        for (int amount : deposits) {
            account.deposit(amount);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}
