import org.junit.Test
import store.NotSynchronizedStore
import store.SynchronizedStore
import subjects.Consumer
import subjects.Producer
import kotlin.test.assertEquals

class StoreTest {

    @Test
    fun notSynchronizedStore() {
        val fStore = NotSynchronizedStore()
        val sStore = NotSynchronizedStore().apply { numberOfBalls = 0 }
        val producerList = List(2) { Producer(fStore, sStore) }
        val consumerList = List(4) { Consumer(sStore) }

        producerList.forEach {
            it.start()
        }

        Thread.sleep(5_000)
        println("Number of balls: " + sStore.numberOfBalls)

        consumerList.forEach {
            it.start()
        }

        Thread.sleep(5_000)

        assertEquals(10_000, consumerList.sumOf { it.internalCounter })
    }

    @Test
    fun synchronizedStore() {
        val fStore = SynchronizedStore()
        val sStore = SynchronizedStore().apply { numberOfBalls = 0 }
        val producerList = List(2) { Producer(fStore, sStore) }
        val consumerList = List(4) { Consumer(sStore) }

        producerList.forEach {
            it.start()
        }

        Thread.sleep(5_000)
        println("Number of balls: " + sStore.numberOfBalls)

        consumerList.forEach {
            it.start()
        }

        Thread.sleep(5_000)

        assertEquals(10_000, consumerList.sumOf { it.internalCounter })
    }
}