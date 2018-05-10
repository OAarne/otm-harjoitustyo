package zhuLi.domain

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller
import zhuLi.dao.JsonSourceListDao
import zhuLi.dao.SourceListDao
import java.io.File

class SourceListService() : Controller() {
    var sources: ObservableList<Source>
    val dao: SourceListDao

    init {
        var sourceFile = File("sources.json")
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

    fun generateSourceBibTex(source: Source): String {
        val tag = source.title.toLowerCase()
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
    // TODO: something like getByProject should go here?
}