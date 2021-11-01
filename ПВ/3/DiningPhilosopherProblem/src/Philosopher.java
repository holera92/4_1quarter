public class Philosopher extends Thread {

    final long THINK_TIME = 4;
    final long EAT_TIME = 2;

    final Chopstick leftChopstick;
    final Chopstick rightChopstick;
    long eatingCounter = 0;

    public Philosopher(Chopstick leftChopstick, Chopstick rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (leftChopstick.getState() != StateOfChopstick.BUSY && rightChopstick.getState() != StateOfChopstick.BUSY) {
                    doAction();
                }
            }
        } catch (InterruptedException e) {
            this.interrupt();
            e.printStackTrace();
        }
    }

    private void doAction() throws InterruptedException {
        synchronized (leftChopstick) {
            leftChopstick.setState(StateOfChopstick.BUSY);
//            System.out.println("Philosopher " + getName() + " take " + leftChopstick.numberOfChopstick + " chopstick");
            synchronized (rightChopstick) {
                rightChopstick.setState(StateOfChopstick.BUSY);
//                System.out.println("Philosopher " + getName()+ " take " + rightChopstick.numberOfChopstick + " chopstick");
                System.out.println("Philosopher " + getName() + " eating...");
                eatingCounter += EAT_TIME;
                sleep(EAT_TIME);
            }
            System.out.println("Philosopher " + getName() + " thinking...");
//            System.out.println("Philosopher " + getName() + " leave " + rightChopstick.numberOfChopstick + " chopstick");
            rightChopstick.setState(StateOfChopstick.FREE);
        }
//        System.out.println("Philosopher " + getName() + " leave " + leftChopstick.numberOfChopstick + " chopstick");
        leftChopstick.setState(StateOfChopstick.FREE);
        sleep(THINK_TIME);
    }
}
