val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.language = Language.RUS
    repository.createOneHundredExercise()
    repository.createExercise(Exercise("Скобки", repository.getPairedSymbolsExercise("()", Language.RUS)))

    println()
    println("English language")
    repository.language = Language.EN
    repository.createOneHundredExercise()
    repository.createExercise(Exercise("Square brackets", repository.getPairedSymbolsExercise("[]", Language.EN)))
}