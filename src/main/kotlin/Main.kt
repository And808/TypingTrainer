val repository = ExerciseRepository()

fun main() {
    val exerciseRus = Exercise("Скобки", repository.getRusExercises())
    val exerciseEn = Exercise("Capital letters", repository.getEnExercises())

    println("Русский язык")
    repository.saveRusExercises(exerciseRus)

    println()
    println("English language")
    repository.saveEnExercises(exerciseEn)
}