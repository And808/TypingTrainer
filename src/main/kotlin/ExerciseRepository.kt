import FileManager.deleteAllFiles
import FileManager.readLines
import StringExt.randomSymbol

class ExerciseRepository {

    private val RIGHT_HAND_CAPITAL_LETTERS_RUS = RIGHT_HAND_LETTERS_RUS.filter { it != 'ы' }.uppercase()
    private val LEFT_HAND_CAPITAL_LETTERS_RUS = LEFT_HAND_LETTERS_RUS.filter { it != 'ь' && it != 'ъ' }.uppercase()
    private val oneHundredDictionaryRus = readLines("src/main/kotlin/dictionaries/Соточка.txt")
    private val oneHundredDictionaryEn = readLines("src/main/kotlin/dictionaries/OneHundred.txt")
    private val dictionary5000Rus = readLines("src/main/kotlin/dictionaries/Частотный словарь 5000.txt")

    var language: Language = Language.RUS

    init {
        deleteAllFiles()
    }

    private fun getRepeatedString(pattern: String, length: Int = 100): String {
        return pattern.repeat((length + pattern.length) / pattern.length).substring(0, length)
    }

    private fun getAbracadabra(symbols: String, length: Int = 100): String {
        var text = ""
        while (text.length < length) {
            text += "${symbols.randomSymbol()}"
        }
        return text
    }

    private fun getAbracadabraWithPrefixAndPostfix(
        letters: String, prefixes: List<String> = listOf(), postfixes: List<String> = listOf(), length: Int = 100
    ): String {
        var text = ""
        while (text.length < length) {
            val randomLetter = letters.randomSymbol()
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

    private fun getOneHundredExercise(language: Language): List<String> {
        val dictionary = if (language === Language.RUS) oneHundredDictionaryRus else oneHundredDictionaryEn
        var text = ""
        while (text.length < 100) {
            text += "${dictionary.random()} "
        }
        return listOf(text.dropLast(1))
    }

    fun getPairedSymbolsExercise(symbols: String, language: Language): List<String> {
        val dictionary = if (language === Language.RUS) dictionary5000Rus else oneHundredDictionaryEn
        return listOf(
            symbols[0].toString().repeat(50) + symbols[1].toString().repeat(50),
            getAbracadabra(symbols, 100),
            dictionary.filter { it.length <= 3 }.shuffled().take(20).joinToString(" ") { "${symbols[0]}${it}${symbols[1]}" }
        )
    }

    fun createExercise(type: ExerciseType) {
        val name = getExerciseName(type)
        val exercise = when (type) {
            ExerciseType.ONE_HUNDRED -> Exercise(name, repository.getOneHundredExercise(language))
            ExerciseType.BRACKETS -> Exercise(name, repository.getPairedSymbolsExercise("()", language))
            ExerciseType.SQUARE_BRACKETS -> Exercise(name, repository.getPairedSymbolsExercise("[]", language))
            ExerciseType.CURLY_BRACKETS -> Exercise(name, repository.getPairedSymbolsExercise("{}", language))
        }

        val languageString = if (language === Language.RUS) "Упражнение" else "Exercise"
        exercise.list.forEachIndexed { index, content ->
            val fileName = if (exercise.list.size > 1) "$languageString $name-$index" else name
            FileManager.createFile(fileName, content)
            println(content)
        }
    }

    fun createExercise(name: String) {
        val exercise = when (name) {
            "Соточка" -> Exercise(name, repository.getOneHundredExercise(language))
            "()" -> Exercise(name, repository.getPairedSymbolsExercise("()", language))
            "[]" -> Exercise(name, repository.getPairedSymbolsExercise("[]", language))
            "{}" -> Exercise(name, repository.getPairedSymbolsExercise("{}", language))
            else -> Exercise("", emptyList())
        }

        exercise.list.forEachIndexed { index, content ->
            val fileName = if (exercise.list.size > 1) "$name-$index" else name
            FileManager.createFile(fileName, content)
            println(content)
        }
    }

    fun createExerciseFromFile(name: String) {
        val lines = readLines("src/main/kotlin/dictionaries/ExercisesRus.txt")
        val list = lines.takeLastWhile { it != name }.takeWhile { it.length > 30 }
        val exercise = Exercise(name, list)

        list.forEachIndexed { index, content ->
            val fileName = if (list.size > 1) "$name-$index" else name
            FileManager.createFile(fileName, content)
        }
        println(exercise)
    }

    private fun getExerciseName(type: ExerciseType): String {
        return when (type) {
            ExerciseType.ONE_HUNDRED -> if (language === Language.RUS) "Соточка" else "OneHundred"
            ExerciseType.BRACKETS -> "Скобки"
            ExerciseType.SQUARE_BRACKETS -> "Квадратные скобки"
            ExerciseType.CURLY_BRACKETS -> "Фигурные скобки"
        }
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