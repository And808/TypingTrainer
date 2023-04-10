import java.io.File
import java.util.*
import kotlin.random.Random

enum class Language {
    RUS,
    EN
}

private const val RIGHT_HAND_LETTERS_RUS = "йцукефывапячсми"
private const val LEFT_HAND_LETTERS_RUS = "нгшщзхъролджэтьбю"
private val RIGHT_HAND_CAPITAL_LETTERS_RUS = RIGHT_HAND_LETTERS_RUS.filter { it != 'ы' }.uppercase()
private val LEFT_HAND_CAPITAL_LETTERS_RUS = LEFT_HAND_LETTERS_RUS.filter { it != 'ь' && it != 'ъ' }.uppercase()

private const val ALPHABET_RUS = LEFT_HAND_LETTERS_RUS + RIGHT_HAND_LETTERS_RUS
private val ONE_HUNDRED_RUS =
    "и в не на я быть он с что а по это она этот к но они мы как из у который то за свой что весь год от так о для ты же все тот мочь вы человек такой его сказать только или ещё бы себя один как уже до время если сам когда другой вот говорить наш мой знать стать при чтобы дело жизнь кто первый очень два день её новый рука даже во со раз где там под можно ну какой после их работа без самый потом надо хотеть ли слово идти большой должен место иметь ничто".split(
        " "
    )
private val ONE_HUNDRED_EN =
    "the be to of and a in that have I it for not on with he as you do at this but his by from they we say her she or an will my one all would there their what so up out if about who get which go me when make can like time no just him know take person into year your good some could them see other than then now look only come its over think also back after use two how our work first well way even new want because any these give day most us".split(
        " "
    )
private const val RIGHT_HAND_LETTERS_EN = "yuiophjklnm"
private const val LEFT_HAND_LETTERS_EN = "qwertasdfgzxcvb"
private const val ALPHABET_EN = LEFT_HAND_LETTERS_EN + RIGHT_HAND_LETTERS_EN


var language = Language.RUS

fun String.randomLetter() = this[Random.nextInt(0, this.length)]

fun createFile(folder: String, name: String, content: String) {
    val file = File("$folder$name.txt")
    file.writeText(content, charset = Charsets.UTF_16)
}

fun getRepeatedString(pattern: String, length: Int = 100): String {
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
        getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN, postfixes = listOf(" "), length = 300),
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

fun getShuffledWords(text: String): String {
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

fun main() {
    val path = """C:\Users\user\Documents\AMi_1.6.1.0\users\Ъ\texts\"""
    Arrays.stream(File(path).listFiles()).forEach(File::delete)

    val exerciseListRus = mutableListOf<String>()
    val exerciseListEn = mutableListOf<String>()


    // Русский язык
    exerciseListRus.add(ONE_HUNDRED_RUS.shuffledText())
    exerciseListRus.add(getAbracadabraWithPrefixAndPostfix("("))
    exerciseListRus.add(getAbracadabraWithPrefixAndPostfix(")"))
    exerciseListRus.add(getAbracadabraWithPrefixAndPostfix("()"))
    exerciseListRus.add(getShuffledWords(ONE_HUNDRED_RUS.map { it.withPrefix("(") }.map { it.withPostfix(")") }.shuffledText()))
    exerciseListRus.add(getShuffledWords(ONE_HUNDRED_RUS.map { it.withPrefix("(") }.map { it.withPostfix(")") }.shuffledText()))
    exerciseListRus.add(getShuffledWords(ONE_HUNDRED_RUS.map { it.withPrefix("(") }.map { it.withPostfix(")") }.shuffledText()))

    // English language
    exerciseListEn.add(ONE_HUNDRED_EN.shuffledText())
    exerciseListEn.add(getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN.uppercase()))
    exerciseListEn.add(getAbracadabraWithPrefixAndPostfix(LEFT_HAND_LETTERS_EN.uppercase()))
    exerciseListEn.add(getAbracadabraWithPrefixAndPostfix(RIGHT_HAND_LETTERS_EN.uppercase() + LEFT_HAND_LETTERS_EN.uppercase()))
    exerciseListEn.add(ONE_HUNDRED_EN.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
    exerciseListEn.add(ONE_HUNDRED_EN.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())
    exerciseListEn.add(ONE_HUNDRED_EN.map { it.replaceFirstChar { it.uppercase() } }.shuffledText())


    println("Русский язык")
    exerciseListRus.forEachIndexed { index, content ->
        createFile(path, "Упражнение - $index (${content.substring(0, 10)})", content)
        println(content)
    }
    println()
    println("English language")
    exerciseListEn.forEachIndexed { index, content ->
        createFile(path, "Exercise - $index (${content.substring(0, 10)})", content)
        println(content)
    }
}