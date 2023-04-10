val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.createOneHundredRusExercise()
    repository.createExercises(Exercise("Скобки", repository.getRusBracketExercises()))

    println()
    println("English language")
    repository.createOneHundredEnExercise()
    repository.createExercises(Exercise("Capital letters", repository.getCapitalLettersEnExercises()))
}