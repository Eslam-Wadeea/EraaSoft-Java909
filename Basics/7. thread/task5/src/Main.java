//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Depositor depositor = new Depositor(account);
        Withdrawer withdrawer = new Withdrawer(account);

        depositor.start();
        withdrawer.start();
    }
}