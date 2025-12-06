public class myClass implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" ->this is a message");
    }
}
