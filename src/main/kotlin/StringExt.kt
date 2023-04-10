import kotlin.random.Random

object StringExt {

    fun String.randomSymbol() = this[Random.nextInt(this.length)]
}