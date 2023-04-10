val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.createRusExercises(Exercise("Скобки", repository.getRusExercises()))

    println()
    println("English language")
    repository.createEnExercises(Exercise("Capital letters", repository.getEnExercises()))
}