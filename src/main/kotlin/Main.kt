val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.createOneHundredRusExercise()
    repository.createRusExercises(Exercise("Скобки", repository.getRusExercises()))

    println()
    println("English language")
    repository.createOneHundredEnExercise()
    repository.createEnExercises(Exercise("Capital letters", repository.getEnExercises()))
}