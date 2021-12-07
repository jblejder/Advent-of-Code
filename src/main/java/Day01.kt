fun main() = solve<Day01>()

class Day01 : Problem() {

    override fun partOne(): Any {
        return readInput().windowed(2).count { (a, b) -> a < b }
    }

    override fun partTwo(): Any {
        return readInput().windowed(4).count { it[0] < it[3] }
    }


    private fun readInput(): List<Int> {
        return Unit::class.java.classLoader.getResource("day01.txt")!!
            .readText()
            .split('\n')
            .mapNotNull { it.toIntOrNull() }
    }
}
