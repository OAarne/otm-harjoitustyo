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
import tornadofx.right
import tornadofx.singleAssign
import tornadofx.smartResize
import tornadofx.tableview
import zhuLi.domain.Source
import zhuLi.ui.SourceViewModel
import zhuLi.ui.controllers.SourceController

class MainView : View("Zhu Li - Digital Research Assistant") {

    private val controller: SourceController by inject()
    private val sourceModel: SourceViewModel by inject()
    private var sourceTable: TableView<Source> by singleAssign()

    override val root = borderpane {
        center {
            // TODO: do something with tablecellfragment to display files etc nicely
            tableview(controller.sources) {
                sourceTable = this
//              TODO: date is shown in american format, this is unacceptable
                column("Title", Source::titleProperty)
                column("Pub. Date", Source::pubDateProperty)
                column("Date Added", Source::addDateProperty)
                column("BibTex", Source::bibTexProperty)
                column("Publisher", Source::publisherProperty)
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
                button("Save").action { controller.save() }
            }
        }
        right {
            add<SourceEditor>()
        }
    }

    private fun addSource() {
        val newSource = Source()
        controller.addSource(newSource)
        sourceTable.selectionModel.select(newSource)
    }

    private fun deleteSource() {
        controller.deleteSource(sourceTable.selectionModel.selectedItem)
    }

    init {
        root.setPrefSize(1200.0, 800.0)
        sourceTable.selectionModel.select(0)
    }
    // TODO: Make it save on close?
}