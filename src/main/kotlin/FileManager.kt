import java.io.File
import java.util.*

object FileManager {

    fun deleteAllFiles(): String {
        val path = """C:\Users\user\Documents\AMi_1.6.1.0\users\Ъ\texts\"""
        Arrays.stream(File(path).listFiles()).forEach(File::delete)
        return path
    }

    fun createFile(folder: String, name: String, content: String) {
        val file = File("$folder$name.txt")
        file.writeText(content, charset = Charsets.UTF_16)
    }

    fun readDictionaryFromFile(fileName: String) = File(fileName).useLines { it.toList() }
}