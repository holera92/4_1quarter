import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.system.measureTimeMillis


fun main() {

    println("Введите размерность матрицы: ")
    val n = readLine()!!.toInt()

    require(n > 0) { "n should be > 0" }

    val A = Array(n) { IntArray(n) { Random.nextInt(1, 10) } }
    val B = Array(n) { IntArray(n) { Random.nextInt(1, 10) } }

    println("Доступно ${Runtime.getRuntime().availableProcessors()} потоков")
    println("Введите количество потоков которые следует задействовать в вычислении: ")

    val executors = Executors.newFixedThreadPool(
        readLine()!!.toInt()
    )

    val C = Array(n) { IntArray(n) }

    val calculationTime = measureTimeMillis {
        executors.runBlocking {
            for (row in A.indices) {
                execute {
                    for (column in B[0].indices) {
                        for (i in 0 until n) {
                            C[row][column] += A[row][i] * B[i][column]
                        }
                    }
                }
            }
        }
    }

    print("Вычисления заняли: ")
    print(calculationTime.toDouble() / 1000)
    println(" секунд")

}

inline fun ExecutorService.runBlocking(block: ExecutorService.() -> Unit) {
    apply(block)
    shutdown().also { awaitTermination(120, TimeUnit.SECONDS) }
}

fun Array<IntArray>.contentToString() = buildString {
    for (row in this@contentToString) {
        append(row.contentToString())
        append("\n")
    }
}

