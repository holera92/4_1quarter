package store;


import kotlin.jvm.Volatile;

public class SynchronizedStore implements Store {

    @Volatile
    int numberOfBalls = 10_000;

    @Override
    public synchronized int getNumberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public synchronized void consumeBalls(int consumingBalls) {
        if (consumingBalls <= numberOfBalls) {
            numberOfBalls -= consumingBalls;
        } else {
            throw new IllegalArgumentException(consumingBalls + ">" + numberOfBalls);
        }
    }

    @Override
    public synchronized void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    @Override
    public synchronized void produceBalls(int producingBalls) {
        numberOfBalls += producingBalls;
    }
}