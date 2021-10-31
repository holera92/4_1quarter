package store

interface Store {

    var numberOfBalls: Int

    fun consumeBalls(consumingBalls: Int)

    fun produceBalls(producingBalls: Int)
}