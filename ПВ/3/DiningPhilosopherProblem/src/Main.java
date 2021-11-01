public class Main {

    static final int NUMBER_OF_PHILOSOPHERS = 10;

    static Chopstick[] chopSticks = new Chopstick[NUMBER_OF_PHILOSOPHERS];
    static Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];


    static double T = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < chopSticks.length; i++) {
            chopSticks[i] = new Chopstick(i);
        }

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(chopSticks[i % chopSticks.length], chopSticks[(i + 1) % chopSticks.length]);
            philosophers[i].start();
        }

        Thread.sleep(5000);

        for (var philosopher : philosophers) {
            philosopher.interrupt();
            T += philosopher.eatingCounter;
        }

        for (int i = 0; i < philosophers.length; i++) {
            System.out.println("Philosopher " + i + " " + philosophers[i].eatingCounter / T);
        }
    }

}