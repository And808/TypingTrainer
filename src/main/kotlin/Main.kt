val repository = ExerciseRepository()

fun main() {
    println("Русский язык")
    repository.createOneHundredRusExercise()
    repository.createExercises(Exercise("Скобки", repository.getBracketExercisesRus()))

    println()
    println("English language")
    repository.createOneHundredEnExercise()
    repository.createExercises(Exercise("Square brackets", repository.getSquareBracketsExercisesEn()))
}