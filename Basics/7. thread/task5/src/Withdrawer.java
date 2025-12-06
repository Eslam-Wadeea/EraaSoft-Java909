public class Withdrawer extends Thread{
    private final BankAccount account;

    public Withdrawer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        int[] withdrawals = {50, 120, 300, 200};

        for (int amount : withdrawals) {
            account.withdraw(amount);
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}
