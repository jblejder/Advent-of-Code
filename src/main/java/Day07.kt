import kotlin.math.absoluteValue

class Day07 : Problem() {

    override fun partOne(): Any {
        val sorted = readInput().sorted()
        val median = if (sorted.size % 2 == 0)
            (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]) / 2
        else sorted.size / 2 + 1
        return sorted.sumOf { (it - median).absoluteValue }
    }

    override fun partTwo(): Any {
        val sorted: List<Int> = readInput().sorted()
        return (sorted.first()..sorted.last()).map { newPos ->
            sorted.sumOf { currentPos ->
                val dist = (newPos - currentPos).absoluteValue
                dist * (dist + 1) / 2
            }
        }.minOf { it }
    }

    private fun readInput(): List<Int> {
        return Unit::class.java.classLoader.getResource("day07.txt")!!
            .readText()
            .split(',')
            .map { it.toInt() }
    }
}

fun main() = solve<Day07>()
