import java.io.File
import java.util.*


fun main() {
    val path = """C:\Users\user\Documents\AMi_1.6.1.0\users\Ъ\texts\"""
    Arrays.stream(File(path).listFiles()).forEach(File::delete)
    val repository = ExerciseRepository()

    // Русский язык
    val exerciseListRus = repository.getRusExercises()

    // English language
    val exerciseListEn = repository.getEnExercises()


    println("Русский язык")
    exerciseListRus.forEachIndexed { index, content ->
        FileManager.createFile(path, "Упражнение - $index (${content.substring(0, 10)})", content)
        println(content)
    }
    println()
    println("English language")
    exerciseListEn.forEachIndexed { index, content ->
        FileManager.createFile(path, "Exercise - $index (${content.substring(0, 10)})", content)
        println(content)
    }
}