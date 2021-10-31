package subjects

import store.Store

class Consumer(
    private val store: Store
) : Thread() {

    var internalCounter = 0

    override fun run() {
        while (store.numberOfBalls > 0) {
            store.consumeBalls(1)
            internalCounter++;
            sleep(1L)
        }
        println("Consumer consume $internalCounter balls")
    }

}