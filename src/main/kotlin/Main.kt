val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.language = Language.RUS
    repository.createOneHundredExercise()
    repository.createExercise(ExerciseType.BRACKETS)

    println()
    println("English language")
    repository.language = Language.EN
    repository.createOneHundredExercise()
    repository.createExercise(ExerciseType.SQUARE_BRACKETS)
}