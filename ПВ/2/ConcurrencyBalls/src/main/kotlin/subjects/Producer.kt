package subjects

import store.Store
import kotlin.math.min
import kotlin.random.Random

class Producer(
    private val firstStore: Store,
    private val secondStore: Store
) : Thread() {

    var internalCounter = 0

    override fun run() {
        while (firstStore.numberOfBalls > 0) {
            val consumingBalls = min(Random.nextInt(1, 100), firstStore.numberOfBalls)
            firstStore.consumeBalls(consumingBalls)
            internalCounter += consumingBalls
            secondStore.produceBalls(consumingBalls)
            sleep(1L)
        }

        println("Producer ${this.name} consume $internalCounter balls")
    }
}