package zhuLi.ui.views

import javafx.scene.control.TableView
import tornadofx.View
import tornadofx.action
import tornadofx.bindSelected
import tornadofx.borderpane
import tornadofx.bottom
import tornadofx.button
import tornadofx.center
import tornadofx.column
import tornadofx.hbox
import tornadofx.makeEditable
import tornadofx.right
import tornadofx.singleAssign
import tornadofx.smartResize
import tornadofx.tableview
import zhuLi.domain.Source
import zhuLi.ui.controllers.SourceController
import java.time.LocalDate

class MainView : View("Zhu Li - Digital Research Assistant") {

    private val controller: SourceController by inject()
    private val sourceModel: SourceViewModel by inject()
    private var sourceTable: TableView<Source> by singleAssign()

    override val root = borderpane {
        center {
            tableview(controller.sources) {
                sourceTable = this
//              TODO: date is shown in american format, this is unacceptable
//              TODO: implement a way to edit authors
                column("id", Source::idProperty)
                column("title", Source::titleProperty).makeEditable()
//        readonlyColumn("authors", Source::authorsProperty)
                column("pub. date", Source::pubDateProperty)//.makeEditable()
                column("Date Added", Source::addDateProperty)
                column("BibTex", Source::bibTexProperty).makeEditable()
                column("publisher", Source::publisherProperty).makeEditable()
                bindSelected(sourceModel)
                isEditable = true
                prefWidth = 800.0
                smartResize()
            }
        }
        bottom {
            hbox {
                button("Add Source").action(::addSource)
                button("Delete selected source").action(::deleteSource)
                button("Save").action(::save)
            }
        }
        right {
            add<SourceEditor>()
        }
    }

    private fun addSource() {
        val newSource = Source(controller.sources.size + 1, "", LocalDate.now(), "", "")
        controller.addSource(newSource)
//        sourceTable.selectionModel.select(newSource)
        sourceTable.selectionModel.select(sourceTable.items.size - 1, sourceTable.columns[1])
    }

    private fun deleteSource() {
        controller.deleteSource(sourceTable.selectionModel.selectedItem)
    }

    private fun save() {
        controller.save()
    }

    init {
//        controller = SourceController("sources.json")
        root.setPrefSize(1200.0, 800.0)
    }
    // TODO: Make it save on close?
}