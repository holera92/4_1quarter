public class Chopstick {

    Chopstick(int numberOfChopstick) {
        this.numberOfChopstick = numberOfChopstick;
    }

    final int numberOfChopstick;
    StateOfChopstick state = StateOfChopstick.FREE;


    synchronized StateOfChopstick getState() {
        return  state;
    }

    synchronized void setState(StateOfChopstick newState) {
        state = newState;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "numberOfChopstick=" + numberOfChopstick +
                '}';
    }
}
