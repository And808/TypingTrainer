import FileManager.readDictionaryFromFile
import kotlin.random.Random

class ExerciseRepository {

    private val RIGHT_HAND_CAPITAL_LETTERS_RUS = Companion.RIGHT_HAND_LETTERS_RUS.filter { it != 'ы' }.uppercase()
    private val LEFT_HAND_CAPITAL_LETTERS_RUS =
        Companion.LEFT_HAND_LETTERS_RUS.filter { it != 'ь' && it != 'ъ' }.uppercase()

    private val ONE_HUNDRED_RUS = readDictionaryFromFile("src/main/kotlin/dictionaries/Соточка.txt").shuffled()
    private val ONE_HUNDRED_EN = readDictionaryFromFile("src/main/kotlin/dictionaries/OneHundred.txt").shuffled()

    private fun String.randomLetter() = this[Random.nextInt(0, this.length)]

    private fun getRepeatedString(pattern: String, length: Int = 100): String {
        return pattern.repeat((length + pattern.length) / pattern.length).substring(0, length)
    }

    fun getAbracadabraWithPrefixAndPostfix(
        letters: String, prefixes: List<String> = listOf(), postfixes: List<String> = listOf(), length: Int = 100
    ): String {
        var text = ""
        while (text.length < length) {
            val randomLetter = letters.randomLetter()
            val randomPrefix = prefixes.randomOrNull().orEmpty()
            val randomPostfix = postfixes.randomOrNull().orEmpty()
            text += "$randomPrefix$randomLetter$randomPostfix"
        }
        return text
    }

    fun getPatternExercises(symbols: String): List<String> {
        val list = mutableListOf<String>()
        symbols.map { it.toString() }.forEach { list.add(getRepeatedString(it, length = 100)) }
        list.add(getAbracadabraWithPrefixAndPostfix(symbols, length = 200))

        return list
    }

    fun getSpaceExercise(): List<String> {
        return listOf(
            getAbracadabraWithPrefixAndPostfix(Companion.RIGHT_HAND_LETTERS_EN, postfixes = listOf(" "), length = 300),
            getAbracadabraWithPrefixAndPostfix(Companion.LEFT_HAND_LETTERS_EN, postfixes = listOf(" "), length = 300),
            getAbracadabraWithPrefixAndPostfix(Companion.ALPHABET_EN, postfixes = listOf(" "), length = 300)
        )
    }

    fun getDigitsExercises(): List<String> {
        return getPatternExercises("567") +
                getPatternExercises("34") +
                getAbracadabraWithPrefixAndPostfix("34567", length = 200) +
                getPatternExercises("89") +
                getAbracadabraWithPrefixAndPostfix("3456789", length = 200) +
                getPatternExercises("120") +
                getAbracadabraWithPrefixAndPostfix("1234567890", length = 200)
    }

//fun List<String>.toText2(length: String): String {
//    val text = ""
//
//    this.forEach {
//        text += it
//    }
//    return text
//}

    fun List<String>.shuffledText(length: Int = 100): String {
        return this
            .shuffled()
            .take(22)
            .joinToString(" ")
    }

    fun String.withPrefix(prefix: String) = "$prefix$this"

    fun String.withPostfix(postfix: String) = "$this$postfix"

    public fun getShuffledWords(text: String): String {
        return text
            .split(" ")
            .shuffled()
            .take(22)
            .joinToString(" ")
    }

    fun getCapitalLettersExercise(text: String): String {
        return text
            .split(" ")
            .shuffled()
            .take(30)
            .joinToString(" ") { it.replaceFirstChar { it.uppercase() } }
    }

    public fun getRusExercises(): List<String> {
        val list = mutableListOf<String>()
        list.add(ONE_HUNDRED_RUS.shuffledText())
        list.add(getAbracadabraWithPrefixAndPostfix("("))
        list.add(getAbracadabraWithPrefixAndPostfix(")"))
        list.add(getAbracadabraWithPrefixAndPostfix("()"))
        list.add(getShuffledWords(ONE_HUNDRED_RUS.map { it.withPrefix("(") }.map { it.withPostfix(")") }
            .shuffledText()))
        list.add(getShuffledWords(ONE_HUNDRED_RUS.map { it.withPrefix("(") }.map { it.withPostfix(")") }
            .shuffledText()))
        list.add(getShuffledWords(ONE_HUNDRED_RUS.map { it.withPrefix("(") }.map { it.withPostfix(")") }
            .shuffledText()))
        return list
    }

    public fun getEnExercises(): List<String> {
        val list = mutableListOf<String>()
        list.add(ONE_HUNDRED_EN.shuffledText())
        list.add(getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN.uppercase()))
        list.add(getAbracadabraWithPrefixAndPostfix(LEFT_HAND_LETTERS_EN.uppercase()))
        list.add(getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN.uppercase() + LEFT_HAND_LETTERS_EN.uppercase()))
        list.add(ONE_HUNDRED_EN.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
        list.add(ONE_HUNDRED_EN.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
        list.add(ONE_HUNDRED_EN.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
        return list
    }

    companion object {
        private const val RIGHT_HAND_LETTERS_RUS = "йцукефывапячсми"
        private const val LEFT_HAND_LETTERS_RUS = "нгшщзхъролджэтьбю"
        private const val ALPHABET_RUS = LEFT_HAND_LETTERS_RUS + RIGHT_HAND_LETTERS_RUS
        private const val RIGHT_HAND_LETTERS_EN = "yuiophjklnm"
        private const val LEFT_HAND_LETTERS_EN = "qwertasdfgzxcvb"
        private const val ALPHABET_EN = LEFT_HAND_LETTERS_EN + RIGHT_HAND_LETTERS_EN
    }
}