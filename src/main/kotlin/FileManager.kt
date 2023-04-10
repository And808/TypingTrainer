import java.io.File

object FileManager {

    fun createFile(folder: String, name: String, content: String) {
        val file = File("$folder$name.txt")
        file.writeText(content, charset = Charsets.UTF_16)
    }

    fun readDictionaryFromFile(fileName: String) = File(fileName).useLines { it.toList() }
}