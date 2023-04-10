data class Exercise(val name: String, val list: List<String>) {

    override fun toString(): String {
        return "$name\n" + list.joinToString("\n") { "   $it" }
    }
}
