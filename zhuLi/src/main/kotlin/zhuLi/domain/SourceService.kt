package zhuLi.domain

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller
import zhuLi.dao.JsonSourceListDao
import zhuLi.dao.SourceListDao
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

class SourceService : Controller() {
    var sources: ObservableList<Source>
    val dao: SourceListDao

    init {
        val properties = Properties()
        properties.setProperty("sourcePath", "sources.json")
        try {
            val inputStream = FileInputStream("config.properties")
            properties.load(inputStream)
        } catch (ex: FileNotFoundException) {}
        val path = properties.getProperty("sourcePath")
        val sourceFile = File(path)
        sourceFile.createNewFile()
        dao = JsonSourceListDao(sourceFile)
        sources = FXCollections.observableArrayList<Source>(dao.sources)
    }

    fun addSource(source: Source) {
        sources.add(source)
    }

    fun deleteSource(source: Source) {
        sources.remove(source)
    }

    fun save() {
        dao.save(sources)
    }

    fun reload() {
        sources = FXCollections.observableArrayList(dao.load())
    }

    /**
     * Generates a basic bibTex entry for the Source. Will likely have to be edited before real-world use.
     */

    fun generateSourceBibTex(source: Source): String {

        val tag = source.title.toLowerCase().split(" ").joinToString("")
        val title = source.title
        val authors = source.authors.toString().substring(1, source.authors.toString().length - 1)
        val type = source.type.toBibtex()
        val date = source.pubDate.year
        val publisher = source.publisher
        val publication = source.publication

        var bibtex = """
            |@$type{ $tag,
            |        title = "$title",
            |        author = "$authors",
            |        year = "$date",
            |        publisher = "$publisher",
            |        journaltitle = "$publication",
            |        booktitle = "$publication"
            |}
            """.trimMargin()

        return bibtex
    }

    /**
     * Concatenates the bibtex entries of the sources in the list to be copied into a .bib file
     */
    fun generateBibliography(selection: List<Source>): String {
        return selection.map { source -> source.bibTex }.joinToString("\n\n")
    }
    // TODO: something like getByProject should go here?
}