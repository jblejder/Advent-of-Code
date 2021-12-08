import kotlin.math.pow

class Day08 : Problem() {

    override fun partOne(): Any {
        val unique = listOf(2, 4, 3, 7)
        return readInput().sumOf { screen: Reading ->
            screen.digits.count { unique.contains(it.length) }
        }
    }

    override fun partTwo(): Any {
        val input = readInput()
        return input.sumOf {
            val decriptor = Descriptor(it)
            it.digits.mapIndexed { i, value ->
                decriptor.valueOf(value) * 10.0.pow(3 - i)
            }.sum()
        }
    }

    private class Descriptor(screen: Reading) {

        private val map = arrayOfNulls<String>(10)

        init {
            findNumbers(screen.combinations)
        }

        private fun findNumbers(input: List<String>) {
            map[1] = input.first { it.length == 2 }
            map[4] = input.first { it.length == 4 }
            map[7] = input.first { it.length == 3 }
            map[8] = input.first { it.length == 7 }

            input.forEach {
                when {
                    map[2] == null && isTwo(it) -> map[2] = it
                    map[3] == null && isThree(it) -> map[3] = it
                    map[5] == null && isFive(it) -> map[5] = it

                    map[0] == null && isZero(it) -> map[0] = it
                    map[6] == null && isSix(it) -> map[6] = it
                    map[9] == null && isNine(it) -> map[9] = it
                }
            }
        }

        private fun isThree(it: String): Boolean {
            return it.length == 5 && it.toSet().plus(map[7]!!.toSet()).size == 5
        }

        private fun isTwo(it: String): Boolean {
            return it.length == 5 && it.toSet().minus(map[4]!!.toSet()).size == 3
        }

        private fun isFive(it: String): Boolean {
            return it.length == 5 && it.toSet().minus(map[4]!!.toSet()).minus(map[7]!!.toSet()).size == 1
        }

        private fun isNine(it: String): Boolean {
            return it.length == 6 && it.toSet().minus(map[4]!!.toSet()).size == 2
        }

        private fun isSix(it: String): Boolean {
            return it.length == 6 && it.toSet().minus(map[1]!!.toSet()).size == 5
        }

        private fun isZero(it: String): Boolean {
            return it.length == 6 && it.toSet().plus(map[1]!!.toSet()).let {
                it.size == 6 && it.minus(map[4]!!.toSet()).size == 3
            }
        }

        fun valueOf(value: String): Int {
            return map.indexOfFirst { it!!.toSet() == value.toSet() }
        }
    }

    private fun readInput(): List<Reading> {
        return Unit::class.java.classLoader.getResource("day08.txt")!!
            .readText()
            .lines()
            .map {
                val split = it.split(" | ")
                Reading(split[0].split(" "), split[1].split(" "))
            }
    }

    data class Reading(val combinations: List<String>, val digits: List<String>)
}

fun main() = solve<Day08>()
