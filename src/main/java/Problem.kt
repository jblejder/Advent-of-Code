import kotlin.system.measureTimeMillis

inline fun <reified T : Problem> solve() {
    T::class.java.getDeclaredConstructor().newInstance().run()
}

abstract class Problem {

    fun run() {
        println("****")
        runInternal("Part one") { partOne() }
        println()
        println("****")
        runInternal("Part two") { partTwo() }
    }

    private fun runInternal(title: String, block: () -> Any) {
        val result: Any
        val time = measureTimeMillis { result = block() }
        println("$title runs $time ms. Result:")
        println(result)
    }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}