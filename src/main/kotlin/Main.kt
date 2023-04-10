import FileManager.deleteAllFiles

val repository = ExerciseRepository()

fun main() {
    val path = deleteAllFiles()

    val exerciseListRus = repository.getRusExercises()
    val exerciseListEn = repository.getEnExercises()

    val exerciseRus = Exercise("Скобки", exerciseListRus)
    val exerciseEn = Exercise("Capital letters", exerciseListEn)


    println("Русский язык")
    exerciseRus.list.forEachIndexed { index, content ->
        FileManager.createFile(path, "Упражнение (${exerciseRus.name}-$index)", content)
        println(content)
    }
    println()
    println("English language")
    exerciseEn.list.forEachIndexed { index, content ->
        FileManager.createFile(path, "Exercise (${exerciseEn.name}-$index)", content)
        println(content)
    }
}