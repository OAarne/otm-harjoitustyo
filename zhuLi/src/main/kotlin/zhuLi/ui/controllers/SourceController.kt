package zhuLi.ui.controllers

import javafx.collections.FXCollections
import tornadofx.Controller
import zhuLi.dao.SourceDao
import zhuLi.domain.SourceService
import zhuLi.ui.models.Source
import java.io.File
import java.util.Date

class SourceController() : Controller() {
    //    val sources: FXCollections.observableArrayList<Source>
    var sources = FXCollections.observableArrayList<Source>()
    val sourceService: SourceService

    init {
        // TODO: pull the path from somewhere
        var source = Source(1, "Top research", Date(1981, 12, 4), "", "ArXiv")
        sourceService = SourceService(SourceDao(File("sampleSources.json")))
        sources = FXCollections.observableArrayList<Source>(sourceService.sources)
    }

    fun addSource(source: Source) {
        sources.add(source)
    }

    fun save() {
    }
}