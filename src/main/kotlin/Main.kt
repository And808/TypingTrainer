val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.createOneHundredRusExercise()
    repository.createExercise(Exercise("Скобки", repository.getPairedSymbolsExercise("()", Language.RUS)))

    println()
    println("English language")
    repository.createOneHundredEnExercise()
    repository.createExercise(Exercise("Square brackets", repository.getPairedSymbolsExercise("[]", Language.EN)))
}