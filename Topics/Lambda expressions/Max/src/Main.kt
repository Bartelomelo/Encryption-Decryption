import java.io.File

//val lambda: (Int, Int) -> Int = { i: Int, i1: Int -> maxOf(i, i1)}
fun main() {
    val file = File("C:/Users/Bartek/Downloads/words_with_numbers.txt").readLines()
    var count = 0
    file.forEach { if (it.first().isDigit()) count++ }
    println(count)
    }
