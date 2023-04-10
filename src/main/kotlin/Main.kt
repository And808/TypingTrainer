val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.createOneHundredRusExercise()
    repository.createExercises(Exercise("Скобки", repository.getPairedSymbolsExercise("()", Language.RUS)))

    println()
    println("English language")
    repository.createOneHundredEnExercise()
    repository.createExercises(Exercise("Square brackets", repository.getPairedSymbolsExercise("[]", Language.EN)))
}