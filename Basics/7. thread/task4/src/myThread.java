public class myThread extends Thread {

    private final String name;
     public myThread(String name) {
         this.name = name;
     }

    @Override
    public void run() {
        System.out.println(name + " is running... ");
        System.out.println(name + " finished.");
    }


}
