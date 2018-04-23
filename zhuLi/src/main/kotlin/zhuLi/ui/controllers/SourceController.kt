package zhuLi.ui.controllers

import javafx.collections.FXCollections
import tornadofx.Controller
import zhuLi.dao.JsonSourceListDao
import zhuLi.domain.Source
import zhuLi.domain.SourceListModel
import java.io.File
import java.util.Date

class SourceController() : Controller() {
    //    val sources: FXCollections.observableArrayList<Source>
    var sources = FXCollections.observableArrayList<Source>()
    val listModel: SourceListModel

    init {
        // TODO: pull the path from somewhere
        var source = Source(1, "Top research", Date(1981, 12, 4), "", "ArXiv")
        listModel = SourceListModel(JsonSourceListDao(File("sampleSources.json")))
        sources = FXCollections.observableArrayList<Source>(listModel.sources)
    }

    fun addSource(source: Source) {
        sources.add(source)
    }

    fun deleteSource(source: Source) {
        sources.remove(source)
    }

    fun save() {
        listModel.save(sources)
    }
}