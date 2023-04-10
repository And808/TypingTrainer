import FileManager.deleteAllFiles
import FileManager.readDictionaryFromFile
import kotlin.random.Random

class ExerciseRepository {

    private val RIGHT_HAND_CAPITAL_LETTERS_RUS = RIGHT_HAND_LETTERS_RUS.filter { it != 'ы' }.uppercase()
    private val LEFT_HAND_CAPITAL_LETTERS_RUS = LEFT_HAND_LETTERS_RUS.filter { it != 'ь' && it != 'ъ' }.uppercase()

    private val oneHundredDictionaryRus = readDictionaryFromFile("src/main/kotlin/dictionaries/Соточка.txt")
    private val oneHundredDictionaryEn = readDictionaryFromFile("src/main/kotlin/dictionaries/OneHundred.txt")

    init {
        deleteAllFiles()
    }

    private fun String.randomLetter() = this[Random.nextInt(0, this.length)]

    private fun getRepeatedString(pattern: String, length: Int = 100): String {
        return pattern.repeat((length + pattern.length) / pattern.length).substring(0, length)
    }

    private fun getAbracadabra(symbols: String, length: Int = 100): String {
        var text = ""
        while (text.length < length) {
            text += "${symbols[Random.nextInt(symbols.length)]}"
        }
        return text
    }

    private fun getAbracadabraWithPrefixAndPostfix(
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

    private fun getPatternExercises(symbols: String): List<String> {
        val list = mutableListOf<String>()
        symbols.map { it.toString() }.forEach { list.add(getRepeatedString(it, length = 100)) }
        list.add(getAbracadabraWithPrefixAndPostfix(symbols, length = 200))

        return list
    }

    fun getSpaceExercise(): List<String> {
        return listOf(
            getAbracadabraWithPrefixAndPostfix(Companion.RIGHT_HAND_LETTERS_EN, postfixes = listOf(" "), length = 300),
            getAbracadabraWithPrefixAndPostfix(LEFT_HAND_LETTERS_EN, postfixes = listOf(" "), length = 300),
            getAbracadabraWithPrefixAndPostfix(ALPHABET_EN, postfixes = listOf(" "), length = 300)
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

    fun List<String>.shuffledText(): String {
        return this
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

    private fun getOneHundredExercise(dictionary: List<String>): String {
        var text = ""
        while (text.length < 100) {
            text += "${dictionary.random()} "
        }
        return text.dropLast(1)
    }

    fun getRusBracketExercises(): List<String> {
        val length = 100
        return listOf(
            "(".repeat(length),
            ")".repeat(length),
            getAbracadabra("()", length),
            oneHundredDictionaryRus.shuffled().take(20).joinToString(" ") { "(${it})" }
        )
    }

    fun getCapitalLettersEnExercises(): List<String> {
        val list = mutableListOf<String>()
        list.add(getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN.uppercase()))
        list.add(getAbracadabraWithPrefixAndPostfix(LEFT_HAND_LETTERS_EN.uppercase()))
        list.add(getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN.uppercase() + LEFT_HAND_LETTERS_EN.uppercase()))
        list.add(oneHundredDictionaryEn.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
        list.add(oneHundredDictionaryEn.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
        list.add(oneHundredDictionaryEn.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
        return list
    }

    fun createExercises(exerciseRus: Exercise) {
        exerciseRus.list.forEachIndexed { index, content ->
            FileManager.createFile("${exerciseRus.name}-$index", content)
            println(content)
        }
    }

    fun createOneHundredRusExercise() =
        FileManager.createFile("Соточка", getOneHundredExercise(oneHundredDictionaryRus))

    fun createOneHundredEnExercise() =
        FileManager.createFile("OneHundred", getOneHundredExercise(oneHundredDictionaryEn))

    companion object {
        private const val RIGHT_HAND_LETTERS_RUS = "йцукефывапячсми"
        private const val LEFT_HAND_LETTERS_RUS = "нгшщзхъролджэтьбю"
        private const val ALPHABET_RUS = LEFT_HAND_LETTERS_RUS + RIGHT_HAND_LETTERS_RUS
        private const val RIGHT_HAND_LETTERS_EN = "yuiophjklnm"
        private const val LEFT_HAND_LETTERS_EN = "qwertasdfgzxcvb"
        private const val ALPHABET_EN = LEFT_HAND_LETTERS_EN + RIGHT_HAND_LETTERS_EN
    }
}