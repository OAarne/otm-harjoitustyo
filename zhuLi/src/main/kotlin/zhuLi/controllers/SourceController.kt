package controllers

import javafx.collections.FXCollections
import models.Source
import tornadofx.Controller
import java.time.LocalDate

class SourceController : Controller() {
    val sources = FXCollections.observableArrayList<Source>()

    init {
        sources.add(Source(1,"Top research", listOf("Top Researcher"), LocalDate.of(1981,12,4), "", "ArXiv"))
        sources.add(Source(2,"Important paper", listOf("Influential scholar"), LocalDate.of(2001,1,23), "", "ArXiv"))
        sources.add(Source(3,"Pointless publication", listOf("Loser McGee"), LocalDate.of(1989,5,23), "", "ArXiv"))
        sources.add(Source(4,"Awful article", listOf("Terrible B. Person"), LocalDate.of(1998,8,11), "", "ArXiv"))
    }

    fun addSource(source: Source) {
        sources.add(source)
    }
}