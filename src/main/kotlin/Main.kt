val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.language = Language.RUS
    repository.createExercise(ExerciseType.ONE_HUNDRED)
    repository.createExerciseFromFile("()")
    repository.createExerciseFromFile("Кавычки")

    println()
    println("English language")
    repository.language = Language.EN
    repository.createExercise(ExerciseType.ONE_HUNDRED)
    repository.createExercise(ExerciseType.SQUARE_BRACKETS)
}