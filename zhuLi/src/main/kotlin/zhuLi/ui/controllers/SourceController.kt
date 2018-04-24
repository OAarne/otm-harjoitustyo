package zhuLi.ui.controllers

import javafx.collections.FXCollections
import tornadofx.Controller
import zhuLi.dao.JsonSourceListDao
import zhuLi.domain.Source
import zhuLi.domain.SourceListModel
import java.io.File

class SourceController() : Controller() {
    //    val sources: FXCollections.observableArrayList<Source>
    var sources = FXCollections.observableArrayList<Source>()
    val listModel: SourceListModel

    init {
//        val sourceProperties = Properties()
//        sourceProperties.load
        // TODO: pull the path from somewhere, like a config file

        var sourceFile = File("sources.json")
        sourceFile.createNewFile()

        listModel = SourceListModel(JsonSourceListDao(sourceFile))
        sources = FXCollections.observableArrayList<Source>(listModel.sources)
//        val resource = this.javaClass.getResourceAsStream("sampleSources.json")
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