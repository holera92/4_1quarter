package store

class NotSynchronizedStore(
    override var numberOfBalls: Int = 10_000
) : Store {

    override fun consumeBalls(consumingBalls: Int) {

        if (consumingBalls <= numberOfBalls) {
            numberOfBalls -= consumingBalls
        }

    }

    override fun produceBalls(producingBalls: Int) {
        numberOfBalls += producingBalls
    }

}