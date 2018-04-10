package zhuLi.views

import controllers.SourceController
import javafx.collections.ObservableList
import models.Source
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableView
import models.SourceModel
import tornadofx.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainView : View("Zhu Li - Digital Research Assistant") {

    private val controller : SourceController by inject()
    private val model: SourceModel by inject()
    private var sourceTable: TableView<Source> by singleAssign()

    override val root = borderpane {
        center {
            tableview(controller.sources) {
                sourceTable = this
//              TODO: date is shown in american format, this is unacceptable
//              TODO: implement a way to edit authors
                column("id",Source::idProperty)
                column("title", Source::titleProperty).makeEditable()
                readonlyColumn("authors", Source::authorsProperty)
                column("pub. date", Source::pubDateProperty).makeEditable()
                column("Date Added", Source::addDateProperty)
                column("BibTex", Source::bibTexProperty).makeEditable()
                column("publisher", Source::publisherProperty).makeEditable()
                bindSelected(model)
                isEditable = true
                prefWidth = 800.0
                smartResize()
            }
        }
        bottom { button("Add Source").action(::addSource) }
    }

    private fun addSource() {
        val newSource = Source(controller.sources.size + 1 , "", listOf(""), LocalDate.now(), "", "")
        controller.addSource(newSource)
        sourceTable.selectionModel.select(newSource)
        sourceTable.edit(sourceTable.items.size -1, sourceTable.columns.first())
    }

    init {
        root.setPrefSize(800.0, 600.0)
    }
}