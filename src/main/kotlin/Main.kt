val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.language = Language.RUS
    repository.createExercise(ExerciseType.ONE_HUNDRED)
    repository.createExercise("!?")

    println()
    println("English language")
    repository.language = Language.EN
    repository.createExercise(ExerciseType.ONE_HUNDRED)
    repository.createExercise(":")
}