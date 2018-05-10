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

    // TODO: something like getByProject should go here?
}