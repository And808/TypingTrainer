import java.io.File
import java.util.*

object FileManager {
    private const val PATH = """C:\Users\user\Documents\AMi_1.6.1.0\users\Ъ\texts\"""

    fun deleteAllFiles(): String {
        Arrays.stream(File(PATH).listFiles()).forEach(File::delete)
        return PATH
    }

    fun createFile(name: String, content: String) {
        val file = File("$PATH$name.txt")
        file.writeText(content, charset = Charsets.UTF_16)
    }

    fun readDictionaryFromFile(fileName: String) = File(fileName).useLines { it.toList() }
}